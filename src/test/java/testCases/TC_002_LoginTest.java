package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.baseClass;

public class TC_002_LoginTest extends baseClass {

    @Test(groups={"Sanity","Master"})
    public void verify_login() {
        try {
            logger.info("---------*******  TC_002_LoginTest *******------------- ");
            
            // HomePage
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account 01.");
            
            hp.clickLogin();
            logger.info("Clicked on Login.");
            
            // LoginPage
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(p.getProperty("email"));
            logger.info("Entered email.");
            
            lp.setPass(p.getProperty("pwd"));
            logger.info("Entered password.");
            
            lp.clickLogin();
            logger.info("Clicked on Login button.");
            
            // MyAccountPage
            MyAccountPage ab = new MyAccountPage(driver);
            boolean accountExists = ab.isMyAccountPageExist();
            
            if (accountExists) {
                logger.info("Login successful and My Account page exists.");
                Assert.assertTrue(true);
            } else {
                logger.warn("Login failed or My Account page not found.");
                Assert.fail("Login failed or My Account page not found.");
            }
          
        } catch (Exception e) {
            logger.error("An error occurred during the test case execution: " + e.getMessage());
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }
    }
}
 