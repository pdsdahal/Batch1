package com.lambdatest.pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lambdatest.driver.BrowserDriver;
import com.lambdatest.util.TestUtil;

public class RegistrationPage {

	WebDriver driver;

	@FindBy(how = How.TAG_NAME, using = "h1")
	private WebElement webElmRegisterHeader;

	@FindBy(how = How.CSS, using = "div#content>p")
	private WebElement webElmRegisterParaLabel;

	@FindBy(how = How.CSS, using = "fieldset#account>legend")
	private WebElement webElmPersonalDetail;

	@FindBy(how = How.XPATH, using = "label[for='input-firstname']")
	private WebElement webElmLableFirstName;

	@FindBy(how = How.ID, using = "input-firstname")
	private WebElement webElmTextFieldFirstName;
	
	@FindBy(how = How.ID, using = "input-lastname")
	private WebElement webElmTextFieldLastName;
	
	
	@FindBy(how = How.ID, using = "input-email")
	private WebElement webElmTextFieldEmail;
	
	

	public RegistrationPage() {
		driver = BrowserDriver.getWebDriver();
		PageFactory.initElements(driver, this);
	}
	
	
	public String verifyTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyHeaderIsVisible() {
		
		//Explict Wait
		
		//WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//WebElement waitForWebElementRegHe = driverWait.until(ExpectedConditions.visibilityOf(webElmRegisterHeader));
		
		//Explicit Wait
		WebElement waitForWebElementRegHe = TestUtil.waitForElementExplict(ExpectedConditions.visibilityOf(webElmRegisterHeader), driver, 2);
		waitForWebElementRegHe.isDisplayed();
			
		//Fluent Wait
		WebElement element =  TestUtil.waitForElementFluent(By.tagName("h1"), driver, 30, 3);
		element.isDisplayed();
		
		return webElmRegisterHeader.isDisplayed();
	}
	
	public boolean verifyRegisterParaIsVisible() {
		return webElmRegisterParaLabel.isDisplayed();
	}
	
	
	public String verifyHeaderText() {
		
		TestUtil.waitForElementExplict(ExpectedConditions.textToBePresentInElementValue(webElmRegisterHeader,""), driver, 2);
		//return waitForWebElementRegHe.isDisplayed();
		return webElmRegisterHeader.getText();
		
		//return webElmRegisterHeader.getText();
	}
	
	public String verifyParagrText() {
		return webElmRegisterParaLabel.getText();
	}
	
	public void registerUser(String firstname, String lastName, String email) {
		webElmTextFieldFirstName.sendKeys(firstname);
		webElmTextFieldLastName.sendKeys(lastName);
		webElmTextFieldEmail.sendKeys(email);
	}

}
