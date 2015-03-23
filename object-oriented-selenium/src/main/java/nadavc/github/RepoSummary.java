/*
 * Copyright © 2015 Nadav Cohen <nadavcoh@gmail.com>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the WTFPL, Version 2, as published by Sam Hocevar.
 * See the COPYING file for more details.
 */

package nadavc.github;

import nadavc.support.SeleniumSegment;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RepoSummary extends SeleniumSegment {

    @FindBy(className = "repo-list-name")
    private WebElement repoName;

    @FindBy(className = "repo-list-description")
    private WebElement repoDescription;

    public RepoSummary(WebDriver driver, SearchContext context) {
       super(driver, context);
    }

    public String getRepoName() {
        return repoName.getText();
    }

    public String getRepoDescription() {
        return repoDescription.getText();
    }

    public String getRepoLanguage() {
        List<WebElement> programmingLanguage = context.findElements(By.cssSelector("span[itemprop=programmingLanguage]"));
        boolean languageExists = programmingLanguage.size() > 0;
        return languageExists ? programmingLanguage.get(0).getText() : "[unspecified]";
    }
}
