package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest{
	
	@DataProvider
	public Object[][] getnegativeLoginData() {
		return new Object[][] {
			{"testuser1@gmail.com", "test@123"},
			{"manasa1@yopmail.com", "test@1234"},
			{"manasa11@gmail.com", "Pranav@13"},
			{"","test@123"},
			{"manasa1@gmail.com", ""},
			{"",""}
			};
		}
	
	
	@Test(dataProvider = "getnegativeLoginData")
	public void negativeLoginTest(String invalidUN, String invalidPWD) {
		Assert.assertTrue(loginPage.doLoginWithInvalidCredentials(invalidUN, invalidPWD));
	}

}
