package com.lambdatest.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lambdatest.driver.BrowserDriver;
import com.lambdatest.pages.HeaderPage;
import com.lambdatest.pages.RegistrationPage;

public class HeaderPageTest {

	HeaderPage headerPage;
	RegistrationPage registrationPage;
	
	@BeforeMethod
	public void setUp() {
		BrowserDriver.start();
		headerPage = new HeaderPage();
	}
	
	
	@Test(priority = 1)
	public void validateMyAccountMenu() {
		boolean actualResult = headerPage.validateMyAccountMenuIsVisibile();
		Assert.assertEquals(actualResult, true, "My Account is not visible.");
	}
	
	@Test(priority = 2)
	public void verfiyMyAccountLabel() {
		String actualText = headerPage.validateMyAccountText();
		Assert.assertEquals(actualText, "My account", "My account labled is not matching.");
	}
	
	@Test(priority = 3)
	public void validateHoverOverMyAccountMenuAndSubMenu() {
		headerPage.validateMouseHoverMyAccountMenu();
		boolean actualLogin =  headerPage.verifyLoginMenuIsVisible();
		boolean actualRegister =  headerPage.verifyRegisterMenuIsVisible();
		Assert.assertEquals(actualLogin, true , "Login is not displayed.");
		Assert.assertEquals(actualRegister, true,"Register is not displayed.");
		registrationPage  = headerPage.verifyClickOnRegister();
	}
	
	
	@AfterMethod
	public void tearDown() {
		BrowserDriver.stop();
	}
	
}
