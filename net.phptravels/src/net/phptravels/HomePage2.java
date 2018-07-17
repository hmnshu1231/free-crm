package net.phptravels;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage2 {
	static WebDriver driver;

	public static void main(String[] args) {

		invokeBrowser("https://www.phptravels.net/");
		homeP();
		myAccount();
		usdMenu();
	}

	public static void invokeBrowser(String baseUrl) {
		System.setProperty("webdriver.chrome.driver",
				"E:\\AuToMaTiOn\\ALL DRIVERS\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		driver.get(baseUrl);
	}

	public static void homeP() {
		WebElement homeBar = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/div/ul/li[1]"));
		WebElement homeBar2 = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/div/ul/li[2]"));

		String barText = homeBar.getText();
		String barText2 = homeBar2.getText();

		System.out.println(barText);
		System.out.println(barText2);

	}

	public static void myAccount() {
		// String expectedText = "MY ACCOUNT";
		WebElement myAcc = driver.findElement(By.xpath("//*[@id=\"li_myaccount\"]/a"));
		String actualText = myAcc.getText();

		System.out.println(actualText);

		Boolean textAcc = actualText.endsWith("MY ACCOUNT");
		System.out.println(textAcc);

	}

	public static void usdMenu() {

		WebElement currUsd = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/ul/li[2]/a"));

		Actions act = new Actions(driver);
		act.moveToElement(currUsd).build().perform();
	}
	}
