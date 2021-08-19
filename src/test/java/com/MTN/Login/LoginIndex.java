package com.MTN.Login;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.STL.Init.SeleniumInit;
import com.STL.Utility.TestData;

public class LoginIndex extends SeleniumInit {

	@Test(description="TC-01 :: Order a plan from Filtered List with chargeable number")
	public void purchasePlanFromFilterChargeableNumber() throws Exception {

		int numOfFailure=0;

		commonIndexPage.startTest(); 

		exploreIndexPage.clickOnLogin();

		ArrayList<String> loginDetails = TestData.getColumnData("data/MTN_Details.xlsx", "Login", "TC_01");
		String username = loginDetails.get(0);
		String password = loginDetails.get(1);

		loginIndexPage.enterLoginDetails(username, password);

		loginIndexPage.goToDashboard();

		exploreIndexPage.clickOnExplorePlans();

//	Click and apply filter to find a plan

		ArrayList<String> planDetails = TestData.getColumnData("data/MTN_Details.xlsx", "Plan", "SP_02_TC_01");
		String planType = planDetails.get(0);
		String plan = planDetails.get(1);
		String iccID = planDetails.get(3);

		commonIndexPage.openPlan(plan);

		commonIndexPage.buyPlan();

		commonIndexPage.enterSimAndValidate(iccID);

		commonIndexPage.clickOnProceed();

		commonIndexPage.clickOnCheckout();

//		commonIndexPage.secureCheckoutDetailsForLoggedInUser(planType);

		if (numOfFailure > 0) {
			Assert.assertTrue(false);
		}
	}

	@Test(description="TC-02 :: Purchase plan after sorting and search number on checkout")
	public void purchasePlanFromSortSearchNumber() throws Exception {

		int numOfFailure=0;

		commonIndexPage.startTest(); 

		exploreIndexPage.clickOnLogin();

		ArrayList<String> loginDetails = TestData.getColumnData("data/MTN_Details.xlsx", "Login", "TC_01");
		String username = loginDetails.get(0);
		String password = loginDetails.get(1);

		loginIndexPage.enterLoginDetails(username, password);

		exploreIndexPage.clickOnExplorePlans();

//		Apply sort to find a plan

		ArrayList<String> planDetails = TestData.getColumnData("data/MTN_Details.xlsx", "Plan", "SP_02_TC_02");
		String planType = planDetails.get(0);
		String plan = planDetails.get(1);
		String iccID = planDetails.get(3);

		commonIndexPage.openPlan(plan);

		commonIndexPage.buyPlan();

		commonIndexPage.enterSimAndValidate(iccID);

		commonIndexPage.clickOnProceed();

		commonIndexPage.clickOnCheckout();

//		commonIndexPage.secureCheckoutDetailsForLoggedInUser(planType);

		if (numOfFailure > 0) {
			Assert.assertTrue(false);
		}
	}

	@Test(description="TC-03 :: Reset Password using Link > Purchase Best Selling plan > View Subscription Details")
	public void resetPwdLnkBestSellingSubscriptionDetail() throws Exception {

		int numOfFailure=0;

		commonIndexPage.startTest(); 

		exploreIndexPage.clickOnLogin();

		ArrayList<String> loginDetails = TestData.getColumnData("data/MTN_Details.xlsx", "Login", "TC_01");
		String username = loginDetails.get(0);
		String password = loginDetails.get(1);

//		loginIndexPage.enterUsername(username);
//		loginIndexPage.clickOnForgotPassword();
//		loginIndexPage.clickOnResetPswdUsingOtpBtn();

//		Reset password using OTP
		
		loginIndexPage.enterLoginDetails(username, password);

		exploreVerification.verifyNameOnHeader("Hi");

		ArrayList<String> planDetails = TestData.getColumnData("data/MTN_Details.xlsx", "Plan", "TC_02");
		String planType = planDetails.get(0);
		String plan = planDetails.get(1);
		String iccID = planDetails.get(3);

		loginIndexPage.selectBestSellingPlan(plan);

		commonIndexPage.buyPlan();

		commonIndexPage.enterSimAndValidate(iccID);

		commonIndexPage.clickOnProceed();

		commonIndexPage.clickOnCheckout();

//		commonIndexPage.secureCheckoutDetailsForLoggedInUser(planType);
//		loginIndexPage.goToDashboard();
/*
//		Observe subscription details for user
//		Click on name on the header and go to Dashboard
//		Observe subscription details for user
*/
		if (numOfFailure > 0) {
			Assert.assertTrue(false);
		}
	}
}