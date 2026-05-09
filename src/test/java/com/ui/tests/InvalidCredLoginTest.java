package com.ui.tests;

import com.ui.pojo.User;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

    @Listeners(com.ui.listeners.TestListener.class)
    public class InvalidCredLoginTest extends TestBase{

        Logger logger = LoggerUtility.getLogger(this.getClass());
        private static final String INVALID_EMAIL_ADDRESS="nikhilt1220@gmail.com";
        private static final String INVALID_PASSWORD="nikhil@1450";

        @Test(description = "Verifies if the proper error message is shown for the user when they use invalid credentials", groups = {"e2e","sanity","smoke"})
        public void loginTest(){

            assertEquals(homePage.goToLoginPage().doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS,INVALID_PASSWORD).getErrorMessage(),"Authentication failed.");
        }
    }

