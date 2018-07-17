import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCRM {

	static WebDriver driver;

	// @FindBy(name = "username")
	// WebElement username;
	//
	// @FindBy(name = "password")
	// WebElement password;
	//
	// @FindBy(xpath = "//input[@type='submit']")
	// WebElement loginBtn;
	//
	// public TestCRM() {
	// PageFactory.initElements(driver, this);
	// }

	@BeforeMethod
	public void invokeBrowser() {

		System.setProperty("webdriver.chrome.driver",
				"E:\\AuToMaTiOn\\ALL DRIVERS\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		driver.get("https://www.freecrm.com");

	}

	@Test
	public void loginCrm() throws InterruptedException {
		// invokeBrowser();
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement loginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		username.sendKeys("himanshum");
		password.sendKeys("hmnshum@1231");
		loginBtn.click();
		Thread.sleep(10000);
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
