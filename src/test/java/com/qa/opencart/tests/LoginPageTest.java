package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EP-100: Design the Open Cart App Login Page")
@Feature("F-101:design open car login feature")
@Story("US-50: develop login core features: title, url, user is able to login")
public class LoginPageTest extends BaseTest{
	
	//shouldn't write WebDriver or any driver related method here
	//assertions should write here
	
	
	@Description("Login page title test....")
	@Owner("Manasa")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("login page title: "+actTitle);
		//chaintest log is only available for test purpose 
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Login page url test....")
	@Owner("Manasa")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Description("forgot password link exist test....")
	@Owner("Manasa")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void isForgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("Login page header test....")
	@Owner("Manasa")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void isLoginPageHeaderExistTest() {
		Assert.assertTrue(loginPage.isHeaderExist());
	}
	
	@Description("user is able to login to app with the correct credentials....")
	@Owner("Manasa")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = Integer.MAX_VALUE)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

}
