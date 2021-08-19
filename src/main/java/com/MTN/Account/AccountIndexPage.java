package com.MTN.Account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.STL.Init.AbstractPage;
import com.STL.Init.Common;

public class AccountIndexPage extends AbstractPage {

	public AccountIndexPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//label[text()='Change Password']/../img")
	WebElement changePasswordLbl;

	public void clickOnChangePassword() {
		Common.clickableElement(changePasswordLbl, driver);
		Common.clickOn(driver, changePasswordLbl);
		log("Clicked Change Password.");
	}

	@FindBy(xpath="//input[@name='currentPassword']")
	WebElement currPswdTxtField;

	public void enterCurrentPassword(String currentPassword) {
		Common.type(currPswdTxtField, currentPassword);
		log("Entered Current Password: "+ currentPassword);
	}

	@FindBy(xpath="//input[@name='newPassword']")
	WebElement newPswdTxtField;

	public void enterNewPassword(String newPassword) {
		Common.type(newPswdTxtField, newPassword);
		log("Entered New Password: "+ newPassword);
	}

	@FindBy(xpath="//input[@name='confirmPassword']")
	WebElement cnfrmPswdTxtField;

	public void enterConfirmPassword(String cnfrmPassword) {
		Common.type(cnfrmPswdTxtField, cnfrmPassword);
		log("Entered Confirm Password: "+ cnfrmPassword);
	}

	@FindBy(xpath="//button[text()='Update']")
	WebElement updateBtn;

	public void clickOnUpdateBtn() {
		Common.clickableElement(updateBtn, driver);
		Common.clickOn(driver, updateBtn);
		log("Clicked on Update button.");
	}

	@FindBy(xpath="//button[text()='Cancel']")
	WebElement cancelBtn;

	public void clickOnCancelBtn() {
		Common.clickableElement(cancelBtn, driver);
		Common.clickOn(driver, cancelBtn);
		log("Clicked on cancel button.");
	}
}