package com.automation.driver;

import java.time.Duration;

import com.automation.configuration.ConfigManager;
import com.automation.pages.AndroidLandingPage;
import com.automation.pages.IOSLandingPage;
import com.automation.pages.LandingPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
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
	public static LandingPage landingPage;

	public void initializeDriver() throws Exception {

		ConfigManager.load("config.yaml");
		host = ConfigManager.get("server", "local", "host");
		port = (ConfigManager.get("server", "local", "port"));
		executionMode = ConfigManager.get("execution", "mode");
		platform = ConfigManager.get("execution", "platform");
		device_name = ConfigManager.get("platform_config",platform, "device_name");
		app = ConfigManager.get("platform_config",platform, "app_path");
		timeout = ConfigManager.get("timeouts", "implicit");
		if (executionMode.equalsIgnoreCase("local")) {
			startAppium();
		}
		driverSetUp();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(timeout)));


	}
	
	public  void initPages() {
System.out.println("tarun Kumar 2");
        if (driver instanceof AndroidDriver) {
        	System.out.println("tarun Kumar 3");
            landingPage = new AndroidLandingPage(driver);
            System.out.println("tarun Kumar 5");
            
       

        } else if (driver instanceof IOSDriver) {
        	System.out.println("tarun Kumar 4");
            landingPage = new IOSLandingPage(driver);
         

        } else {
            throw new RuntimeException("Unsupported platform");
        }
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
		//service= new AppiumServiceBuilder().withIPAddress("127.0.0.1")
		//		.usingPort(4723).build();
		//	service.start();

	}

	public void stopAppium() {
		service.stop();
	}

	public void tearDown() {
		if(DriverManager.getDriver()!=null) {
		DriverManager.getDriver().quit();
		}
		DriverManager.unloadDriver();
		if (executionMode.equalsIgnoreCase("local")) {
			stopAppium();
		}
	}
}
