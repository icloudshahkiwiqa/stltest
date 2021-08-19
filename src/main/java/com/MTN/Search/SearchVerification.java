package com.MTN.Search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.STL.Init.AbstractPage;
import com.STL.Init.Common;

public class SearchVerification extends AbstractPage{

	public SearchVerification(WebDriver driver) {
		super(driver);
	}

	public boolean verifySelectedPlanPage(String plan) {
		WebElement planHeading = driver.findElement(By.xpath("//h2[text()='"+plan+"']")); 
		Common.waitForElement(planHeading, driver);

		return planHeading.isDisplayed();
	}
}