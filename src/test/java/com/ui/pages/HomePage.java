package com.ui.pages;

import com.constants.Browser;
import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class HomePage extends BrowserUtility {
    //This class will follow page object design pattern - Page Class

    private static final By SIGN_IN_LINK_LOCATOR= By.xpath("//a[contains(text(),'Sign')]");

    public HomePage(Browser browserName) {
        super(browserName);
        goToWebsite("https://automationpractice.techwithjatin.com/");
    }

    //Page functions --> should not use void return type
    public LoginPage goToLoginPage(){
    clickOn(SIGN_IN_LINK_LOCATOR);
    LoginPage loginPage = new LoginPage(getDriver());
    return loginPage;
    }
}


