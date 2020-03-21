package com.Ebanking.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Ebanking.pageObjects.loginPage;

import junit.framework.Assert;

public class TC_LoginTest extends Base {

	@Test
	public void LoginTest() throws IOException {

		logger.info("URL ist opened");

		loginPage lp = new loginPage(driver);
		lp.setUserName(userName);
		logger.info("Entered Username");

		lp.setPassword(password);
		logger.info("Entered password");

		lp.clickSubmit();
		logger.info("Submitted");

		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info(" Loging Test Case passed");
		} else {
			captureScreen(driver, "LoginTest");
			Assert.assertTrue(false);
			logger.info("Login Test Case failed");
		}
	}

}
