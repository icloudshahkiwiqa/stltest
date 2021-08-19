package com.MTN.Search;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.STL.Init.Common;
import com.STL.Init.SeleniumInit;
import com.STL.Utility.TestData;

public class SearchIndex extends SeleniumInit{

	@Test(description="TC-02 :: Order a plan from the Search result")
	public void searchPlan() throws Exception {

		int numOfFailure=0;

		commonIndexPage.startTest();

		exploreIndexPage.clickOnLogin();

		exploreIndexPage.clickOnRegisterHere();

		String firstName = "fname"+Common.generateRandomChars(4);
		String middleName = "mname"+Common.generateRandomChars(4);
		String lastName = "lname"+Common.generateRandomChars(4);
		String languageToExecute= TestData.getCellValue("data/MTN_Details.xlsx", "URL", 1, 1);
		if(languageToExecute.equalsIgnoreCase("Arabic") || languageToExecute.contentEquals("عربى")) {
			firstName = "محمد";
			middleName = "سلمان";
			lastName = "الفارسی";
		}
		String username = "test"+Common.generateRandomNumber(5)+"@yopmail.com";
		String dob = commonIndexPage.dateAbove18Yrs();
		String altContactNum = Common.generateRandomNumber(10);
		String altEmail = "testAlt"+Common.generateRandomNumber(4)+"@yopmail.com";

		TestData.setCellData("data/MTN_RuntimeDetails.xlsx", "Registration", firstName, 1, 1);
		TestData.setCellData("data/MTN_RuntimeDetails.xlsx", "Registration", middleName, 2, 1);
		TestData.setCellData("data/MTN_RuntimeDetails.xlsx", "Registration", lastName, 3, 1);
		TestData.setCellData("data/MTN_RuntimeDetails.xlsx", "Registration", username, 4, 1);
		TestData.setCellData("data/MTN_RuntimeDetails.xlsx", "Registration", dob, 5, 1);
		TestData.setCellData("data/MTN_RuntimeDetails.xlsx", "Registration", altContactNum, 6, 1);
		TestData.setCellData("data/MTN_RuntimeDetails.xlsx", "Registration", altEmail, 7, 1);

//		ArrayList<String> regDetails = TestData.getColumnData("data/MTN_RuntimeDetails.xlsx", "Registration", "TestData");
//		firstName = regDetails.get(0);
//		middleName = regDetails.get(1);
//		lastName = regDetails.get(2);
//		username = regDetails.get(3);
//		dob = regDetails.get(4);
//		altContactNum = regDetails.get(5);
//		altEmail = regDetails.get(6);

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

		ArrayList<String> planDetails = TestData.getColumnData("data/MTN_Details.xlsx", "Plan", "TC_02");
		String planType = planDetails.get(0);
		String plan = planDetails.get(1);
		String iccID = planDetails.get(3);

		exploreVerification.verifyNameOnHeader(firstName);

		searchIndexPage.enterPlanToSearch(plan);

		searchIndexPage.selectPlan(plan);

		commonIndexPage.buyPlan();

		commonIndexPage.enterSimAndValidate(iccID);

		commonIndexPage.clickOnProceed();

		commonIndexPage.clickOnCheckout();

		String idValue = Common.generateRandomChars(5)+Common.generateRandomNumber(6);
		String issueDate = commonIndexPage.issueDate();
		String expiryDate = commonIndexPage.expiryDate();

//		TestData.setCellData("data/MTN_RuntimeDetails.xlsx","Basic", idValue, 1, 1);
//		TestData.setCellData("data/MTN_RuntimeDetails.xlsx","Basic", issueDate, 2, 1);
//		TestData.setCellData("data/MTN_RuntimeDetails.xlsx","Basic", expiryDate, 3, 1);

		String squareNum = Common.generateRandomNumber(2);
		String houseNum = Common.generateRandomNumber(3);
		String street = Common.generateRandomChars(6);
		String zipcode = Common.generateRandomNumber(5);

//		TestData.setCellData("data/MTN_RuntimeDetails.xlsx","Address", squareNum, 1, 1);
//		TestData.setCellData("data/MTN_RuntimeDetails.xlsx","Address", houseNum, 2, 1);
//		TestData.setCellData("data/MTN_RuntimeDetails.xlsx","Address", street, 3, 1);
//		TestData.setCellData("data/MTN_RuntimeDetails.xlsx","Address", zipcode, 4, 1);

		String contactName = Common.generateRandomChars(10);

//		TestData.setCellData("data/MTN_RuntimeDetails.xlsx","Contact", contactName, 1, 1);
//		TestData.setCellData("data/MTN_RuntimeDetails.xlsx","Contact", altContactNum, 2, 1);

//		if(planType.equalsIgnoreCase("postpaid")) {
			String billingContactNumber = altContactNum;
//			TestData.setCellData("data/MTN_RuntimeDetails.xlsx","BillingAccount", billingContactNumber, 1, 1);
//		}

//		commonIndexPage.secureCheckoutDetails(planType);

		String totalAmount = commonIndexPage.secureCheckoutDetails(planType, idValue, issueDate, expiryDate, squareNum, houseNum, street, zipcode, contactName,
				billingContactNumber);

		String orderNumber = commonIndexPage.getOrderNumber();
		String paymentID = commonIndexPage.getPaymentID();
//		String totalAmount = TestData.getColumnData("data/MTN_RuntimeDetails.xlsx", "Payment", "TestData").get(0);

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