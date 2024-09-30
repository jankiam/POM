package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.crm.qa.utilities.TestUtil;
import com.crm.qa.utilities.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	
	public static ExtentReports extentReports;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentTest extentTest; 
	
	
	public TestBase() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"C:\\Users\\yasha\\eclipse-workspace\\POM\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeSuite
	public void setupSuite() {
			 extentReports = new ExtentReports();
			 sparkReporter= new ExtentSparkReporter("extent.html");
			 extentReports.attachReporter(sparkReporter);
			 
		}
	
	
	public static void initialization() {
		String browsername = prop.getProperty("browser");
		if (browsername.equals("chrome")) {
			driver = new ChromeDriver();

		} else if (browsername.equals("firefox")) {
			driver = new FirefoxDriver();
		}

        //Use this Eventfiringdecorator class for selenium console logs
		WebEventListener listener = new WebEventListener();
		driver = new EventFiringDecorator(listener).decorate(driver);
		

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
	}

	
	@AfterSuite
	public void tearDownSuite() {
		extentReports.flush();
		
	}
}
