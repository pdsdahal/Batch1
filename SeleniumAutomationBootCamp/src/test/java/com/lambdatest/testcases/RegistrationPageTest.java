package com.lambdatest.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lambdatest.driver.BrowserDriver;
import com.lambdatest.pages.HeaderPage;
import com.lambdatest.pages.RegistrationPage;
import com.lambdatest.util.TestUtil;

public class RegistrationPageTest {

	HeaderPage headerPage;
	RegistrationPage registrationPage;

	@BeforeMethod
	public void setUp() {
		BrowserDriver.start();
		headerPage = new HeaderPage();
		headerPage.validateMouseHoverMyAccountMenu();
		registrationPage = headerPage.verifyClickOnRegister();
	}

	@Test(priority = 1)
	public void verifyTitleOfRegistrationPage() {
		String actualTitleName = registrationPage.verifyTitle();
		Assert.assertEquals(actualTitleName, "Register Account", "Title Name in Register page is not matched");
	}

	@Test(priority = 2)
	public void verifyAllRegisterionWebElementArePresent() {
		Assert.assertEquals(registrationPage.verifyHeaderIsVisible(), true, "Header is not visible");
		Assert.assertEquals(registrationPage.verifyRegisterParaIsVisible(), true, "Para.. is not visible");
	}

	@Test(priority = 3)
	public void verifyAllLableTextValueDisplayCorrectly() {
		Assert.assertEquals(registrationPage.verifyHeaderText(), "Register Account", "Header text is not matched");
		
		String expected = "If you already have an account with us, please login at the\n"
				+ "login page\n"
				+ ".";
		Assert.assertEquals(registrationPage.verifyParagrText(),expected,"Header Parag. is not matched");
	}
	
	@DataProvider
	public Object[][]  getTestDataForRegister() {
		Object[][]  testData = TestUtil.readTestData("register");
		return testData;
	}
	
	@Test(priority = 4, dataProvider = "getTestDataForRegister")
	public void verifyRegisterUser(String firstname, String lastname, String email, String telephone, String password, String configpassword) {
		registrationPage.registerUser(firstname, lastname, email);
	}
	
	
	@AfterMethod
	public void tearDown() {
		BrowserDriver.stop();
	}

}
