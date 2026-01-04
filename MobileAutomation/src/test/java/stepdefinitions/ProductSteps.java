package stepdefinitions;

import com.automation.driver.BaseTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductSteps extends BaseTest{

	@Given("User is on landing page of the application")
	public void user_is_on_landing_page_of_the_application() {
	    landingPage.verifyLandingPage();
	    
	}
	
	@When("user select {string} from the country dropdown")
	public void user_select_from_the_country_dropdown(String string) {

	}
	@When("user enter {string} in username field")
	public void user_enter_in_username_field(String string) {

	}
	@When("user selects {string} as gender in radio button")
	public void user_selects_as_gender_in_radio_button(String string) {

	}
	@Then("user lands on product list page")
	public void user_lands_on_product_list_page() {

	}

}
