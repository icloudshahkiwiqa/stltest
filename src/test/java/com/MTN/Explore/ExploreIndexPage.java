package com.MTN.Explore;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.STL.Init.AbstractPage;
import com.STL.Init.Common;

public class ExploreIndexPage extends AbstractPage{

	public ExploreIndexPage(WebDriver driver) {
		super(driver);
	} 

	@FindBy(xpath="//button[@class='login-btn']")
	WebElement loginBtn;

	public ExploreVerification clickOnLogin() {
		log("Click on Login button");
		Common.clickableElement(loginBtn, driver);
		Common.clickOn(driver, loginBtn);

		return new ExploreVerification(driver);
	}

	@FindBy(xpath="//a[contains(@href,'register')]")
	WebElement registerHereLink;

	public ExploreVerification clickOnRegisterHere() {
		log("Click on Register Here.");
		Common.scrollToMiddle(driver, registerHereLink);
		Common.clickableElement(registerHereLink, driver);
		Common.clickOn(driver, registerHereLink);

		return new ExploreVerification(driver);
	}

	@FindBy(xpath="//button[@type='submit']")
	WebElement registerBtn;

	public ExploreVerification clickOnRegisterBtn() {
		Common.scrollToMiddle(driver, registerBtn);
		Common.clickableElement(registerBtn, driver);
		Common.clickOn(driver, registerBtn);
		Common.pause(5);

		return new ExploreVerification(driver);
	}

	@FindBy(xpath="//input[@name='firstName']")
	WebElement firstNameTxtField;

	public void enterFirstname(String firstName) {
		Common.waitForElement(firstNameTxtField, driver);
		firstNameTxtField.sendKeys(firstName);
		log("Entered First Name : "+firstName);
	}

	@FindBy(xpath="//input[@name='middlename']")
	WebElement middleNameTxtField;

	public void enterMiddleName(String middleName) {
		Common.waitForElement(middleNameTxtField, driver);
		middleNameTxtField.sendKeys(middleName);
		log("Entered Middle Name : "+middleName);
	}

	@FindBy(xpath="//input[@name='lastName']")
	WebElement lastNameTxtField;

	public void enterLastName(String lastName) {
		Common.waitForElement(lastNameTxtField, driver);
		lastNameTxtField.sendKeys(lastName);
		log("Entered Last Name : "+lastName);
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

	@FindBy(xpath="//input[@name='otp']")
	WebElement otpTxtField;

	public void enterOTP(String otp) {
		Common.waitForElement(otpTxtField, driver);
		otpTxtField.sendKeys(otp);
		log("Entered OTP : "+otp);
	}

	@FindBy(xpath="//input[@type='checkbox']/..")
	WebElement receiveCommunicationChkBox;

	public void recieveCommunication(String receiveCommunication) {
		if(receiveCommunication.equalsIgnoreCase("yes")) {
			Common.checkChkBox(receiveCommunicationChkBox);
		}
		log("Receive Communication : "+receiveCommunication);
	}

	@FindBy(xpath="//input[@data-testid='dob']")
	WebElement dobTxtField;

	public void enterDob(String dob) {
		Common.waitForElement(dobTxtField, driver);
		dobTxtField.sendKeys(dob);
		dobTxtField.sendKeys(Keys.ENTER);
		log("Entered DoB : "+dob);
	}

	@FindBy(xpath="//select[@name='gender']")
	WebElement genderDrpdwn;

	public void selectGender(String gender) {
		Common.selectFromComboByVisibleElement(genderDrpdwn, gender);
		log("Selected Gender : "+ gender);
	}

	@FindBy(xpath="//select[@name='preferredLanguage']")
	WebElement prefLanguageDrpdwn;

	public void selectPreferredLanguage(String language) {
		Common.selectFromComboByVisibleElement(prefLanguageDrpdwn, language);
		log("Selected Preferred Language : "+ language);
	}

	@FindBy(xpath="//input[@name='alternateContact']")
	WebElement altContactNumberTxtField;

	public void enterAltContactNumber(String altContactNumber) {
		Common.waitForElement(altContactNumberTxtField, driver);
		altContactNumberTxtField.sendKeys(altContactNumber);
		log("Entered Alternate Contact Number : "+altContactNumber);
	}

	@FindBy(xpath="//input[@name='alternateEmail']")
	WebElement alternateEmailTxtField;

	public void enterAlternateEmail(String altEmail) {
		Common.waitForElement(alternateEmailTxtField, driver);
		alternateEmailTxtField.sendKeys(altEmail);
		log("Entered Alternate Email : "+altEmail);
	}

	public ExploreVerification enterAllDetailsForRegistration(String firstName, String middleName, String lastName, String username, String password,
			String otp, String receiveCommunication, String dob, String gender, String language, String altContactNumber, String altEmail) {
		log("Enter all User details.");
		enterFirstname(firstName);
		enterMiddleName(middleName);
		enterLastName(lastName);
		enterUsername(username);
		enterPassword(password);
		enterOTP(otp);
		recieveCommunication(receiveCommunication);
		enterDob(dob);
		selectGender(gender);
		selectPreferredLanguage(language);
		enterAltContactNumber(altContactNumber);
		enterAlternateEmail(altEmail);
		clickOnRegisterBtn();

		return new ExploreVerification(driver);
	}

	@FindBy(xpath="//section[contains(@class,'explore-plans')]//img[contains(@class,'arrow-icon')]")
	WebElement explorePlansArrow;

	public ExploreVerification clickOnExplorePlans() {
		log("Click on 'Explore our plans' button.");
		Common.scrollToMiddle(driver, explorePlansArrow);
		Common.clickableElement(explorePlansArrow, driver);
		Common.captureScreenshot(driver);
		Common.clickOn(driver, explorePlansArrow);

		return new ExploreVerification(driver);
	}
}