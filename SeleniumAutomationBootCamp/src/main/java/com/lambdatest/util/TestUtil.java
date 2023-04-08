package com.lambdatest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.poi.extractor.MainExtractorFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lambdatest.driver.BrowserDriver;

public class TestUtil {

	static WebDriver driver;

	public static void hoverMenuItem(WebElement webElement) {
		driver = BrowserDriver.getWebDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(webElement).build().perform();
	}

	static String basePath = System.getProperty("user.dir");
	static String filePathTestData = basePath + "/src/test/resources/TestData.xlsx";

	public static Object[][] readTestData(String sheetName) {
		try {
			FileInputStream fileInputStream = new FileInputStream(filePathTestData);
			Workbook workbook = WorkbookFactory.create(fileInputStream);
			Sheet sheet = workbook.getSheet(sheetName);
			int columnSize = sheet.getRow(0).getLastCellNum();
			int rowSize = sheet.getLastRowNum();

			// int[] data = new int[10];
			// int[][] data1 = new int[100][50];
			Object[][] data = new Object[rowSize][columnSize];

			for (int i = 1; i <= rowSize; i++) {
				for (int j = 0; j < columnSize; j++) {
					data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
				}
			}
			return data;
		} catch (Exception e) {

		}
		return null;
	}

	public static void takeScreenshot() throws IOException {
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// screenshot/123456.png
		String destFilePath = basePath + "/screenshot/" + System.currentTimeMillis() + ".png";
		FileUtils.copyFile(sourceFile, new File(destFilePath));
	}

	public static <T> T waitForElementExplict(ExpectedCondition<T> condition, WebDriver webDriver, int timeout) {
		WebDriverWait driverWait = new WebDriverWait(webDriver, Duration.ofSeconds(timeout));
		return driverWait.until(condition);
	}

	public static WebElement waitForElementFluent(final By locator, WebDriver webDriver, int timeOut, int pollTime) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollTime)).ignoring(Exception.class);

		return wait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver t) {
				// TODO Auto-generated method stub
				return t.findElement(locator);
			}
		});
	}

}
