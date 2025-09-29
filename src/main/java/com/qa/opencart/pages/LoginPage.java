package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;


public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private static final Logger log = LogManager.getLogger(LoginPage.class);
	
	//no assertions here
	
	//private By locators : page objects
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgotPwdLink = By.linkText("Forgotten Password");
	private final By header = By.tagName("h2");
	private final By registerLink = By.linkText("Register");
	private final By loginErrormessg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
		
	
	//public constructors
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	//public page methods/actions
	
	@Step("getting the login page title...")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		//System.out.println("Login page title: "+title);
		log.info("Login page title: "+title);
		return title;
	}
	
	@Step("getting the login page url...")
	public String getLoginPageUrl() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
		//System.out.println("Login page URL: "+url);
		log.info("Login page URL: "+url);
		return url;
	}
	
	@Step("forgot password link exist...")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}
	
	@Step("page header exist...")
	public boolean isHeaderExist() {
		return eleUtil.isElementDisplayed(header);
	}
	
	@Step("login with correct user : {0} and correct password: {1}")
	public AccountsPage doLogin(String appUsername, String appPassword) {
		//System.out.println("Appilication credentials: "+ appUsername + ":" + appPassword);
		log.info("Appilication credentials: "+ appUsername + " : " + "**********");
		eleUtil.waitForElementVisible(email, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(appUsername);
		eleUtil.doSendKeys(password, appPassword);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	@Step("login with incorrect user : {0} and incorrect password: {1}")
	public boolean doLoginWithInvalidCredentials(String invalidUN, String invalidPWD) {
		log.info("Appilication invalid credentials: "+ invalidUN + ":" + invalidPWD);
		WebElement emailEle = eleUtil.waitForElementVisible(email, AppConstants.DEFAULT_MEDIUM_WAIT);
		emailEle.clear();
		emailEle.sendKeys(invalidUN);
		eleUtil.doSendKeys(password, invalidPWD);
		eleUtil.doClick(loginBtn);
		String errorMessg = eleUtil.doElementGetText(loginErrormessg);
		log.info("invalid creds error messg: "+ errorMessg);
		if(errorMessg.contains(AppConstants.LOGIN_BLANK_CREDS_MESSG)) {
			return true;
		}
		else if(errorMessg.contains(AppConstants.LOGIN_INVALID_CREDS_MESSG)) {
			return true;
		}
		return false;
	}
	
	@Step("navigating to the register page...")
	public RegisterPage navigateToRegisterPage() {
		log.info("trying to navigating to register page...");
		eleUtil.waitForElementVisible(registerLink, AppConstants.DEFAULT_SHORT_WAIT).click();
		return new RegisterPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
