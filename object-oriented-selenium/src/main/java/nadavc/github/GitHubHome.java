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

public class GitHubHome extends SeleniumSegment {

    @FindBy(css = "a[href*='login']")
    private WebElement signInButton;

    public GitHubHome(WebDriver driver) {
        super(driver, driver);
        driver.get("http://github.com");
    }

    public SignInPage goToSignIn() {
        signInButton.click();
        waitForElementToAppear(By.cssSelector("input.btn"));
        return new SignInPage(driver);
    }

    public OrgProfilePage goToOrgProfile(String orgName) {
        driver.get("http://github.com/" + orgName);
        return new OrgProfilePage(driver);
    }
}