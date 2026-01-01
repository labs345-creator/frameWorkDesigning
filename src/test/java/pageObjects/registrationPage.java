package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class registrationPage extends basePage{
	
	public registrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFname;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLname;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelePhone;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPasswrd;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtCnfrmPasswrd;
	@FindBy(xpath="//input[@type='checkbox']") WebElement chckboxPrivacy;
	@FindBy(xpath="//input[@type='submit']") WebElement submitBtn;
	@FindBy(xpath="//div[@id='content']//h1") WebElement TxtsuccessMsg;
	
	public void fillFrstName(String FName) {
		txtFname.sendKeys(FName);
	}
	
	public void fillLastName(String LName) {
		txtLname.sendKeys(LName);
	}
	
	public void fillEmail(String Email) {
		txtEmail.sendKeys(Email);
	}
	
	public void fillTelephone(String phoneNum) {
		txtTelePhone.sendKeys(phoneNum);
	}
	
	public void fillpswd(String paswd) {
		txtPasswrd.sendKeys(paswd);
	}
	
	public void fillCnfrmpswd(String cnfmPaswd) {
		txtCnfrmPasswrd.sendKeys(cnfmPaswd);
	}
	
	public void chckPrivacy() {
		chckboxPrivacy.click();
	}
	
	public void submitBtn() {
		submitBtn.click();
	}
	
	public String getMessage() {
		try {
			return (TxtsuccessMsg.getText());
		} catch(Exception e) {
			return(e.getMessage());
		}
	}

}
