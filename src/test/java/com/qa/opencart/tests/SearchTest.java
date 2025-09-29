package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchTest extends BaseTest{
	
	@BeforeClass
	public void searchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	
	@DataProvider
	public Object[][] getProducts()
	{
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"imac", "iMac"},
			{"canon", "Canon EOS 5D"}
		};
	}
	@Test(dataProvider = "getProducts")
	public void searchTest(String searchKey, String productName) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		String actHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, productName);
	}
	
	
	

}
