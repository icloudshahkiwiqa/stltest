package com.MTN.Account;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.STL.Init.SeleniumInit;

public class AccountIndex extends SeleniumInit {

	@Test(description="TC-03 :: Reset Password using Link > Purchase Best Selling plan > View Subscription Details")
	public void goToMyAccount() throws Exception {

		int numOfFailure=0;

		commonIndexPage.startTest(); 

		exploreIndexPage.clickOnLogin();
/*
		ArrayList<String> loginDetails = TestData.getColumnData("ReadDataFromExcel/MTN.xlsx", "Login", "TC_01");
		String username = loginDetails.get(0);
		String password = loginDetails.get(1);

		loginIndexPage.enterUsername(username);
		loginIndexPage.clickOnForgotPassword();
		loginIndexPage.clickOnResetPswdUsingOtpBtn();
*/
//		Reset password using OTP
		
		loginIndexPage.enterLoginDetails("test42182@yopmail.com", "C10ud24@h2Ah");

		exploreVerification.verifyNameOnHeader("Hi");
/*
		ArrayList<String> planDetails = TestData.getColumnData("ReadDataFromExcel/MTN.xlsx", "Plan", "TC_02");
		String planType = planDetails.get(0);
		String plan = planDetails.get(1);
		String iccID = planDetails.get(3);

		loginIndexPage.selectBestSellingPlan("NEW_Silver2021");

		commonIndexPage.buyPlan();

		commonIndexPage.enterSimAndValidate(iccID);

		commonIndexPage.clickOnProceed();

		commonIndexPage.clickOnCheckout();

//		Save Basic Details
//		Save Address Details
//		Fill up Contact Details
//		Upload Documents
//		Search number and select
//		Fill up billing details
//		Accept Terms and Conditions
//		Review Order Summary
*/
		loginIndexPage.goToMyAccount();
//		Observe subscription details for user
//		Click on name on the header and go to Dashboard
//		Observe subscription details for user

		accountIndexPage.clickOnChangePassword();

		accountIndexPage.enterCurrentPassword("C10ud24@h2Ah");
		accountIndexPage.enterNewPassword("C10ud24@h2Ah");
		accountIndexPage.enterConfirmPassword("C10ud24@h2Ah");

		accountIndexPage.clickOnUpdateBtn();

		loginIndexPage.enterLoginDetails("test42182@yopmail.com", "C10ud24@h2Ah");

		exploreVerification.verifyNameOnHeader("Hi");

		if (numOfFailure > 0) {
			Assert.assertTrue(false);
		}
	}
}
