package com.app.reusableComponents;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.app.allureReports.AllureListener;
import com.app.allureReports.BaseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;


public class CommonMethods {

	static AndroidDriver<WebElement> driver;
	public static int MaxElementWait=60;



	public CommonMethods() {
		CommonMethods.driver=BaseClass.getDriver();
	}

	/**
	 * To wait for the specific element on the page if element is visible or Enabled 
	 * 
	 * @param androidDriver -
	 * @param element - webelement to wait for to appear
	 * @param maxElementWait2 - how long to wait for
	 * @return boolean - return true if element is present else return false
	 */
	public static boolean waitForElement(AndroidDriver<WebElement> androidDriver,String fieldName, WebElement element, int maxElementWait2) {
		boolean statusOfElementToBeReturned = false;
		WebDriverWait wait = new WebDriverWait(androidDriver, maxElementWait2);
		try {
			WebElement waitElement = wait.until(ExpectedConditions.visibilityOf(element));
			if (waitElement.isDisplayed() && waitElement.isEnabled()) {
				statusOfElementToBeReturned = true;
			}
		} catch (Exception ex) {
			statusOfElementToBeReturned = false;
			System.out.println(fieldName+ " not found");
		}
		return statusOfElementToBeReturned;
	}

	/**waitForPageLoad waits for the page load with custom page load wait time 
	 * @param driver
	 * @param element
	 * 				as element which is displayed on page
	 * 			
	 */
	public static void waitForPageLoad(final AppiumDriver<WebElement> driver,WebElement element) {
		FluentWait<WebDriver> wait = new WebDriverWait(driver, MaxElementWait)
				.pollingEvery(Duration.ofMillis(1000))
				.ignoring(StaleElementReferenceException.class)
				.withMessage("Page Load Timed Out");
		try {

			wait.until(ExpectedConditions.visibilityOf(element));

			String title = driver.getTitle().toLowerCase();
			String url = driver.getCurrentUrl().toLowerCase();

			if ("the page cannot be found".equalsIgnoreCase(title)
					|| title.contains("is not available")
					|| url.contains("/error/")
					|| url.toLowerCase().contains("/errorpage/")
					||driver.getPageSource().contains("No page was found for the web address")) {
				Assert.fail("Page is Not loaded. [Title: " + title + ", URL:" + url
						+ "]");
			}
		} catch (TimeoutException e) {
			driver.navigate().refresh();
			wait.until(ExpectedConditions.visibilityOf(element));
		}

	} 

	/**
	 * To wait for the specific element on the page as per configured Max wait
	 * 
	 * @param androidDriver -
	 * @param element - webelement to wait for to appear
	 * @param maxWait - how long to wait for
	 * @return boolean - return true if element is present else return false
	 */
	public static void waitForElement(AndroidDriver<WebElement> androidDriver,String fieldName,WebElement element) {
		waitForElement(androidDriver, fieldName, element, MaxElementWait);
	}

	/**
	 * To wait for the specific element on the page if element is CLickable
	 * 
	 * @param driver -
	 * @param element - webelement to wait for to appear
	 * @param maxWait - how long to wait for
	 * @return boolean - return true if element is present else return false
	 */
	public static boolean waitForElementToBeClickable(WebDriver driver,String fieldName, WebElement element) {
		boolean statusOfElementToBeReturned = false;
		WebDriverWait wait = new WebDriverWait(driver, MaxElementWait);
		try {
			WebElement waitElement = wait.until(ExpectedConditions.elementToBeClickable(element));
			if (waitElement.isDisplayed() && waitElement.isEnabled()) {
				statusOfElementToBeReturned = true;
			}
		} catch (Exception ex) {
			statusOfElementToBeReturned = false;
			System.out.println(fieldName+ " not Clickable");
		}
		return statusOfElementToBeReturned;
	}

	//custom click method to log evey click action in to extent report
	public static void click_custom(WebElement element, String fieldName) {
		try {
			waitForElementToBeClickable(BaseClass.getDriver(), fieldName, element);
			waitForElement(BaseClass.getDriver(), fieldName, element);
			element.click();
		}catch(ElementClickInterceptedException e1)
		{
			waitForElementToBeClickable(BaseClass.getDriver(), fieldName, element);
			element.click();
		}
		catch (Exception e) {
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to Click on "+fieldName);
			Assert.fail("Not able to Click on "+fieldName);
		}
	}

	//Customized sendkeys method-> To log sendkeys message for every occ.
	public static void sendKeys_custom(WebElement element, String fieldName, String valueToBeSent) {
		try {
			waitForElement(BaseClass.getDriver(), fieldName, element);
			element.sendKeys(valueToBeSent);
		}catch(ElementNotInteractableException e1) {
			waitForElement(BaseClass.getDriver(), fieldName, element);
			element.sendKeys(valueToBeSent);
		}
		catch (Exception e) {
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to enter text "+valueToBeSent+" in "+fieldName);
			Assert.fail("Not able to enter text "+valueToBeSent+" in "+fieldName);
		}
	}



	//clear data from field
	public static void clear_custom(WebElement element,String fieldName) {
		element.clear();
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to clear "+fieldName);
		}
	}

	//custom mouseHover 
	public void moveToElement_custom(WebElement element,String fieldName){

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
		Actions actions = new Actions(driver);		
		actions.moveToElement(element).build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void selectByVisibleTextById(List<WebElement> element,String text)
	{
		System.out.println("Total number of options available in dropdown:"+element.size());

		for(WebElement e:element)
		{
			String val=e.getText();
			if(val.equalsIgnoreCase(text))
			{
				e.click();
				break;
			}
		}
	}
	
	public static void selectRadiobutton(List<WebElement> element,String value)
	{
		for (WebElement ref : element)
		{
			if(ref.getText().equalsIgnoreCase(value))
			{
				ref.click();
				break;
			}

		}

	}
	
	public WebElement scrollByDescription(AppiumDriver<WebElement> driver,String menuText) {

     return driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"" + menuText + "\").instance(0));")); 
	}

	
	public WebElement scrollByText(AppiumDriver<WebElement> driver,String menuText) {

	     return driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"" + menuText + "\").instance(1));")); 
		}
	
	/**
	 * Scroll Down The Page
	 */
	@Step("Scroll Down The Page")
	public void scrollDown(AppiumDriver<WebElement> driver,int count,int startx,int starty,int endx,int endy)
	{
		try {
			for(int i=0;i<=count;i++)
			{
			TouchAction action = new TouchAction(driver); 
            action.press(PointOption.point(startx, starty))
            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
            .moveTo(PointOption.point(endx,endy)).release().perform();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to Scroll Down the page");
			Assert.fail("Not able to Scroll Down the page");
		}
	}

}
