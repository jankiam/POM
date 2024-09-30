package com.crm.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//td[contains(text(),'User: Gagan Khanna')]")
	WebElement userNameLabel;

	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;

	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement tasksLink;

	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContact;

	// Initialize all page objects by this current class object driver
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public String verifyHomePageTitle() {
		return driver.getTitle();

	}

	public boolean verifyCorrectUserName() {
		return userNameLabel.isDisplayed();

	}

	public ContactsPage clickOnContactsLink() {
		contactsLink.click();
		return new ContactsPage();
	}

	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage();
	}

	public TasksPage clickOnTasksLink() {
		tasksLink.click();
		return new TasksPage();
	}

	public void clickOnNewContactLink() {
		Actions actions = new Actions(driver);
		actions.moveToElement(contactsLink).perform();
		newContact.click();
	}

	
}
