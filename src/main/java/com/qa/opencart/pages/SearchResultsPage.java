package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private static final Logger log = LogManager.getLogger(SearchResultsPage.class);	
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By searchResults = By.cssSelector("div.product-thumb");
	private final By resultHeader = By.cssSelector("div#content h1");
	
	public int getsearchResultsCount() {
		int resultCount = eleUtil.waitForElementsPresence(searchResults, AppConstants.DEFAULT_MEDIUM_WAIT).size();
		//System.out.println("results count ---> "+resultCount);
		log.info("results count ---> "+resultCount);
		return resultCount;
	}
	
	public String getResultsHeaderValue() {
		String header = eleUtil.doElementGetText(resultHeader);
		//System.out.println("results header --> "+ header);
		log.info("results header --> "+ header);
		return header;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		//System.out.println("product name---> "+productName);
		log.info("product name---> "+productName);
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}

}
