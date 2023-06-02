package com.app.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.allureReports.AllureListener;
import com.app.allureReports.BaseClass;
import com.app.reusableComponents.CommonMethods;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;

public class HomePage extends BaseClass {

	//Find the WebElements using Locators

	@AndroidFindBy(accessibility="Tap to Edit Your Profile")
	WebElement iconProfile;
	
	@AndroidFindBy(accessibility="Menu. Contains your orders, your account, shop by department, programmes and features, settings and customer service Tab 4 of 4")
	WebElement tabMenu;
	
	@AndroidFindBy(id="in.amazon.mShop.android.shopping:id/chrome_search_box")
	WebElement txtSearchBox;
	
	@AndroidFindBy(id="in.amazon.mShop.android.shopping:id/rs_search_src_text")
	WebElement txtSearch;
	
	CommonMethods common=new CommonMethods();

	/*
	 * Initialize and Load the Home Page
	 */
	public HomePage(AndroidDriver<WebElement> driver) 
	{
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**Verify Login Successful
	 * 
	 */
	@Step("Verify Login Successful")
	public boolean verifyLoginSuccessFul()
	{	
		AllureListener allureListner=new AllureListener();
		boolean flag=false;
		try
		{	
			Thread.sleep(5000);
			if(txtSearchBox.isDisplayed()|| iconProfile.isDisplayed())
			{
				flag=true;
				allureListner.saveScreenShot(driver);
				AllureListener.saveTextLog("User is login suceessfully");
				Assert.assertEquals(flag, true,"User is login suceessfully");
			}else
			{
				flag=false;
				allureListner.saveScreenShot(driver);
				AllureListener.saveTextLog("User is not login suceessfully");
				Assert.fail("User is not login suceessfully");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			allureListner.saveScreenShot(driver);
			AllureListener.saveTextLog("User is not login suceessfully");
			Assert.fail("User is not login suceessfully");
		}

		return flag;
	}
	
	/**
	 * Click On Menu Tab
	 */
	@Step("Click On Menu Tab")
	public void clickOnTabMenu()
	{
		try {
			CommonMethods.waitForElement(driver, "Menu Tab", tabMenu);
			tabMenu.click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to Click On Menu Tab");
			Assert.fail("Not able to Click On Menu Tab");
		}
	}
	
	/**Enter Search Text
	 * @param text
	 * 			as String
	 */			
	@Step("Enter Search Text :text[0]")
	public void enterSearchText(String text)
	{
		try {
			CommonMethods.waitForElement(driver, "Search Text", txtSearch);
			txtSearch.clear();
			txtSearch.sendKeys(text);
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));		
			}
		catch (Exception e) {
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to enter Search text");
			Assert.fail("Not able to enter Search text");
		}
	}
	
	//Click on Search Box
		@Step("Click on Search Box")
		public void clickOnSearchBox()
		{
			try {
				CommonMethods.waitForElement(driver, "Search Box", txtSearchBox);
				txtSearchBox.click();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Not able to click on search Box");
			}
		}
	
	//Search Product
		@Step("Search Product")
		public void SearchProduct(String productName)
		{
			try {
				clickOnSearchBox();
				enterSearchText(productName);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Not able to Search a Product");
			}
		}

}
