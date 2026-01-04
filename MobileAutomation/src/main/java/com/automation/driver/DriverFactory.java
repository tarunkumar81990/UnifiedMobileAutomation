package com.automation.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class DriverFactory {
	private static DriverFactory instance = null;

	private DriverFactory() {
	}

	public static DriverFactory getInstance() {
		if (instance == null) {
			instance = new DriverFactory();
		}
		return instance;
	}

	public AppiumDriver createDriver(String executionMode, String platform, String device_name, String app, String host,
			String port) throws MalformedURLException {
		AppiumDriver driver = null;
		if (executionMode.equalsIgnoreCase("local")) {
			if (platform.equalsIgnoreCase("android")) {
				UiAutomator2Options options = new UiAutomator2Options();
				options.setDeviceName(device_name);
				options.setApp(app);
				options.setFullReset(true);
				URL url = URI.create("http://" + host + ":" + port).toURL();
				driver = new AndroidDriver(url, options);

			} else if (platform.equalsIgnoreCase("android")) {
			} else {
				throw new IllegalArgumentException("Execution platform is not valid");
			}

		} else if (executionMode.equalsIgnoreCase("cloud")) {

		} else {
			throw new IllegalArgumentException("Execution Mode is not valid");
		}

		return driver;
	}
}
