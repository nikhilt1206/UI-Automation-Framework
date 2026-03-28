package com.ui.tests;

import static com.constants.Browser.*;
import com.ui.pages.HomePage;
import static org.testng.Assert.*;

import com.ui.pojo.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestCase {
    HomePage homePage;

    @BeforeMethod (description = "Load the Homepage of the website")
    public void setUp(){
        homePage = new HomePage(CHROME);
    }

    @Test (description = "Verifies with the valid user is able to login into the application", groups = {"e2e","sanity"}
    ,dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,dataProvider = "LoginTestDataProvider")
    public void loginTest(User user){

        String username = homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName();
        assertEquals(username,"Nikhil TIWARI");

    }
}

