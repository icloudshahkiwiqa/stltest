package com.MTN.Search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.STL.Init.AbstractPage;
import com.STL.Init.Common;

public class SearchIndexPage extends AbstractPage{

	public SearchIndexPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//a[contains(@class,'search')]")
	WebElement searchIcon;

	@FindBy(xpath="//input[@type='search']")
	WebElement searchTxtField;

	public void enterPlanToSearch(String plan) {
		log("Search a plan.");
		Common.clickableElement(searchIcon, driver);
		Common.clickOn(driver, searchIcon);
		String searchPlan = plan.split(" ")[0].trim();
		searchTxtField.sendKeys(searchPlan);
		log("Searching : "+plan);
	}

	public void selectPlan(String plan) {
		log("Select plan from result.");
		WebElement result = driver.findElement(By.xpath("//div[contains(@class,'content') and text()='"+plan+"']"));
		Common.waitForElement(result, driver);
		Common.clickOn(driver, result);
		log("Clicked on Plan : "+plan);
		Common.pause(15);
	}
}