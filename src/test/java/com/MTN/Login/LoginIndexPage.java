package com.MTN.Login;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.STL.Init.AbstractPage;
import com.STL.Init.Common;
import com.STL.Utility.TestData;

public class LoginIndexPage extends AbstractPage {

	public LoginIndexPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//input[@name='username']")
	WebElement usernameTxtField;

	public void enterUsername(String username) {
		Common.waitForElement(usernameTxtField, driver);
		usernameTxtField.sendKeys(username);
		log("Entered Username : "+username);
	}

	@FindBy(xpath="//input[@name='password']")
	WebElement passwordField;

	public void enterPassword(String password) {
		Common.waitForElement(passwordField, driver);
		passwordField.sendKeys(password);
		log("Entered Password : "+password);
	}

	@FindBy(xpath="//button[contains(@class,'Login')]")
	WebElement doLoginBtn;

	public LoginVerification clickToLogin() {
		Common.clickableElement(doLoginBtn, driver);
		Common.clickOn(driver, doLoginBtn);
		log("Clicked Login button.");

		return new LoginVerification(driver);
	}

	public LoginVerification enterLoginDetails(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickToLogin();

		return new LoginVerification(driver);
	}

	@FindBy(xpath="//p[contains(@class,'userName')]")
	WebElement usernameOnHeader;

	public void clickUsernameOnHeader() {
		Common.clickableElement(usernameOnHeader, driver);
		Common.clickOn(driver, usernameOnHeader);
	}

	@FindBy(xpath="//li//img[contains(@src,'Dashboard')]/parent::div")
	WebElement dashboardLbl;

	public void clickOnDashboard() {
		Common.clickableElement(dashboardLbl, driver);
		Common.clickOn(driver, dashboardLbl);
	}

	public void goToDashboard() {
		log("Go to Username > Dashboard.");
		clickUsernameOnHeader();
		clickOnDashboard();
	}

	@FindBy(xpath="//li//img[contains(@src,'My Account')]/parent::div")
	WebElement myAccountLbl;

	public void clickOnMyAccount() {
		Common.clickableElement(myAccountLbl, driver);
		Common.clickOn(driver, myAccountLbl);
	}

	public void goToMyAccount() {
		log("Go to Username > My Account.");
		clickUsernameOnHeader();
		clickOnMyAccount();
	}

	@FindBy(xpath="//label[contains(@class,'forgot-password')]")
	WebElement forgotPasswordLnk;

	public void clickOnForgotPassword() {
		Common.clickableElement(forgotPasswordLnk, driver);
		Common.clickOn(driver, forgotPasswordLnk);
		log("Clicked Forgot your Password? link");
	}

	public void clickOnResetPswdUsingLinkBtn() throws IOException {
		String resetLink = TestData.getValueFromConfig("message.properties", "Link");
		WebElement resetUsingLinkBtn = driver.findElement(By.xpath("//button[contains(text(),'"+resetLink+"')]"));
		Common.clickableElement(resetUsingLinkBtn, driver);
		Common.clickOn(driver, resetUsingLinkBtn);
		log("Clicked on Reset Password Using Link button");
	}

	@FindBy(xpath="//button[contains(text(),'OTP')]")
	WebElement resetUsingOtpBtn;

	public void clickOnResetPswdUsingOtpBtn() {
		Common.clickableElement(resetUsingOtpBtn, driver);
		Common.clickOn(driver, resetUsingOtpBtn);
		log("Clicked on Reset Password Using OTP button");
	}

	@FindBy(xpath="//div[@class='explore-slider']")
	WebElement slider;

	public void selectBestSellingPlan(String plan) {
		WebElement bestSellingPlan = driver.findElement(By.xpath("//div[@class='explore-slider']//h2[text()='"+plan+"']/following-sibling::a"));
		try {
			Common.clickOn(driver, bestSellingPlan);
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView({inline: 'center'});", bestSellingPlan);
			Common.scrollToMiddle(driver, bestSellingPlan);
			Common.jsClick(driver, bestSellingPlan);
		}
		log("Selected best selling plan: "+plan);
	}
}