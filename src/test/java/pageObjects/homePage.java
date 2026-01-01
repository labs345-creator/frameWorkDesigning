package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basePage {
	
	public homePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath ="//span[text()='My Account']") WebElement spanMyAcct;
	@FindBy(xpath="//a[text()='Register']") WebElement linkRegAcct;
	@FindBy(xpath="//a[text()='Login']") WebElement linkLoginAcct;
	
	public void clickMyAccount() {
		spanMyAcct.click();
	}
	
	public void clickRegAccount() {
		linkRegAcct.click();
	}
	
	public void clickLoginAcct() {
		linkLoginAcct.click();
	}
	

}
