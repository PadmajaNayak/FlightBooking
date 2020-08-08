package flightBooking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookingPage {
	public BookingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	WebDriver driver;

	@FindBy(id = "gosuggest_inputSrc")
	static WebElement fromCity;
	@FindBy(id = "gosuggest_inputDest")
	static WebElement toCity;
	@FindBy(id = "gi_search_btn")
	static WebElement searchBtn;
	@FindBy(id = "departureCalendar")
	static WebElement departureCalendar;
	@FindBy(className = "DayPicker-Caption")
	static WebElement monthEle;
	@FindBy(className = "DayPicker-NavButton DayPicker-NavButton--next")
	static WebElement monthNextBtn;
	@FindBy(xpath = "//li[@id='DURATION']//i[@class ='ico13 icon-arrow2-up hpyBlueLt ']")
	static WebElement sortedDuration;
	@FindBy(xpath = "//li[@id='PRICE']//i[@class ='ico13 icon-arrow2-up hpyBlueLt ']")
	static WebElement sortedPrice;
	@FindBy(xpath = "//li[@id='DURATION']")
	static WebElement durationEle;
	@FindBy(xpath = "//li[@id='PRICE']")
	static WebElement priceEle;
	@FindBy(xpath = "//span[@data-cy='finalPrice']")
	static List<WebElement> finalPriceListEle;
	@FindBy(xpath = "//input[@value='BOOK']")
	static List<WebElement> bookEleList;
	@FindBy(xpath = "//span[@data-cy='depTime']")
	static List<WebElement> depTimeListEle;
	@FindBy(xpath = "//div[@data-cy='duration']")
	static List<WebElement> durationListEle;

	public void selectSource(String source) {
		Utilities.waitForElement(driver, toCity, 60);
		fromCity.sendKeys(source);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement ele = driver
				.findElement(By.xpath("//ul[@id='react-autosuggest-1']/li[@id='react-autosuggest-1-suggestion--0']"));
		ele.click();
	}

	public void selectDestination(String dest) {
		Utilities.waitForElement(driver, toCity, 60);
		toCity.sendKeys(dest);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement ele = driver
				.findElement(By.xpath("//ul[@id='react-autosuggest-1']/li[@id='react-autosuggest-1-suggestion--0']"));
		ele.click();
	}

	public void getFares() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
		LocalDate now = LocalDate.now().plusDays(3);
		String date = dtf.format(now);
		departureCalendar.click();
		WebElement dateEle = driver.findElement(By.xpath("//div[@aria-label ='" + date + "']"));
		dateEle.click();
		searchBtn.click();
	}

	public void getBestFare() {
		Utilities.waitForElement(driver, durationEle, 60);
		durationEle.click();
		List<String> priceList = new ArrayList<>();
		for (WebElement finalPrice : finalPriceListEle) {
			priceList.add(finalPrice.getText().replace(",", ""));
		}
		List<Integer> durationList = new ArrayList<>();
		for (WebElement duration : durationListEle) {
			int hr = Integer.parseInt((duration.getText().split("h "))[0]);
			int min = Integer.parseInt((duration.getText().split("h "))[1].replace("m", ""));
			int time = (hr * 60) + min;
			durationList.add(time);
		}
		for (int i = 0; i < durationList.size(); i++) {
			if (durationList.get(i) == durationList.get(i + 1)) {
				if (Integer.parseInt(priceList.get(i)) == Integer.parseInt(priceList.get(i + 1))) {
					if (Integer.parseInt((depTimeListEle.get(i).getText().split(":"))[0]) > Integer
							.parseInt((depTimeListEle.get(i + 1).getText().split(":"))[0])) {
						bookEleList.get(i).click();
						break;
					} else {
						bookEleList.get(i).click();
						break;
					}
				} else {
					bookEleList.get(i).click();
					break;
				}
			}
		}
	}
}
