package com.app.suite;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.app.allureReports.BaseClass;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;


public class ReadOTP  extends BaseClass {

	@Test
	public void readOTP() throws InterruptedException
	
	{
	
		
		
		
		// driver.findElement(By.xpath("//*[@text='Use Messages without an account']")).click();
		    
		    Thread.sleep(3000);
//Dear ROHIT LAXMAN JANGAM,Your One Time Password (OTP) to login to IDFC MF website is 874222.This OTP will be valid for next 5 mins.Regards,		    IDFCMF
		    System.out.println("Tets 1");
		   
		    try {
				((AndroidDriver<WebElement>) driver).openNotifications();

		    	//driver.pressKeyCode(AndroidKeyCode.KEYCODE_NOTIFICATION, AndroidKeyMetastate.META_STATE_ON);
		    	
			} catch (Exception e) {
			
				 e.printStackTrace();
			}


			System.out.println("Tets case passed");
			
			 WebElement otpElement =driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/action0']"));
			 String otpMessage = otpElement.getText();
			 System.out.println("OTP message: "+otpMessage);
			 String otp = otpMessage.replaceAll("[^0-9]", ""); // Extract only the numeric digits from the message
			  System.out.println("OTP: " + otp);
		
			/*
		    
		//WebElement otpElement = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"OTP\")"));
	 WebElement otpElement = driver.findElement(By.xpath("//*[contains(text(),'Dear ROHIT LAXMAN JANGAM')]"));
		
		  String otpMessage = otpElement.getText();
		  System.out.println(otpMessage);
		  
		  String otp = otpMessage.replaceAll("[^0-9]", ""); // Extract only the numeric digits from the message
		  System.out.println("OTP: " + otp);
		  
		 */
	
	}
}
