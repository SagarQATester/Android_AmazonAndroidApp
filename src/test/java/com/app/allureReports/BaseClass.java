package com.app.allureReports;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseClass {
	public AndroidDriver<WebElement> driver;
	public static ThreadLocal<AndroidDriver<WebElement>> tdriver = new ThreadLocal<AndroidDriver<WebElement>>();

	public synchronized static AndroidDriver<WebElement> getDriver() {
		return tdriver.get();
	}
	@BeforeTest
	public void setup() throws InterruptedException
	{
		try {
			DesiredCapabilities caps=new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "RMX2151");
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12");
			caps.setCapability(MobileCapabilityType.NO_RESET, true);
			caps.setCapability(MobileCapabilityType.UDID, "YXXCNRGQYHXG5LOR");
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
			//caps.setCapability(MobileCapabilityType.APP, "C:\\Users\\RAHUL\\workspace\\AmazonAndroidApp\\src\\main\\resources\\apps\\Amazon.apk");
		//	caps.setCapability("appPackage", "com.google.android.apps.messaging");
		//	caps.setCapability("appActivity", "com.google.android.apps.messaging.ui.ConversationListActivity");
			
			driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),caps);
			Thread.sleep(5000);
			
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
	           String sessionID = driver.getSessionId().toString();
	            System.out.println("Session ID: " + sessionID);


		} catch (MalformedURLException e) {

			e.printStackTrace();
		}	
	}


	@AfterTest
	public void teardown()
	{
		//driver.quit();
	}
}
