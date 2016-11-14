package skeleton;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import skeleton.Checkout.Produce;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Stepdefs {
	private Belly belly;
	private int waitingTime;
	private Checkout checkout;

	@Given("^I have (\\d+) cukes in my belly$")
	public void i_have_cukes_in_my_belly(int cukes) throws Throwable {
		belly = new Belly();
		belly.eat(cukes);
	}

	@When("^I wait (\\d+) hour$")
	public void i_wait_hour(int waitingTime) throws Throwable {
		this.waitingTime = waitingTime;
	}

	@Then("^my belly should (.*)$")
	public void my_belly_should_growl(String expectedSound) throws Throwable {
		String actualSound = belly.getSound(waitingTime);
		assertThat(actualSound, is(expectedSound));
	}

	@Then("^my belly is (.*)$")
	public void my_belly_should_be_silent(String expectedSound) throws Throwable {
		String actualSound = belly.getSound(waitingTime);
		assertThat(actualSound, is(expectedSound));
	}

	@Given("^I checkout (\\d+) apples$")
	public void checkedout_some_apples(int apples) throws Throwable {
		checkout = new Checkout();
		for (int c = 0; c < apples; c++) {
			checkout.registerItem(Produce.APPLE);
		}
	}
	
	@Given("^I checkout (\\d+) banana$")
	public void checkedout_some_bananas(int banana) throws Throwable {
		checkout = new Checkout();
		for (int c = 0; c < banana; c++) {
			checkout.registerItem(Produce.BANANA);
		}
	}
	
	@Given("^I checkout (\\d+) grapes$")
	public void checkedout_some_grapes(int grapes) throws Throwable {
		checkout = new Checkout();
		for (int c = 0; c < grapes; c++) {
			checkout.registerItem(Produce.GRAPES);
		}
	}
	
	@Then("^my cost is (.*)$")
	public void my_total_cost_is(String totalGroceryCost) throws Throwable {
		String totalCost = checkout.getTotalCost();
		assertThat(totalCost, is(totalGroceryCost));
	}
}
