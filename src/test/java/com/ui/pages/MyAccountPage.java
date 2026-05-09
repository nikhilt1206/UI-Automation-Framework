package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.naming.directory.SearchResult;
import java.time.Duration;

public final class MyAccountPage extends BrowserUtility {

    private static final By USER_NAME_LOCATOR = By.xpath("//div[@class='header_user_info']//span");
    private static final By SEARCH_TEXT_BOX_LOCATOR= By.id("search_query_top");
    private static final By ADD_NEW_ADDRESS_LINK_LOCATOR = By.xpath("//a[@title='Add my first address']");
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(USER_NAME_LOCATOR));
        //return getVisibleText(USER_NAME_LOCATOR);
        return user.getText();
    }

    public SearchResultPage searchForAProduct(String productName){
        enterText(SEARCH_TEXT_BOX_LOCATOR,productName);
        enterSpecialKey(SEARCH_TEXT_BOX_LOCATOR, Keys.ENTER);
        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        return searchResultPage;
    }

    public AddressPage goToAddAddressPage(){
        clickOn(ADD_NEW_ADDRESS_LINK_LOCATOR);

        AddressPage addressPage = new AddressPage(getDriver());
        return addressPage;
    }

}
