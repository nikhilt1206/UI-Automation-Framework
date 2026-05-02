package com.utility;

import com.constants.Browser;
import io.opentelemetry.sdk.metrics.internal.exemplar.AlwaysOffExemplarFilter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BrowserUtility {
    //This class will provide us methods to interact with selenium web driver
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public WebDriver getDriver() {
        return driver.get();
    }

    public BrowserUtility(WebDriver driver) {
        //Initialize the instance variable driver
        super();
        this.driver.set(driver);
    }

    public BrowserUtility(String browserName){
        logger.info("Launching browser for "+browserName);
        if(browserName.equalsIgnoreCase("chrome")){
            driver.set(new ChromeDriver());
        }
        else if(browserName.equalsIgnoreCase("edge")){
            driver.set(new EdgeDriver());
        }
        else{
            logger.error("Invalid Browser Name...Please select Chrome or Edge only");
            System.err.print("Invalid Browser Name...Please select Chrome or Edge only");
        }
    }

    public BrowserUtility(Browser browserName){
        logger.info("Launching browser for "+browserName);
        if(browserName==Browser.CHROME){
            driver.set(new ChromeDriver());
        }
        else if(browserName==Browser.EDGE){
            driver.set(new EdgeDriver());
        }
        else if(browserName==Browser.FIREFOX){
            driver.set(new FirefoxDriver());
        }
    }
    public BrowserUtility(Browser browserName,boolean isHeadless){
        logger.info("Launching browser for "+browserName);
        if(browserName==Browser.CHROME){
            if(isHeadless) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=old"); //headless mode
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options));
            }
            else{
                driver.set(new ChromeDriver());
            }
        }
        else if(browserName==Browser.EDGE){
            if(isHeadless) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=old"); //headless mode
                options.addArguments("-disable-gpu");
                driver.set(new EdgeDriver(options));
            }
            else {
                driver.set(new EdgeDriver());
            }
        }
        else if(browserName==Browser.FIREFOX){
            if(isHeadless){
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=old");
                options.addArguments("-disable-gpu");
                driver.set(new FirefoxDriver(options));
            }
            else {
                driver.set(new FirefoxDriver());
            }
        }
    }
    //Wrapper method to open URL
    public void goToWebsite(String url){
        logger.info("Visiting the website " +url);
        driver.get().get(url);
    }
    public void maximizeWindow(){
        logger.info("Maximizing the browser window");
        driver.get().manage().window().maximize();
    }
    public void clickOn(By locator){
        logger.info("Finding element with locator "+locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now performing click");
        element.click();
    }
    public void enterText(By locator, String textToEnter){
        logger.info("Finding element with locator "+locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now enter text "+textToEnter);
        element.sendKeys(textToEnter);
    }
    public String getVisibleText(By locator){
        logger.info("Finding element with locator "+locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now returning the visible text "+element.getText());
        return element.getText();
    }
    public String takeScreenShot(String name){
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        File screenshotData =  screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat format= new SimpleDateFormat("HH-mm-ss");
        String timeStamp = format.format(date);
        String path = System.getProperty("user.dir")+"//screenshots//"+name+" - "+timeStamp+".png";
        File sceenshotFile = new File(path);
        try {
            FileUtils.copyFile(screenshotData,sceenshotFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }
    public void quit(){
        driver.get().quit();
    }
}
