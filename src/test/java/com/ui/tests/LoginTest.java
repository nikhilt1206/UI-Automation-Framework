package com.ui.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    public static  void main(String[] args){
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationpractice.techwithjatin.com/");
        driver.manage().window().maximize();

        By signInLocator = By.xpath("//a[contains(text(),'Sign')]");
        WebElement signInLinkWebElement = driver.findElement(signInLocator);
        signInLinkWebElement.click();

        By emailTextBoxLocator = By.id("email");
        WebElement emailTextBoxWebElement = driver.findElement(emailTextBoxLocator);
        emailTextBoxWebElement.sendKeys("bixol92172@flosek.com");

        By passwordTextBoxLocator = By.id("passwd");
        WebElement passwordTextBoxWebElement = driver.findElement(passwordTextBoxLocator);
        passwordTextBoxWebElement.sendKeys("password");

        By submitLoginButtonLocator = By.id("SubmitLogin");
        WebElement submitLoginButtonWebElement = driver.findElement(submitLoginButtonLocator);
        submitLoginButtonWebElement.click();

        //driver.quit();
    }
}

