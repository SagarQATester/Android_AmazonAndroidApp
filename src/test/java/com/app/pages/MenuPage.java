package com.app.pages;

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

public class MenuPage extends BaseClass {
	
	//Find the WebElements using Locators
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='c_se']")
	WebElement btnSettings;
	
	@AndroidFindBy(xpath="//*[@text='Sign Out']")
	WebElement viewSignOut;
	
	@AndroidFindBy(id="android:id/button2")
	WebElement btnSignOut;
	
	CommonMethods common=new CommonMethods();

	/*
	 * Initialize and Load the Home Page
	 */
	public MenuPage(AndroidDriver<WebElement> driver) 
	{
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * Click On Settings Dropdown
	 */
	@Step("Click On Dropdown Settings")
	public void clickOnDrpSettings()
	{
		try {
			common.scrollDown(driver,2, 115,1814,115,290);
			CommonMethods.waitForElement(driver, "Settings", btnSettings);
			btnSettings.click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to Click On Settings Dropdown");
			Assert.fail("Not able to Click On Settings Dropdown");
		}
	}
	
	/**
	 * Click On Sign Out
	 */
	@Step("Click On Sign Out")
	public void clickOnSignOut()
	{
		try {
			common.scrollDown(driver, 1, 115, 1814, 115, 290);
			CommonMethods.waitForElement(driver, "Sign Out", viewSignOut);
			viewSignOut.click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to Click On Sign Out");
			Assert.fail("Not able to Click On Sign Out");
		}
	}
	
	/**
	 * Click On Sign Out Button
	 */
	@Step("Click On Sign Out Button")
	public void clickOnBtnSignOut()
	{
		try {
			CommonMethods.waitForElement(driver, "Sign Out Button", btnSignOut);
			btnSignOut.click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to Click On Sign Out Button");
			Assert.fail("Not able to Click On Sign Out Button");
		}
	}

	public void logout()
	{
		clickOnDrpSettings();
		clickOnSignOut();
		clickOnBtnSignOut();
	}
}
