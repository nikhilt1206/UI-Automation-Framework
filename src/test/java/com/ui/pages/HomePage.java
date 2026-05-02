package com.ui.pages;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import static com.utility.PropertiesUtil.*;

import com.utility.JSONUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public final class HomePage extends BrowserUtility {
    //This class will follow page object design pattern - Page Class
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private static final By SIGN_IN_LINK_LOCATOR= By.xpath("//a[contains(text(),'Sign')]");

    public HomePage(Browser browserName, boolean isHeadless) {
        super(browserName,isHeadless);
        goToWebsite(JSONUtility.readJSON(QA).getUrl());
    }

    public HomePage(WebDriver driver){
        super(driver);
        goToWebsite(JSONUtility.readJSON(QA).getUrl());
    }

    //Page functions --> should not use void return type
    public LoginPage goToLoginPage(){
        logger.info("Trying to perform click to goto Sign in Page");
    clickOn(SIGN_IN_LINK_LOCATOR);
    LoginPage loginPage = new LoginPage(getDriver());
    return loginPage;
    }
}


