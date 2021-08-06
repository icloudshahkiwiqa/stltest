package com.MTN.Category;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.STL.Init.AbstractPage;

public class CategoryIndexPage extends AbstractPage{

	public CategoryIndexPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//h4[text()='Mobility']")
	WebElement mobilityCategory;

	@FindBy(xpath="//a[text()='Mobile Internet']")
	WebElement mobileInternetCategory;

	public void goToMobility(String mobilityType) {
		log("Go to Mobility > Mobile Internet > "+mobilityType);
		Actions action = new Actions(driver);
		action.moveToElement(mobilityCategory).moveToElement(mobileInternetCategory).build().perform();
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Mobile Internet']/..//a[text()='"+mobilityType+"']"))).click().build().perform();
		log("Clicked on "+mobilityType);
	}
}