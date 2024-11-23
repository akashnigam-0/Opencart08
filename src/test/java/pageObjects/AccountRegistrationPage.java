package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

    WebDriver driver;	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement lnkFirstName ;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement lnkLastName ;
	@FindBy(xpath="//input[@id='input-email']") WebElement lnkEmail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement tel;
	@FindBy(xpath="//input[@id='input-password']") WebElement lnkPassword;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement lnkCnfPassword;
	@FindBy(xpath="//input[@value='0']") WebElement lnkSubscribe;
	@FindBy(xpath="//input[@name='agree']") WebElement lnkAgree;
	@FindBy(xpath="//input[@value='Continue']") WebElement lnkContinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;
	
	public void firstName(String fname) {
		lnkFirstName.sendKeys(fname);
	}
    public void lastName(String lname) {
		lnkLastName.sendKeys(lname);
	}
    public void email(String mail) {
		lnkEmail.sendKeys(mail);
	}
    public void tel(String no) {
		tel.sendKeys(no);
	}
    public void password(String pass) {
		lnkPassword.sendKeys(pass);
	}   
    public void cnf_password(String cpass) {
		lnkCnfPassword.sendKeys(cpass);
    }
    public void subscribe() {
		lnkSubscribe.click();
	}
    public void agree() {
    	lnkAgree.click();
	}
    public void continuebtn() {
		lnkContinue.click();
	}
    
    public String getConfirmMsg(){
    	try {
    		return (msgConfirmation.getText());
    	}
    	catch(Exception e){
    		return (e.getMessage());
    	}
    	
    }
	
}
