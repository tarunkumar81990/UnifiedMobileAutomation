package com.automation.pages;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import org.testng.Assert;

public abstract class AbstractLandingPage implements LandingPage {

	public AppiumDriver driver;

	public AbstractLandingPage(AppiumDriver driver) {
		this.driver = driver;

	}
	protected abstract WebElement landingPageImage();

	public void verifyLandingPage() {
		Assert.assertTrue(landingPageImage().isDisplayed(),"User is not on Landing page");
		
	}

	public void selectCountry(String country) {
		
	}

	public void enterUserName(String userName) {
	}

	public void selectGender(String gender) {
	}

	public void verifyProductListPage() {
	}
}
