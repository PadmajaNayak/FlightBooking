package flightBooking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverUtility {
	public static WebDriver driver;
	public static WebDriver setDriver(String browser) {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
			driver = new ChromeDriver();
		} else if (browser.equals("safari")) {
			driver = new SafariDriver();
		}
		return driver;
	}
}
