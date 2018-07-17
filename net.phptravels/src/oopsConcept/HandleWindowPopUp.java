package oopsConcept;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HandleWindowPopUp {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"E:\\AuToMaTiOn\\ALL DRIVERS\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
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

		driver.get("http://www.popuptest.com/goodpopups.html");

		driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/font/b/a[1]")).click();

		Set<String> handler = driver.getWindowHandles();

		Iterator<String> it = handler.iterator();

		String parentWindowId = it.next();
		System.out.println("parentWindowId is = " + parentWindowId);

		String childWindowId = it.next();
		System.out.println("childWindowId is = " + childWindowId);

		Thread.sleep(3000);
		driver.switchTo().window(childWindowId);
		Thread.sleep(3000);
		driver.switchTo().window(parentWindowId);
		Thread.sleep(3000);
		driver.switchTo().window(childWindowId);
		driver.close();
		Thread.sleep(3000);
		driver.switchTo().window(parentWindowId);

		Thread.sleep(3000);
		driver.close();
	}

}
