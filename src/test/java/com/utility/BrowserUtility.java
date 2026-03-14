package com.utility;

import com.constants.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BrowserUtility {
    //This class will provide us methods to interact with selenium web driver
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public BrowserUtility(WebDriver driver) {
        //Initialize the instance variable driver
        super();
        this.driver = driver;
    }

    public BrowserUtility(String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
        }
        else{
            System.err.print("Invalid Browser Name...Please select Chrome or Edge only");
        }
    }

    public BrowserUtility(Browser browserName){
        if(browserName==Browser.CHROME){
            driver = new ChromeDriver();
        }
        else if(browserName==Browser.EDGE){
            driver = new EdgeDriver();
        }
        else if(browserName==Browser.FIREFOX){
            driver = new FirefoxDriver();
        }
    }
    //Wrapper method to open URL
    public void goToWebsite(String url){
        driver.get(url);
    }
    public void maximizeWindow(){
        driver.manage().window().maximize();
    }
    public void clickOn(By locator){
        WebElement element = driver.findElement(locator);
        element.click();
    }
    public void enterText(By locator, String textToEnter){
        WebElement element = driver.findElement(locator);
        element.sendKeys(textToEnter);
    }
    public String getVisibleText(By locator){
        WebElement element = driver.findElement(locator);
        return element.getText();
    }
}
