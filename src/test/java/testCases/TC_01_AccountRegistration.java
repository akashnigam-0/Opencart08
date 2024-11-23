package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.baseClass;

public class TC_01_AccountRegistration extends baseClass {

    @Test(groups={"Regression","Master"})
    public void verify_account_registration() {
        try {
            logger.info("---------*******  TC_01_AccountRegistration *******------------- ");
            
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account.");

            hp.clickRegister();
            logger.info("Navigated to the registration page.");
            
            AccountRegistrationPage reg = new AccountRegistrationPage(driver);
            reg.firstName("akashs");
            logger.info("Entered first name.");

            reg.lastName("nigams");
            logger.info("Entered last name.");

            reg.email(randomStr() + "@gmail.com");
            logger.info("Entered email.");

            reg.tel(randomNum());
            logger.info("Entered telephone number.");

            String pass = randomPass();
            reg.password(pass);
            logger.info("Entered password.");

            reg.cnf_password(pass);
            logger.info("Confirmed password.");

            reg.subscribe();
            logger.info("Subscribed to newsletter.");

            reg.agree();
            logger.info("Agreed to the terms and conditions.");

            reg.continuebtn();
            logger.info("Submitted the registration form.");

            String cnf_msg = reg.getConfirmMsg();
            Assert.assertEquals(cnf_msg, "Your Account Has Been Created!");
            logger.info("Account creation confirmation validated.");

            logger.info("Test case execution completed successfully.");

        } catch (Exception e) {
            logger.error("An error occurred during the test case execution: " + e.getMessage());
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }
    }
}
