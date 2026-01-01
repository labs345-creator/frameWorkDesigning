package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;
import testBase.baseClass;
import utilities.dataProviders;

public class TC_003_LoginDDT extends baseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=dataProviders.class, groups= {"sanity", "regression", "master", "dataDriven"})
	public void loginDDT(String email, String pswd, String exp) {
		
		logger.info("starting test");
		try {
	homePage hp = new homePage(driver);
	hp.clickMyAccount();
	hp.clickLoginAcct();
	
	loginPage lp = new loginPage(driver);
	lp.setEmail(email);
	lp.setPaswd(pswd);
	lp.submitLogin();
	
	myAccountPage ap = new myAccountPage(driver);
	boolean msgStatus = ap.validateMsg();
	//Assert.assertEquals(msgStatus, true);
	if(exp.equalsIgnoreCase("Valid")) {
		if(msgStatus == true)
		{
			ap.clickLogout();
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}
	if(exp.equalsIgnoreCase("invalid")) {
		if(msgStatus==true) {
			ap.clickLogout();
			Assert.assertTrue(false);
		}
		else {
			Assert.assertTrue(true);
		}
	}
		}
		catch(Exception e) {
			Assert.fail();
		}
	logger.info("tests completed");
	}

}
