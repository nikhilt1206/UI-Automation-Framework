package com.utility;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BrowserUtility {
    //This class will provide us methods to interact with selenium web driver
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private WebDriverWait wait;

    public WebDriver getDriver() {
        return driver.get();
    }

    public BrowserUtility(WebDriver driver) {
        //Initialize the instance variable driver
        super();
        this.driver.set(driver);
        wait = new WebDriverWait(driver,Duration.ofSeconds(30L));
    }

    public BrowserUtility(String browserName) {
        logger.info("Launching browser for " + browserName);
        if (browserName.equalsIgnoreCase("chrome")) {
            driver.set(new ChromeDriver());
            wait = new WebDriverWait(driver.get(),Duration.ofSeconds(30L));
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver.set(new EdgeDriver());
            wait = new WebDriverWait(driver.get(),Duration.ofSeconds(30L));
        } else {
            logger.error("Invalid Browser Name...Please select Chrome or Edge only");
            System.err.print("Invalid Browser Name...Please select Chrome or Edge only");
        }
    }

    public BrowserUtility(Browser browserName) {
        logger.info("Launching browser for " + browserName);
        if (browserName == Browser.CHROME) {
            driver.set(new ChromeDriver());
            wait = new WebDriverWait(driver.get(),Duration.ofSeconds(30L));
        } else if (browserName == Browser.EDGE) {
            driver.set(new EdgeDriver());
            wait = new WebDriverWait(driver.get(),Duration.ofSeconds(30L));
        } else if (browserName == Browser.FIREFOX) {
            driver.set(new FirefoxDriver());
            wait = new WebDriverWait(driver.get(),Duration.ofSeconds(30L));
        }
    }

    public BrowserUtility(Browser browserName, boolean isHeadless) {
        logger.info("Launching browser for " + browserName);
        if (browserName == Browser.CHROME) {
            if (isHeadless) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=old"); //headless mode
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options));
                wait = new WebDriverWait(driver.get(),Duration.ofSeconds(30L));
            } else {
                driver.set(new ChromeDriver());
                wait = new WebDriverWait(driver.get(),Duration.ofSeconds(30L));
            }
        } else if (browserName == Browser.EDGE) {
            if (isHeadless) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=old"); //headless mode
                options.addArguments("-disable-gpu");
                driver.set(new EdgeDriver(options));
                wait = new WebDriverWait(driver.get(),Duration.ofSeconds(30L));
            } else {
                driver.set(new EdgeDriver());
                wait = new WebDriverWait(driver.get(),Duration.ofSeconds(30L));
            }
        } else if (browserName == Browser.FIREFOX) {
            if (isHeadless) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=old");
                options.addArguments("-disable-gpu");
                driver.set(new FirefoxDriver(options));
                wait = new WebDriverWait(driver.get(),Duration.ofSeconds(30L));
            } else {
                driver.set(new FirefoxDriver());
                wait = new WebDriverWait(driver.get(),Duration.ofSeconds(30L));
            }
        }
    }

    //Wrapper method to open URL
    public void goToWebsite(String url) {
        logger.info("Visiting the website " + url);
        driver.get().get(url);
    }

    public void maximizeWindow() {
        logger.info("Maximizing the browser window");
        driver.get().manage().window().maximize();
    }

    public void clickOn(By locator) {
        logger.info("Finding element with locator " + locator);
        //WebElement element = driver.get().findElement(locator);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        logger.info("Element found and now performing click");
        element.click();
    }

    public void clickOnCheckBox(By locator) {
        logger.info("Finding element with locator " + locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Element found and now performing click");
        element.click();
    }

    public void clickOn(WebElement element) {
        logger.info("Element found and now performing click");
        element.click();
    }

    public void enterText(By locator, String textToEnter) {
        logger.info("Finding element with locator " + locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Element found and now enter text " + textToEnter);
        element.sendKeys(textToEnter);
    }

    public void enterSpecialKey(By locator, Keys keyToEnter) {
        logger.info("Finding element with locator " + locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Element found and now enter special key " + keyToEnter);
        element.sendKeys(keyToEnter);
    }

    public String getVisibleText(By locator) {
        logger.info("Finding element with locator " + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now returning the visible text " + element.getText());
        return element.getText();
    }

    public String getVisibleText(WebElement element) {
        logger.info("Returning the visible text " + element.getText());
        return element.getText();
    }

    public List<String> getAllVisibleText(By locator) {
        logger.info("Finding all elements with locator " + locator);
        List<WebElement> elementList = driver.get().findElements(locator);
        logger.info("Elements found and now printing the list of elements");
        List<String> visibleTextList = new ArrayList<String>();
        for (WebElement element : elementList) {
            System.out.println(getVisibleText(element));
            visibleTextList.add(getVisibleText(element));
        }
        return visibleTextList;
    }

    public List<WebElement> getAllElements(By locator) {
        logger.info("Finding all elements with locator " + locator);
        List<WebElement> elementList = driver.get().findElements(locator);
        logger.info("Elements found and now printing the list of elements");
        List<String> visibleTextList = new ArrayList<String>();
        return elementList;
    }

    public String takeScreenShot(String name) {
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
        String timeStamp = format.format(date);
        String path = "./screenshots/" + name + " - " + timeStamp + ".png";
        File sceenshotFile = new File(path);
        try {
            FileUtils.copyFile(screenshotData, sceenshotFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    public void quit() {
        driver.get().quit();
    }

    public void waitForVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForPresence(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void selectFromDropdown(By dropdownLocator, String optionToSelect) {
        logger.info("Finding element with locator " + dropdownLocator);
        WebElement element = driver.get().findElement(dropdownLocator);
        Select select = new Select(element);
        logger.info("Selecting the option " + optionToSelect);
        select.selectByVisibleText(optionToSelect);
    }

    public void selectHiddenDropdown(By dropdownLocator, String visibleText) {
        logger.info("Finding hidden dropdown with locator " + dropdownLocator);
        WebElement element = waitForPresence(dropdownLocator);
        // Get option value using visible text
        Select select = new Select(element);
        String value = select.getOptions()
                .stream()
                .filter(option -> option.getText().equals(visibleText))
                .findFirst()
                .get()
                .getAttribute("value");
        JavascriptExecutor js = (JavascriptExecutor) driver.get();
        logger.info("Selecting dropdown option " + visibleText);
        js.executeScript(
                "arguments[0].value=arguments[1]; arguments[0].dispatchEvent(new Event('change'));",
                element,
                value
        );
    }

    public void clearText(By textBoxLocator) {
        logger.info("Finding element with locator" + textBoxLocator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textBoxLocator));
        logger.info("Element found and now clearing the textbox field");
        element.clear();
    }
}
