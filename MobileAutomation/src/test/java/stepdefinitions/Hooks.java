package stepdefinitions;

import com.automation.driver.BaseTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
BaseTest baseTest= new BaseTest();
@Before
public void before() throws Exception {
	baseTest.initializeDriver();
	}



@After
public void after() {
	baseTest.tearDown();
}
}
