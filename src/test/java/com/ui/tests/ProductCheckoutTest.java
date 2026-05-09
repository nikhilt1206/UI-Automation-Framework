package com.ui.tests;

import com.constants.Size;
import com.ui.pages.ProductDetailPage;
import com.ui.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.constants.Size.*;

public class ProductCheckoutTest extends TestBase{

    private static final String SEARCH_TERM = "Printed Summer Dress";
    private SearchResultPage searchResultPage;

    @BeforeMethod(description = "User logs into the application and searches for a product")
    public void setUp(){
       searchResultPage = homePage.goToLoginPage().doLoginWith("bixol92172@flosek.com","password")
                .searchForAProduct(SEARCH_TERM);
    }

    @Test(description = "Verify if the logged in user is able to buy a dress",groups = {"e2e","smoke","sanity"})
    public void checkoutTest(){
        String result = searchResultPage.clickOnTheProductAtIndex(1).changeSize(L).addProductToCart().proceedToCheckout()
                .goToConfirmAddressPage().goToShippmentPage().goToPaymentPage().makePaymentByWire();
        //System.out.println(result);
        Assert.assertTrue(result.contains("complete"));
    }

}
