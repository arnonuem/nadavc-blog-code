/*
 * Copyright © 2015 Nadav Cohen <nadavcoh@gmail.com>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the WTFPL, Version 2, as published by Sam Hocevar.
 * See the COPYING file for more details.
 */

package nadavc.support;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

public abstract class SeleniumSegment {
    protected final SearchContext context;
    protected final WebDriver driver;

    public SeleniumSegment(WebDriver driver, SearchContext context) {
        this.driver = driver;
        this.context = context;
        PageFactory.initElements(new SearchContextElementLocatorFactory(context), this);
    }

    protected void waitForElementToAppear(By by) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void waitForElementToDisappear(By by) {
        waitForElementToAppear(by);
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public class SearchContextElementLocatorFactory implements ElementLocatorFactory {

        private final SearchContext context;

        public SearchContextElementLocatorFactory(SearchContext context) {
            this.context = context;
        }

        @Override
        public ElementLocator createLocator(Field field) {
            return new DefaultElementLocator(context, field);
        }
    }
}
