package features;

import org.openqa.selenium.WebDriver;

import flightBooking.BookingPage;
import flightBooking.DriverUtility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDef {
	WebDriver driver;
	BookingPage bookingPage;
	
	@Given("I open the flight booking portal in {string} browser")
	public void i_open_the_flight_booking_portal_in_browser(String browser) {
		driver = DriverUtility.setDriver(browser);
		driver.manage().window().maximize();
		driver.get("http://www.goibibo.com/");
		bookingPage = new BookingPage(driver);
	}

	@And("I select {string} as source")
	public void i_select_as_source(String source) {
		bookingPage.selectSource(source);
	}

	@And("I select {string} as destination")
	public void i_select_as_destination(String destination) {
		bookingPage.selectDestination(destination);
	}

	@When("I get the fares")
	public void i_get_the_fares() {
		bookingPage.getFares();
	}

	@Then("I should be able to select the best itinerary")
	public void i_should_be_able_to_select_the_best_itinerary() {
		bookingPage.getBestFare();
	}
	
	@And("I close the browser")
	public void i_close_the_browser() {
		driver.quit();
	}
}
