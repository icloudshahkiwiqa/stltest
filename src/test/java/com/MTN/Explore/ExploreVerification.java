package com.MTN.Explore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.STL.Init.AbstractPage;
import com.STL.Init.Common;

public class ExploreVerification extends AbstractPage{

	public ExploreVerification(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//h3[contains(@class,'Login')]")
	WebElement loginDetailLbl;

	@FindBy(xpath="//input[@name='username']/..//label")
	WebElement loginUsernameLbl;

	@FindBy(xpath="//input[@name='password']/..//label")
	WebElement loginPasswordLbl;

	public boolean verifyLoginPage() {
		Common.clickableElement(loginUsernameLbl, driver);
		String actualURL = driver.getCurrentUrl();

		return actualURL.contains("login")  && loginDetailLbl.isDisplayed() && loginUsernameLbl.isDisplayed() && loginPasswordLbl.isDisplayed();
	}

	@FindBy(xpath="//p[contains(@class,'Header_txt')]")
	WebElement usernameOnHeader;

	public boolean verifyNameOnHeader(String firstName){
		Common.pause(5);
		boolean result = false;
		int attempts = 0;
		while(attempts < 2) {
			try {
				Common.waitForElement(usernameOnHeader, driver);
				result = true;
				break;
			} catch(Exception e) {
			}
			attempts++;
		}
		String username = usernameOnHeader.getText().toUpperCase();

		return username.contains(firstName.toUpperCase());
	}

	@FindBy(xpath="//img[@alt='icon-user']")
	WebElement userIcon;

	public boolean verifyHomePageAfterLogin() {
		Common.pause(5);
		Common.waitForElement(userIcon, driver);

		return userIcon.isDisplayed();
	}

	@FindBy(xpath="//div[@class='valid-feedback']")
	WebElement validSimNumMsg;

	public boolean verifyValidSimNumber() {
		Common.waitForElement(validSimNumMsg, driver);
		if(validSimNumMsg.isDisplayed()) {
			return true;
		} else
			return false;
	}
}