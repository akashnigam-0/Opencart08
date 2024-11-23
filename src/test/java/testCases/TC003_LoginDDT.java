package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.baseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends baseClass {

    @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class,groups="DataDriven")
    public void verify_loginDDT(String email, String pwd, String exp) {
        try {
            logger.info("---------*******  TC_002_LoginTest *******------------- ");
            
            // HomePage
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account.");
            
            hp.clickLogin();
            logger.info("Clicked on Login.");
            
            // LoginPage
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            logger.info("Entered email.");
            
            lp.setPass(pwd);
            logger.info("Entered password.");
            
            lp.clickLogin();
            logger.info("Clicked on Login button.");
            
            // MyAccountPage
            MyAccountPage ab = new MyAccountPage(driver);
            boolean accountExists = ab.isMyAccountPageExist();
            
            if (exp.equalsIgnoreCase("valid")) {
                if (accountExists == true) {
                    logger.info("Login successful, expected result: Valid.");
                    Assert.assertTrue(true);
                    ab.clickLogout();
                    logger.info("Logged out successfully.");
                } else {
                    logger.warn("Login failed, but expected result was Valid.");
                    Assert.assertTrue(false);
                }
            } else { // exp is "invalid"
                if (accountExists == true) {
                    logger.warn("Login successful, but expected result was Invalid.");
                    ab.clickLogout();
                    Assert.assertTrue(false);
                } else {
                    logger.info("Login failed as expected, result: Invalid.");
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            logger.error("An error occurred during the test case execution: " + e.getMessage());
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }
    }
}
