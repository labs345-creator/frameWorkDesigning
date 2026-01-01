package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.registrationPage;
import testBase.baseClass;

public class TC_001_AccountRegistrationTest extends baseClass {
	
	@Test(groups= {"sanity", "regression", "master", "dataDriven"})
	public void verifyRegistration() {
		logger.info("test started");
		try {
		homePage homeObj = new homePage(driver);
		homeObj.clickMyAccount();
		logger.info("clicked on my account successfully");
		homeObj.clickRegAccount();
		logger.info("clicked on register successfully");

		registrationPage regObj = new registrationPage(driver);
		regObj.fillFrstName(randomNewString());
		regObj.fillLastName(randomNewString());
		regObj.fillEmail(randomNewString()+"@hotmail.com");
		regObj.fillTelephone(randomNewNumber());
		String paswd = randomNewalphaNumeric();
		regObj.fillpswd(paswd);
		regObj.fillCnfrmpswd(paswd);
		regObj.chckPrivacy();
		regObj.submitBtn();
		
		logger.info("all details filled successfully");
		
		String msg = regObj.getMessage();
		if(msg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
			logger.info("message is successfully validated");
		}
		else {
			
			logger.error("Test failed .. ");
			logger.debug("Debug logs .. ");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(msg, "Your Account Has Been Created");
		//logger.info("message is successfully validated");
		}
		catch(Exception e) {
			
			Assert.fail();
		}
		
		logger.info("execution completed");
		
	}
	
	

}
