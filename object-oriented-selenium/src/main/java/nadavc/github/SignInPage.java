/*
 * Copyright © 2015 Nadav Cohen <nadavcoh@gmail.com>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the WTFPL, Version 2, as published by Sam Hocevar.
 * See the COPYING file for more details.
 */

package nadavc.github;

import nadavc.support.SeleniumSegment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends SeleniumSegment {

    @FindBy(id = "login_field")
    private WebElement loginField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "input[value='Sign in']")
    private WebElement signInButton;

    public SignInPage(WebDriver driver) {
        super(driver, driver);
    }

    public SignInPage withUsername(String username) {
        loginField.sendKeys(username);
        return this;
    }

    public SignInPage withPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public void signIn() {
        signInButton.click();
        // wait for profile home and create a new page...
    }

}
