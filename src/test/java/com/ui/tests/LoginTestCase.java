package com.ui.tests;

import static com.constants.Browser.*;
import com.ui.pages.HomePage;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestCase {
    HomePage homePage;

    @BeforeMethod (description = "Load the Homepage of the website")
    public void setUp(){
        homePage = new HomePage(CHROME);
    }

    @Test (description = "Verifies with the valid user is able to login into the application", groups = {"e2e","sanity"})
    public void loginTest(){

        String username = homePage.goToLoginPage().doLoginWith("bixol92172@flosek.com","password").getUserName();
        assertEquals(username,"Nikhil TIWARI");

    }
}

