package com.app.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.allureReports.AllureListener;
import com.app.allureReports.BaseClass;
import com.app.reusableComponents.CommonMethods;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;

public class SearchPage extends BaseClass {
	
	//Find the WebElements using Locators
	
	@AndroidFindBy(xpath="//android.view.View[@resource-id='search']/android.view.View[4]/android.view.View[2]/android.view.View")
	WebElement searchProductName;
	
	@AndroidFindBy(xpath="//android.view.View[@resource-id='search']/android.view.View[4]/android.view.View[2]/android.view.View")
	WebElement searchBrandName;
		
	CommonMethods common=new CommonMethods();
	AllureListener allureListner=new AllureListener();
	
	/*
	 * Initialize and Load the Home Page
	 */
	public SearchPage(AndroidDriver<WebElement> driver) 
	{
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/**Verify Search Product Name
	 * 
	 */
	@Step("Verify Search Product Name")
	public boolean verifySearchByProductName(String productName)
	{	
		boolean flag=false;
		try
		{
			Thread.sleep(10000);
			common.scrollDown(driver, 0, 115, 1637, 115, 450);
			String product_name[]=productName.split(" ");
			String product_Name=searchProductName.getAttribute("content-desc");
			System.out.println("product_Name"+product_Name);
			System.out.println("productName"+productName);
			AllureListener.saveTextLog("Expected Product Name: "+productName);
			AllureListener.saveTextLog("Actual Product Name: "+product_Name);

			if(product_Name.equalsIgnoreCase(productName)||product_Name.contains(product_name[0])||product_Name.contains(product_name[1]))
			{
				flag=true;
				allureListner.saveScreenShot(driver);
				AllureListener.saveTextLog("User is search product by product name successfully ");
				Assert.assertEquals(flag, true,"User is search product by product name successfully ");
			}else
			{
				flag=false;
				allureListner.saveScreenShot(driver);
				AllureListener.saveTextLog("User is not search product by product name successfully ");
				Assert.fail("User is not search product by product name successfully ");
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			allureListner.saveScreenShot(driver);
			AllureListener.saveTextLog("User is not search product by product name successfully ");
			Assert.fail("User is not search product by product name successfully ");
		}
		return flag;	
	}


	/**Verify Search By Product Brand Name
	 * 
	 */
	@Step("Verify Search By Product Brand Name")
	public boolean verifySearchByProductBrandName(String productBrandName)
	{	
		boolean flag=false;
		try
		{
			Thread.sleep(10000);
			common.scrollDown(driver, 0, 115, 1400, 115, 422);
			String product_Brand_Name=searchBrandName.getAttribute("content-desc");
			System.out.println("product with brand name"+product_Brand_Name);
			System.out.println("product brand"+productBrandName);
			AllureListener.saveTextLog("Expected Product Brand Name: "+product_Brand_Name);
			AllureListener.saveTextLog("Actual Product Brand Name: "+productBrandName);

			if(product_Brand_Name.contains(productBrandName))
			{
				flag=true;
				allureListner.saveScreenShot(driver);
				AllureListener.saveTextLog("User is search product by Brand name successfully ");
				Assert.assertEquals(flag, true,"User is search product by Brand name successfully ");
			}else
			{
				flag=false;
				allureListner.saveScreenShot(driver);
				AllureListener.saveTextLog("User is not search product by Brand name successfully ");
				Assert.fail("User is not search product by Brand name successfully ");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			allureListner.saveScreenShot(driver);
			AllureListener.saveTextLog("User is not search product by Brand name successfully ");
			Assert.fail("User is not search product by Brand name successfully ");
		}
		return flag;	
	}



}
