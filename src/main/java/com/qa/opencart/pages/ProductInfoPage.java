package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productMap;
	private static final Logger log = LogManager.getLogger(ProductInfoPage.class);	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By header = By.tagName("h1");
	private final By productImages= By.cssSelector("div#content img");
	private final By quantity = By.id("input-quantity");
	private final By addToCartBtn = By.id("button-cart");
	private final By shoppingCartLink = By.linkText("shopping cart");
	private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	
	
	public String getProductHeader() {
		String headerVal = eleUtil.waitForElementVisible(header, AppConstants.DEFAULT_SHORT_WAIT).getText();
		//System.out.println("product header is--> "+ headerVal);
		log.info("product header is--> "+ headerVal);
		return headerVal;
	}
	
	public int getProductImages() {
		int imagesCount = eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_WAIT).size();
		//System.out.println("total number of images : "+imagesCount);
		log.info("total number of images : "+imagesCount);
		return imagesCount;
	}
	
//	public ShoppingCartPage doEnterQuantity(int qtySize) {
//		WebElement productQty = eleUtil.waitForElementVisible(quantity, AppConstants.DEFAULT_MEDIUM_WAIT);
//		productQty.clear();
//		productQty.sendKeys(String.valueOf(qtySize));
//		eleUtil.doClick(addToCartBtn);
//		eleUtil.waitForElementVisible(shoppingCartLink, AppConstants.DEFAULT_MEDIUM_WAIT).click();
//		return new ShoppingCartPage(driver);
//		
//	}
	
	public Map<String, String> getProductData() {
		//productMap = new HashMap<String, String>(); //[] and does not maintain the order
		//productMap = new LinkedHashMap<String, String>();
		//to maintain the order use LinkedHashMap
		
		productMap = new TreeMap<String, String>(); //sorted order with the keys
		
		productMap.put("productname", getProductHeader());
		productMap.put("productimages", String.valueOf(getProductImages()));
		
		getProductMetaData();
		getProductPriceData();
		
		//System.out.println("======Product Data=======: \n"+ productMap);
		log.info("======Product Data=======: \n"+ productMap);
		return productMap;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.waitForElementsVisible(productMetaData, AppConstants.DEFAULT_SHORT_WAIT);
		//System.out.println("total meta data: "+ metaList.size());
		log.info("total meta data: "+ metaList.size());
		
		for(WebElement e : metaList) {
			String metaData = e.getText();
			String meta[] = metaData.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}
	
	
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.waitForElementsVisible(productPriceData, AppConstants.DEFAULT_SHORT_WAIT);
		//System.out.println("total price data: "+ priceList.size());
		log.info("total price data: "+ priceList.size());
		
		String priceValue = priceList.get(0).getText();
		String exTaxValue = priceList.get(1).getText().split(":")[1].trim();
		
		productMap.put("productprice", priceValue);
		productMap.put("extaxprice", exTaxValue);
		
		
		}
}
	
