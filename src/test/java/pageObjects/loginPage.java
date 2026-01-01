package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends basePage {
	
	public loginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPaswd;
	@FindBy(xpath="//input[@type='submit']") WebElement btnSubmit;
	
	public void setEmail(String emailAdress) {
		txtEmail.sendKeys(emailAdress);
	}
	
	public void setPaswd(String pswd) {
		txtPaswd.sendKeys(pswd);
	}
	
	public void submitLogin() {
		btnSubmit.click();
	}

}
