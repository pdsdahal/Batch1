package com.lambdatest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.lambdatest.driver.BrowserDriver;
import com.lambdatest.util.TestUtil;

public class HeaderPage {

	private WebDriver driver;

	@FindBy(how = How.LINK_TEXT, using = "Home")
	private WebElement webElmHome;

	private WebElement webElmSpecial;
	private WebElement webElmBlog;

	@FindBy(how = How.LINK_TEXT, using = "Mega Menu")
	private WebElement webElmMegaMenu;

	private WebElement webElmAddOns;

	@FindBy(how = How.LINK_TEXT, using = "My account")
	private WebElement webElmMyAccount;
	
	@FindBy(how = How.LINK_TEXT, using="Login")
	private WebElement webElmLogin;

	@FindBy(how = How.LINK_TEXT, using="Register")
	private WebElement webElmRegister;
	

	public HeaderPage() {
		driver = BrowserDriver.getWebDriver();
		PageFactory.initElements(driver, this);
	}

	public String validateMyAccountText() {
		return webElmMyAccount.getText();
	}

	public boolean validateMyAccountMenuIsVisibile() {
		return webElmMyAccount.isDisplayed();
	}

	public void validateMouseHoverMyAccountMenu() {
		TestUtil.hoverMenuItem(webElmMyAccount);		
	}
	
	public boolean verifyLoginMenuIsVisible() {
		return webElmLogin.isDisplayed();
	}
	
	public boolean verifyRegisterMenuIsVisible() {
		return webElmRegister.isDisplayed();
	}
	
	public RegistrationPage verifyClickOnRegister() {
		webElmRegister.click();
		return new RegistrationPage();
	}
	
	

}
