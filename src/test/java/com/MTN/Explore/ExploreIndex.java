package com.MTN.Explore;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.STL.Init.Common;
import com.STL.Init.SeleniumInit;
import com.STL.Utility.TestData;

public class ExploreIndex extends SeleniumInit{

	@Test(description="TC-01 :: Order a plan from the Plan list")
	public void guestUserPurchasePlan() throws Exception {

		int numOfFailure=0;

		commonIndexPage.startTest();

		exploreIndexPage.clickOnExplorePlans();
		String str = TestData.readExcelData("MTN_Details.xlsx");
		ArrayList<String> planDetails = TestData.getColumnData(str, "Plan", "TC_01");

		String planType = planDetails.get(0);
		String plan = planDetails.get(1);
		String iccID = planDetails.get(3);

		commonIndexPage.openPlan(plan);

		commonIndexPage.buyPlan();

		commonIndexPage.enterSimAndValidate(iccID);

		commonIndexPage.clickOnProceed();

		commonIndexPage.clickOnCheckout();

		exploreIndexPage.clickOnRegisterHere();

		String firstName = "fname"+Common.generateRandomChars(4);
		String middleName = "mname"+Common.generateRandomChars(4);
		String lastName = "lname"+Common.generateRandomChars(4);

		String languageToExecute= TestData.getColumnData("data/MTN_Details.xlsx", "URL", "Language").get(0);
		if(languageToExecute.equalsIgnoreCase("Arabic") || languageToExecute.contentEquals("عربى")) {
			firstName = "محمد";
			middleName = "سلمان";
			lastName = "الفارسی";
		}
		String username = "test"+Common.generateRandomNumber(5)+"@yopmail.com";
		String dob = commonIndexPage.dateAbove18Yrs();
		String altContactNum = Common.generateRandomNumber(10);
		String altEmail = "testAlt"+Common.generateRandomNumber(4)+"@yopmail.com";

		ArrayList<String> regMandatoryDetails = TestData.getColumnData("data/MTN_Details.xlsx", "Registration", "TestData");
		String password = regMandatoryDetails.get(0);
		String otp = regMandatoryDetails.get(1);
		String gender = regMandatoryDetails.get(2);
		String prefLang = regMandatoryDetails.get(3);
		String receiveCommunication = regMandatoryDetails.get(4);

		exploreIndexPage.enterAllDetailsForRegistration(firstName, middleName, lastName, username, password, otp, receiveCommunication, dob, gender,
				prefLang, altContactNum, altEmail);

		TestData.setCellData("data/MTN_OrderDetails.xlsx", "Registration", username, 1, 1);
		TestData.setCellData("data/MTN_OrderDetails.xlsx", "Registration", password, 2, 1);

		String idValue = Common.generateRandomChars(5)+Common.generateRandomNumber(6);
		String issueDate = commonIndexPage.issueDate();
		String expiryDate = commonIndexPage.expiryDate();

		String squareNum = Common.generateRandomNumber(2);
		String houseNum = Common.generateRandomNumber(3);
		String street = Common.generateRandomChars(6);
		String zipcode = Common.generateRandomNumber(5);

		String contactName = Common.generateRandomChars(10);

		String totalAmount = commonIndexPage.secureCheckoutDetails(planType, idValue, issueDate, expiryDate, squareNum, houseNum, street, zipcode, contactName,
				altContactNum);

		String orderNumber = commonIndexPage.getOrderNumber();
		String paymentID = commonIndexPage.getPaymentID();

		log("Verifying order placed successfully.");
		if (commonVerification.verifyOrderPlaced()) {
			logStatus(1, " Success message is displayed like Order Successful! Your Order Number is: "+orderNumber+" and Payment Id is: "+paymentID);
		} else {
			logStatus(2, " Order Unsuccessful.");
			numOfFailure++;
		}

		TestData.setCellData("data/MTN_OrderDetails.xlsx", "OrderDetails", username, 1, 1);
		TestData.setCellData("data/MTN_OrderDetails.xlsx", "OrderDetails", orderNumber, 2, 1);
		TestData.setCellData("data/MTN_OrderDetails.xlsx", "OrderDetails", paymentID, 3, 1);
		TestData.setCellData("data/MTN_OrderDetails.xlsx", "OrderDetails", totalAmount, 4, 1);

		if (numOfFailure > 0) {
			Assert.assertTrue(false);
		}
	}
}