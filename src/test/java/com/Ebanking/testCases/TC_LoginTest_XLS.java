package com.Ebanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Ebanking.pageObjects.loginPage;
import com.Ebanking.utilities.XLUtils;

import junit.framework.Assert;

public class TC_LoginTest_XLS extends Base {

	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException {

		loginPage lp = new loginPage(driver);

		lp.setUserName(user);
		logger.info("Entered Username");

		lp.setPassword(pwd);
		logger.info("Entered Password");

		lp.clickSubmit();
		logger.info("Submitted");
		
		Thread.sleep(3000);

		if (IsAlertPresent() == true) {
			driver.switchTo().alert().accept();// close alert
			driver.switchTo().defaultContent();// swicht to default content
			Assert.assertTrue(false);
			logger.warning("Login failed");
		} else {
			Assert.assertTrue(true);
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();// close Lougout alert
			driver.switchTo().defaultContent();// swicht to default content
			logger.info("Login passed");
		}
	}

	public boolean IsAlertPresent() { // user definied method to check if alert present or not
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			// TODO: handle exception
			return false;
		}
	}

	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/Ebanking/testData/LoginData.xlsx";

		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

		String loginData[][] = new String[rownum][colcount];

		for (int i = 1; i < rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				loginData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j); // 1 0
			}
		}
		return loginData;
	}

}
