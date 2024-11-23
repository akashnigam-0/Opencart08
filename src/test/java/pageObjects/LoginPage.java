package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	WebDriver driver;	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txtemail ;
	
	@FindBy(xpath="//input[@id='input-password']") WebElement txtpass ;
	
	@FindBy(xpath="//input[@value='Login']") WebElement btnlogin ;
	
	public void setEmail(String email) {
		txtemail.sendKeys(email);
	}
	public void setPass(String pwd) {
		txtpass.sendKeys(pwd);
	}
	public void clickLogin() {
		btnlogin.click();
	}

}
