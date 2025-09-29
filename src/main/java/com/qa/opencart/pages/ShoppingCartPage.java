package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class ShoppingCartPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private static final Logger log = LogManager.getLogger(LoginPage.class);

	
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

}
