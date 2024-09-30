package com.crm.qa.testcases;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utilities.TestUtil;

public class ContactsPageTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactspage;
	
	String sheetName= "contacts";

	public ContactsPageTest() {
		// Using super keyword to call super/base class constructor
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		loginpage = new LoginPage();
		testutil= new TestUtil();
		contactspage= new ContactsPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		testutil.switchToFrame();
		//contactspage= homepage.clickOnContactsLink();
	}

	@Test(priority = 1)
	public void verifyContactsPageLabelTest() {
		contactspage= homepage.clickOnContactsLink();
		Assert.assertTrue(contactspage.verifyContactsLabel());

	}

	@Test(priority = 2)
	public void selectContactByNameTest() {
		contactspage= homepage.clickOnContactsLink();
		contactspage.selectContacts("Akshay Singh");
		contactspage.selectContacts("Alex Berth");

	}

	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][]= testutil.getTestData(sheetName);
		return data;
	}
		
		
	@Test(priority = 3, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title,String firstname, String lastname, String company)  {
		homepage.clickOnNewContactLink();
		//contactspage.createNewContact("Mr.", "Testjk", "Peter","Google");
		contactspage.createNewContact(title, firstname, lastname, company);
		
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
