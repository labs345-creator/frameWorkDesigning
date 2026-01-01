package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;
import testBase.baseClass;

public class TC_002_AccountLoginTest extends baseClass{
	@Test(groups= {"sanity", "regression", "master", "dataDriven"})
	public void verifyLoginTest() {
		logger.info("starting login test");
		try {
		homePage hp = new homePage(driver);
		hp.clickMyAccount();
		hp.clickLoginAcct();
		
		loginPage lp = new loginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPaswd(p.getProperty("password"));
		lp.submitLogin();
		
		myAccountPage ap = new myAccountPage(driver);
		boolean msgStatus = ap.validateMsg();
		Assert.assertEquals(msgStatus, true);
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("test cases completed");
		
	}

}
