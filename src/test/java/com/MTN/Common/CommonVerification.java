package com.MTN.Common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.STL.Init.AbstractPage;
import com.STL.Init.Common;

public class CommonVerification extends AbstractPage {

	public CommonVerification(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//p[contains(@class,'orderNumber')]/a")
	WebElement orderNumber;

	@FindBy(xpath="//p[contains(@class,'paymentID')]/a")
	WebElement paymentId;

	public boolean verifyOrderPlaced() {
		Common.waitForElement(orderNumber, driver);

		return orderNumber.isDisplayed() && paymentId.isDisplayed();
	}
}
