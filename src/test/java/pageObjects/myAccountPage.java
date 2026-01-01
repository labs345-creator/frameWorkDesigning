package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class myAccountPage extends basePage {
	
	public myAccountPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//div[@id='content']//h2[text()='My Account']") WebElement msg;
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']") WebElement logoutBtn;
	
	public boolean validateMsg() {
		try {
			return (msg.isDisplayed());
			
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		logoutBtn.click();
	}

}
