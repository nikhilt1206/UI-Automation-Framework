package com.ui.tests;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    public static  void main(String[] args){
        WebDriver driver = new ChromeDriver();
        BrowserUtility browserUtility = new BrowserUtility(driver);
        browserUtility.goToWebsite("https://automationpractice.techwithjatin.com/");
        browserUtility.maximizeWindow();

        By signInLocator = By.xpath("//a[contains(text(),'Sign')]");
        browserUtility.clickOn(signInLocator);

        By emailTextBoxLocator = By.id("email");
        browserUtility.enterText(emailTextBoxLocator,"bixol92172@flosek.com");

        By passwordTextBoxLocator = By.id("passwd");
        browserUtility.enterText(passwordTextBoxLocator,"password");

        By submitLoginButtonLocator = By.id("SubmitLogin");
        browserUtility.clickOn(submitLoginButtonLocator);

    }
}

