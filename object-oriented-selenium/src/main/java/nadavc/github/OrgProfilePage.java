/*
 * Copyright © 2015 Nadav Cohen <nadavcoh@gmail.com>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the WTFPL, Version 2, as published by Sam Hocevar.
 * See the COPYING file for more details.
 */

package nadavc.github;

import nadavc.support.SeleniumSegment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;

import static java.text.MessageFormat.format;

public class OrgProfilePage extends SeleniumSegment {

    @FindBy(css = "em.current")
    private WebElement currentPageNumber;

    @FindBy(css = "h1.org-name")
    private WebElement orgName;

    @FindBy(css = "p.org-description")
    private WebElement orgDescription;

    @FindBy(className = "next_page")
    private WebElement nextPage;

    public OrgProfilePage(WebDriver driver) {
        super(driver, driver);
    }

    public Integer getPageNumber() {
        return Integer.parseInt(currentPageNumber.getText());
    }

    public String getOrgName() {
        return orgName.getText();
    }

    public String getOrgDescription() {
        return orgDescription.getText();
    }

    public boolean hasNextPage() {
        String tagName = nextPage.getTagName();
        return tagName.equals("a"); // when there are no more pages, this would be a <span/>
    }

    public OrgProfilePage goToNextPage() {
        nextPage.click();
        waitForElementToDisappear(By.className("context-loader"));
        return new OrgProfilePage(driver);
    }

    public Iterable<RepoSummary> getRepos() {
        return RepoDetailsIterator::new;
    }

    public class RepoDetailsIterator implements Iterator<RepoSummary> {

        private static final String REPO_DETAILS_SELECTOR = "div.repo-list-item:nth-child({0})";
        private int index = 0;
        private int repoCount = driver.findElements(By.className("repo-list-name")).size();

        @Override
        public boolean hasNext() {
            return index + 1 != repoCount;
        }

        @Override
        public RepoSummary next() {
            String cssSelector = format(REPO_DETAILS_SELECTOR, index + 2);
            WebElement rootOfRepoDetails = driver.findElement(By.cssSelector(cssSelector));
            index++;
            return new RepoSummary(driver, rootOfRepoDetails);
        }
    }

}
