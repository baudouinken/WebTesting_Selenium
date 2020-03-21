package com.Ebanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Ebanking.pageObjects.AddCustomerPage;
import com.Ebanking.pageObjects.loginPage;

public class TC_AddCustomerTest extends Base {

	@Test
	public void addNewCustomer() throws InterruptedException, IOException {

		loginPage lp = new loginPage(driver);
		lp.setUserName(userName);
		lp.setPassword(password);
		lp.clickSubmit();
		logger.info("Looged");

		Thread.sleep(3000);

		logger.info("Adding customer data ...");
		AddCustomerPage addCust = new AddCustomerPage(driver);
		addCust.clickAddNewCustomer();
		addCust.custName(randomString(4));
		addCust.custGender("male");
		addCust.custDob("12", "09", "1978");
		Thread.sleep(3000);
		addCust.custAddress("DEUTSCHLAND");
		addCust.custCity(randomString(6));
		addCust.custState("Sing");
		addCust.custPinno("123456789");
		addCust.custTelephoneNo("04978451289");
		addCust.custEmailId(randomString(6) + "@gmail.com");
		addCust.custPassword("abcdef");
		addCust.custSubmit();

		Thread.sleep(10000);

		logger.info("Start customer validation ...");
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");

		if (res == true) {
			Assert.assertTrue(true);
			logger.info("Test case passed");
		} else {
			captureScreen(driver, "AddNewCustomer");
			Assert.assertTrue(false);
			logger.info("Test case Failed");
		}
	}

}
