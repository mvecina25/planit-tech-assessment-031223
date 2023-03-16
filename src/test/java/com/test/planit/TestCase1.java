package com.test.planit;

//import static org.testng.Assert.assertEquals;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBaseSetup;
import base.TestListener;
import pages.Contact;
import pages.Home;

public class TestCase1 extends TestBaseSetup{
	
	WebDriver driver;
	
	@BeforeClass
	public void setUp(ITestContext iTestContext) {
		driver = getDriver();		
		
		// Set the driver object to the ITestContext
		TestListener.setContext(iTestContext, driver);
	}
	
	@Test
	public void testCase1() {		
		
		SoftAssert SAssert = new SoftAssert();
		
		// Establishing connection of two POMs
		Home HP = new Home(driver);
		
		// From the home page go to contact page
		Contact CP = HP.click_ContactTab();

		// Click submit button
		CP.click_Submit();

		// Verify forename error
		String expectedErrorForename = "Forename is required";
		SAssert.assertEquals(CP.errMsg_foreName(), expectedErrorForename);

		// Verify email error
		String expectedErrorEmail = "Email is required";
		SAssert.assertEquals(CP.errMsg_email(), expectedErrorEmail);
		
		// Verify message error
		String expectedErrorMessage = "Message is required";
		SAssert.assertEquals(CP.errMsg_message(), expectedErrorMessage);

		// Populate mandatory fields
		CP.input_Forename("Medel");
		CP.input_Email("medel.vecina@gmail.com");
		CP.input_Message("Planit Testing");

		// Validate errors are gone
		SAssert.assertFalse(CP.errMsg_ContactForm().contains("Forename is required"));
		SAssert.assertFalse(CP.errMsg_ContactForm().contains("Email is required"));
		SAssert.assertFalse(CP.errMsg_ContactForm().contains("Message is required"));	
		
		SAssert.assertAll();

	}
	
}
