package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class IOSLandingPage extends AbstractLandingPage {

	public IOSLandingPage(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "")
	private WebElement img_landingPage;

	@Override
	protected WebElement landingPageImage() {
		System.out.println("InIostarun");
		return img_landingPage;
	
	}
	

}
