package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class AndroidLandingPage extends AbstractLandingPage {

	public AndroidLandingPage(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//android.widget.ImageView[@resource-id=\"com.androidsample.generalstore:id/splashscreen\"]")
	private WebElement img_landingPage;

	@Override
	protected WebElement landingPageImage() {
		System.out.println("Andoidtarun");
		return img_landingPage;
		
	}

}


