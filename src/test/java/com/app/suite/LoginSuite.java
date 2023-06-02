package com.app.suite;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.app.allureReports.BaseClass;
import com.app.pages.HomePage;
import com.app.pages.LoginPage;
import com.app.pages.MenuPage;
import com.app.reusableComponents.ReadExcelSheetData;

public class LoginSuite extends BaseClass {
	
	@Test(groups={"Regression","LoginScreen","TC_001","ToBeExecutedAlways"},priority=1,enabled=true,description="TC_001: To check the User Login Amazon Application"
			+ "\n\n\n TC_002: To check the User Logout Amazon Application")
	public void TC_001() throws Exception
	{
		try {
			Map<String, Map<String, String>> excelFileMap=ReadExcelSheetData.setMapData("LoginScreen","TC_001");
			Map<String, String> dataMap=excelFileMap.get("TC_001");
			final String mobileNumber=dataMap.get("mobileNumber").replace("no", "");

			LoginPage loginpage=new LoginPage(driver);
			loginpage.login(mobileNumber);
			Thread.sleep(10000);
			HomePage homepage=new HomePage(driver);
			//Verify Login Successful message
			Assert.assertTrue(homepage.verifyLoginSuccessFul(), "User was not able to login Successfully");
			homepage.clickOnTabMenu();
			MenuPage menupage=new MenuPage(driver);
			menupage.logout();
			//Verify Logout Successful message
			Assert.assertTrue(loginpage.verifyLogOutSuccessFul(), "User was not able to logout Successfully");
			
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("User was not able to login and logout in Amazon");
		}
	}
}
