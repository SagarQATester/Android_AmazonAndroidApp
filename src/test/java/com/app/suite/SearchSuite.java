package com.app.suite;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.app.allureReports.BaseClass;
import com.app.pages.HomePage;
import com.app.pages.LoginPage;
import com.app.pages.MenuPage;
import com.app.pages.SearchPage;
import com.app.reusableComponents.ReadExcelSheetData;

public class SearchSuite extends BaseClass {
	
	@Test(groups={"Regression","SearchScreen","TC_003","ToBeExecutedAlways"},priority=1,enabled=true,description="TC_003: Correct search results should show up for different types such as product name, brand name, or fuzzy search.")
	public void TC_001() throws Exception
	{
		try {
			Map<String, Map<String, String>> excelFileMap=ReadExcelSheetData.setMapData("SearchScreen","TC_003");
			Map<String, String> dataMap=excelFileMap.get("TC_003");
			final String mobileNumber=dataMap.get("mobileNumber").replace("no", "");
			final String productName=dataMap.get("productName");
			final String brandName=dataMap.get("brandName");
			
			LoginPage loginpage=new LoginPage(driver);
			loginpage.login(mobileNumber);
			HomePage homepage=new HomePage(driver);
			homepage.SearchProduct(productName);
			SearchPage searchpage=new SearchPage(driver);
			Thread.sleep(10000);
			Assert.assertTrue(searchpage.verifySearchByProductName(productName), "User was not able to Search Product By Product Name");
			homepage.SearchProduct(brandName);
			Thread.sleep(10000);
			Assert.assertTrue(searchpage.verifySearchByProductBrandName(brandName), "User was not able to Search Product By Brand Name");
			homepage.clickOnTabMenu();
			MenuPage menupage=new MenuPage(driver);  
			menupage.logout();
			
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("User was not able to Search Product by Product Name and Brand Name in Amazon");
		}
	}


}
