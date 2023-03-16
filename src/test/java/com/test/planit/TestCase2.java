package com.test.planit;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import base.TestBaseSetup;
import base.TestListener;
import pages.Contact;
import pages.Home;

public class TestCase2 extends TestBaseSetup{
	
	WebDriver driver;
	
	@BeforeMethod
	@BeforeClass
	public void setUp(ITestContext iTestContext) {
		driver = getDriver();		
		
		// Set the driver object to the ITestContext
		TestListener.setContext(iTestContext, driver);
	}
		
	
	// Note: Run this test 5 times to ensure 100% pass rate
	@Test (invocationCount = 5)
	public void testCase2() {		
		
		SoftAssert SAssert = new SoftAssert();
		
		// Establishing connection of two POMs
		Home HP = new Home(driver);		
		HP.click_HomeTab();
		
		// From the home page go to contact page
		Contact CP = HP.click_ContactTab();		
		
		// Populate mandatory fields
		CP.input_Forename("Medel");
		CP.input_Email("medel.vecina@gmail.com");
		CP.input_Message("Planit Testing");
		
		// Click submit button
		CP.click_Submit();
		
		// Validate successful submission message
		SAssert.assertFalse(CP.alertMsg_ContactForm().contains("Thanks test, we appreciate your feedback."));
		
		SAssert.assertAll();
		
	}
	
}
