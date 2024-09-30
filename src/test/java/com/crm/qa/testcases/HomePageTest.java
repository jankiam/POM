package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utilities.TestUtil;

public class HomePageTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactspage;
	public HomePageTest() {
		// Using super keyword to call super/base class constructor
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		testutil = new TestUtil();
		loginpage = new LoginPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String homepageTitle= homepage.verifyHomePageTitle();
		Assert.assertEquals(homepageTitle, "CRMPRO", "Home Page title not matched");
	}
	
	@Test(priority=2)
	public void verifyUserNameTest() {
		testutil.switchToFrame();
		System.out.println("switched to iframe successfully");
		Assert.assertTrue(homepage.verifyCorrectUserName(), "User name is matched");
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() {
		testutil.switchToFrame();
		contactspage= homepage.clickOnContactsLink();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
