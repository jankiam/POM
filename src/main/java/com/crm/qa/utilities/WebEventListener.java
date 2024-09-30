package com.crm.qa.utilities;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import com.crm.qa.base.TestBase;

public class WebEventListener extends TestBase implements WebDriverListener {

	@Override
	public void afterGet(WebDriver driver, String url) {

		System.out.println("After Navigating to URl: " + url);
	}

	@Override
	public void beforeFindElements(WebDriver driver, By locator) {
		System.out.println("Before Finding elements: " + locator);

	}

	@Override
	public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
		System.out.println("After Finding elements: " + locator);

	}

	@Override
	public void beforeClick(WebElement element) {
		System.out.println("Before clicking on : " + element.toString());

	}

	@Override
	public void afterClick(WebElement element) {
		System.out.println("After clicking on : " + element.toString());

	}

	@Override
	public void afterSubmit(WebElement element) {
		System.out.println("After submit  : " + element.toString());

	}

	@Override
	public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
		System.out.println("After entering values for element: " + element.toString() + "  entered values as: ");

		for (int i = 0; i < keysToSend.length; i++) {
			System.out.println(keysToSend[i] + " ");
		}
	}

	@Override
	public void afterFrame(WebDriver.TargetLocator targetLocator, String nameOrId, WebDriver driver) {
		System.out.println("After switching to frame " + nameOrId);

	}

	@Override
	public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
		System.out.println("Error occured: " + method);
		try {
			TestUtil.takeScreenshotAtEndOfTest();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}



}
