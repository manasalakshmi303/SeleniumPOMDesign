package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CsvUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void goToRegisterPage() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getRegData() {
		return new Object[][] {
			{"pranav", "automation", "9987654321","pranav@123", "yes"},
			{"reethu", "automation", "9876543211","reethu@123", "no"},
			{"jaya", "automation", "9988765432", "jaya2123", "yes"}
		};
	}
	
	@DataProvider
	public Object[][] getRegSheetData() {
		return ExcelUtil.getTestData("register");
	}
	
	@DataProvider
	public Object[][] getRegCsvData() {
		return CsvUtil.csvData("register");
	}
	
	@Test(dataProvider= "getRegCsvData")
	public void registerTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue
			(registerPage.userRegister(firstName, lastName, StringUtil.getRandomEMail(), telephone, password, subscribe));
	}

}
