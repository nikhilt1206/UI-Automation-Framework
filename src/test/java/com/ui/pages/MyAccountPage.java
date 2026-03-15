package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class MyAccountPage extends BrowserUtility {

    private static final By USER_NAME_LOCATOR = By.xpath("//div[@class='header_user_info']//span");
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(USER_NAME_LOCATOR));
        //return getVisibleText(USER_NAME_LOCATOR);
        return user.getText();
    }
}
