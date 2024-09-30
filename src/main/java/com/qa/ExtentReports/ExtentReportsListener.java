package com.qa.ExtentReports;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.crm.qa.base.TestBase;

public class ExtentReportsListener  extends TestBase implements ITestListener {

	
    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
        extentTest.log(Status.FAIL, result.getThrowable());

        
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        // This can be used to initialize before the test suite starts
    }

    @Override
    public void onFinish(ITestContext context) {
        // This is where we flush the report after all tests
        extentReports.flush();
    }
}
