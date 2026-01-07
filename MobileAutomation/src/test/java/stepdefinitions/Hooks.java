package stepdefinitions;

import com.automation.driver.BaseTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BaseTest{
@Before
public void before() throws Exception {
	initializeDriver();
	initPages();
	}



@After
public void after() {
	tearDown();
}
}
