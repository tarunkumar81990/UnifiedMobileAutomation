package com.automation.driver;

import java.time.Duration;

import com.automation.configuration.ConfigManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	public static AppiumDriver driver;
	AppiumDriverLocalService service;
	String host;
	String port;
	String executionMode;
	String platform;
	String device_name;
	String app;
	String timeout;

	public void initializeDriver() throws Exception {

		ConfigManager.load("config.yaml");
		host = ConfigManager.get("server", "local", "host");
		port = (ConfigManager.get("server", "local", "port"));
		executionMode = ConfigManager.get("execution", "mode");
		platform = ConfigManager.get("execution", "platform");
		device_name = ConfigManager.get(platform, "device_name");
		app = ConfigManager.get(platform, "app_path");
		timeout = ConfigManager.get("timeouts", "implicit");
		if (executionMode.equalsIgnoreCase("local")) {
			startAppium();
		}
		driverSetUp();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(timeout)));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(timeout)));


	}

	void driverSetUp() throws Exception {
		AppiumDriver driverObj = DriverFactory.getInstance().createDriver(executionMode, platform, device_name, app,
				host, port);
		DriverManager.setDriver(driverObj);
		driver = DriverManager.getDriver();
	}

	public void startAppium() {
		service = new AppiumServiceBuilder().withIPAddress(host).usingPort(Integer.parseInt(port)).build();
		service.start();

	}

	public void stopAppium() {
		service.stop();
	}

	public void tearDown() {
		DriverManager.getDriver().close();
		DriverManager.unloadDriver();
		if (executionMode.equalsIgnoreCase("local")) {
			stopAppium();
		}
	}
}
