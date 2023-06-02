package com.app.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.allureReports.AllureListener;
import com.app.allureReports.BaseClass;
import com.app.reusableComponents.CommonMethods;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;

public class LoginPage extends BaseClass {
	
	//Find the WebElements using Locators
	
	@AndroidFindBy(id="in.amazon.mShop.android.shopping:id/sign_in_button")
	WebElement btnSignIn;
	
	@AndroidFindBy(id="in.amazon.mShop.android.shopping:id/continue_button")
	WebElement btnContinueInEnglish;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='ap_email_login']")
	WebElement txtEmailOrMobileNo;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='Continue']")
	WebElement btnContinue;
	
	@AndroidFindBy(accessibility="sib")
	WebElement viewSignIn;
	
	@AndroidFindBy(accessibility="Select English")
	WebElement viewSelectEnglish;
	
	@AndroidFindBy(accessibility="Your Amazon.com Tab 2 of 4")
	WebElement iconProfileTab;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='cvf-input-code']")
	WebElement txtOtp;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'is your Amazon OTP') and @index='0']")
	WebElement otpElemnt;
	
	@AndroidFindBy(accessibility="Clear all notifications.")
	WebElement btnClearNotifications;
	
	CommonMethods common=new CommonMethods();

	/*
	 * Initialize and Load the Login Page
	 */
	public LoginPage(AndroidDriver<WebElement> driver) 
	{
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/**Login to Amazon
	 * @param username 
	 * 				 as String
	 */
	@Step("User Enter username : [0]")
	public void login(String numberOrEmail)
	{	
	try {
		clickOnTextViewLogin();
		if(numberOrEmail.matches("\\d{10}") && numberOrEmail.matches("(0/91)?[7-9][0-9]{9}") )
		{
			enterMobileNumber(numberOrEmail);
		}
		else {
			enterEmailAddress(numberOrEmail);
		}
		clickBtnContinue();
		String otp=getOtp();
		enterOTP(otp);
		clickBtnContinue();
		
	} catch (Exception e) {
		e.printStackTrace();
		AllureListener.saveTextLog("Not able to login");
		Assert.fail("Not able to login");
	}
}
	
	/**Enter Mobile Number 
	 * @param mobileNumber
	 * 			as String
	 */			
	@Step("Enter Mobile Number :mobileNumber[0]")
	public void enterMobileNumber(String mobileNumber)
	{
		try {
			CommonMethods.waitForElement(driver, "Mobile Number", txtEmailOrMobileNo);
			txtEmailOrMobileNo.sendKeys(mobileNumber);
		}
		catch (Exception e) {
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to enter Mobile Number");
			Assert.fail("Not able to enter Mobile Number");
		}
	}
	
	/**Enter Email Address
	 * @param emailAdd
	 * 			as String
	 */			
	@Step("Enter Email Address :emailAdd[0]")
	public void enterEmailAddress(String emailAdd)
	{
		try {
			CommonMethods.waitForElement(driver, "Email Address", txtEmailOrMobileNo);
			txtEmailOrMobileNo.sendKeys(emailAdd);
		}catch (Exception e) {
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to enter Email Address");
			Assert.fail("Not able to enter Email Address");
		}
		
	}
	/**
	 * Click Continue Button
	 */
	@Step("Click On Continue Button")
	public void clickBtnContinue()
	{
		try {
			CommonMethods.waitForElement(driver, "Continue Button", btnContinue);
			btnContinue.click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to click Continue Button");
			Assert.fail("Not able to click Continue Button");
		}
	}

	//Click on Login Textview or Profile icon
	@Step("Click on Login Textview or Profile icon")
	public void clickOnTextViewLogin() throws InterruptedException
	{	try 
		{
		if(viewSelectEnglish.isDisplayed())
		{
			viewSelectEnglish.click();
			btnContinueInEnglish.click();
			btnSignIn.click();
		} 	
		}
		catch(Exception e){
			clickBtnLogin();
		}
	}
	
	//Click on Button Login
	@Step("Click on Button Login")
	public void clickBtnLogin() throws InterruptedException
	{	try 
		{
		if(btnSignIn.isDisplayed())
		{
			btnSignIn.click();
		} 	
		}
		catch(Exception e){
			iconProfileTab.click();
			viewSignIn.click();
		}
	}
	
	/**Enter OTP 
	 * @param otp
	 * 			as String
	 */			
	@Step("Enter OTP :otp[0]")
	public void enterOTP(String otp)
	{
		try {
			CommonMethods.waitForElement(driver, "OTP", txtOtp);
			txtOtp.sendKeys(otp);
		}
		catch (Exception e) {
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to enter OTP");
			Assert.fail("Not able to enter OTP");
		}
	}
	
	/**Get OTP 
	 * @throws InterruptedException 
	 * 
	 */			
	@Step("Get OTP")
	public String getOtp() throws InterruptedException
	{ 	
			Thread.sleep(1000);
			((AndroidDriver<WebElement>) driver).openNotifications();
			CommonMethods.waitForElement(driver, "OTP Message", otpElemnt);
			String otpMessage=otpElemnt.getText();
			String OTP[]=otpMessage.split(" ");
			System.out.println("OTP is : "+OTP[0]);
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));
			return OTP[0];
	}
	
	/**Clear Notification Bar
	 * @throws InterruptedException 
	 * 
	 */			
	@Step("Clear Notification Bar")
	public void clearNotification() throws InterruptedException
	{ 	
			((AndroidDriver<WebElement>) driver).openNotifications();
			CommonMethods.waitForElement(driver, "Clear Notification", btnClearNotifications);
			btnClearNotifications.click();
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		
	}
	
	/**Verify Logout Successful
	 * 
	 */
	@Step("Verify Logout Successful")
	public boolean verifyLogOutSuccessFul()
	{	
		AllureListener allureListner=new AllureListener();
		boolean flag=false;
		try
		{	
			Thread.sleep(5000);
			if(btnSignIn.isDisplayed())
			{
				flag=true;
				allureListner.saveScreenShot(driver);
				AllureListener.saveTextLog("User is logout suceessfully");
				Assert.assertEquals(flag, true,"User is logout suceessfully");
			}else
			{
				flag=false;
				allureListner.saveScreenShot(driver);
				AllureListener.saveTextLog("User is not logout suceessfully");
				Assert.fail("User is not logout suceessfully");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			allureListner.saveScreenShot(driver);
			AllureListener.saveTextLog("User is not logout suceessfully");
			Assert.fail("User is not logout suceessfully");
		}
		return flag;	
	}

}
