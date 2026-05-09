package com.ui.pages;

import com.utility.BrowserUtility;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmAddressPage extends BrowserUtility {

    private static final By PROCEED_TO_CHECKOUT = By.name("processAddress");

    public ConfirmAddressPage(WebDriver driver) {
        super(driver);
    }

    public ShippmentPage goToShippmentPage(){
        clickOn(PROCEED_TO_CHECKOUT);
        return new ShippmentPage(getDriver())
;    }
}
