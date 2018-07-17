package com.facebook.TestNG;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

public class FacebookTestNG {

	static WebDriver driver;
@Test
	public static void invokeBrowser(String baseUrl) {
		System.setProperty("webdriver.chrome.driver",
				"E:\\AuToMaTiOn\\ALL DRIVERS\\chromedriver_win32\\chromedriver.exe");

		Map<String, Object> prefs = new HashMap<String, Object>();

		// Set the notification setting it will override the default setting
		prefs.put("profile.default_content_setting_values.notifications", 2);

		// Create object of ChromeOption class
		ChromeOptions options = new ChromeOptions();

		// Set the experimental option
		options.setExperimentalOption("prefs", prefs);

		options.addArguments("--disable-extensions");

		driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		driver.get(baseUrl);
	}

	public static void facebookLogon(String userName, String password) throws IOException {

		WebElement email = driver.findElement(By.id("email"));
		WebElement pass = driver.findElement(By.id("pass"));
		WebElement logIn = driver.findElement(By.id("u_0_2"));

		captureScreenshots("fbLoginPage");

		Actions action = new Actions(driver);
		action.moveToElement(email).click().sendKeys(email, userName).perform();
		captureScreenshots("enterEmail");
		action.moveToElement(pass).click().sendKeys(pass, password).perform();
		captureScreenshots("enterPass");
		logIn.click();

		String actualTitle = driver.getTitle();
		String expectedTitle = "Facebook";

		if (actualTitle.equalsIgnoreCase(expectedTitle)) {
			System.out.println("Test Passed");
		} else {
			System.out.println("Test Failed - " + "Actual Title is = " + actualTitle);
		}

	}

	public static void captureScreenshots(String screenshotName) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(src, new File("./Screenshot/" + screenshotName + ".png"));

	}

	public static void logOut() throws IOException, InterruptedException {
		WebElement userNav = driver.findElement(By.cssSelector("#userNavigationLabel"));
		Actions action = new Actions(driver);
		action.moveToElement(userNav).click().perform();
		Thread.sleep(5000);
		captureScreenshots("fbLogoutOptions");

		WebElement lout = driver.findElement(By.cssSelector(
				"._w0d[action='https://www.facebook.com/login/device-based/regular/logout/?button_name=logout&button_location=settings']"));

		action.moveToElement(lout).click().perform();
		captureScreenshots("fbLoginPageAgain");

	}

	public static void createUser() throws InterruptedException, IOException {
		WebElement firstName = driver.findElement(By.xpath("//*[@name=\"firstname\"]"));
		firstName.sendKeys("Himanshu");
		//

		WebElement lastName = driver.findElement(By.xpath("//*[@name=\"lastname\"]"));
		lastName.sendKeys("Malviya");

		WebElement emailId = driver.findElement(By.xpath("//*[@name=\"reg_email__\"]"));
		emailId.sendKeys("9669304056");

		WebElement password = driver.findElement(By.xpath("//*[@name=\"reg_passwd__\"]"));
		password.sendKeys("hmnshu1231");

		WebElement day = driver.findElement(By.xpath("//*[@name=\"birthday_day\"]"));

		Select birthDay = new Select(day);
		birthDay.selectByValue("31");

		WebElement Month = driver.findElement(By.xpath("//*[@name=\"birthday_month\"]"));

		Select birthMonth = new Select(Month);
		birthMonth.selectByIndex(4);

		WebElement year = driver.findElement(By.xpath("//*[@name=\"birthday_year\"]"));

		Select birthYear = new Select(year);
		birthYear.selectByVisibleText("1993");
		Thread.sleep(10000);

		captureScreenshots("createNewUser");
	}

}
