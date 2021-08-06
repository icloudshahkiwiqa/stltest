package com.MTN.Category;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.STL.Init.Common;
import com.STL.Init.SeleniumInit;
import com.STL.Utility.TestData;

public class CategoryIndex extends SeleniumInit{

	@Test(description="TC-03 :: Order a plan from Category > Sub Category")
	public void purchasePlanFromCategory() throws Exception {

		int numOfFailure=0;

		commonIndexPage.startTest();

		ArrayList<String> planDetails = TestData.getColumnData("ReadDataFromExcel/MTN.xlsx", "Plan", "TC_03");
		String planType = planDetails.get(0);
		String plan = planDetails.get(1);
		String mobilityType = planDetails.get(2);
		String iccID = planDetails.get(3);

		categoryIndexPage.goToMobility(mobilityType);

		commonIndexPage.openPlan(plan);

		commonIndexPage.buyPlan();

		commonIndexPage.enterSimAndValidate(iccID);

		commonIndexPage.clickOnProceed();

		commonIndexPage.clickOnCheckout();

		exploreIndexPage.clickOnRegisterHere();

		String firstName = "fname"+Common.generateRandomChars(4);
		String middleName = "mname"+Common.generateRandomChars(4);
		String lastName = "lname"+Common.generateRandomChars(4);
		String languageToExecute= TestData.getCellValue("ReadDataFromExcel/MTN.xlsx", "URL", 1, 1);
		if(languageToExecute.equalsIgnoreCase("Arabic") || languageToExecute.contentEquals("عربى")) {
			firstName = "محمد";
			middleName = "سلمان";
			lastName = "الفارسی";
		}
		String username = "test"+Common.generateRandomNumber(5)+"@yopmail.com";
		String dob = commonIndexPage.dateAbove18Yrs();
		String altContactNum = Common.generateRandomNumber(10);
		String altEmail = "testAlt"+Common.generateRandomNumber(4)+"@yopmail.com";

		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx", "Registration", firstName, 1, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx", "Registration", middleName, 2, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx", "Registration", lastName, 3, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx", "Registration", username, 4, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx", "Registration", dob, 5, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx", "Registration", altContactNum, 6, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx", "Registration", altEmail, 7, 1);

		ArrayList<String> regDetails = TestData.getColumnData("ReadDataFromExcel/MTN_Random.xlsx", "Registration", "TestData");
		firstName = regDetails.get(0);
		middleName = regDetails.get(1);
		lastName = regDetails.get(2);
		username = regDetails.get(3);
		dob = regDetails.get(4);
		altContactNum = regDetails.get(5);
		altEmail = regDetails.get(6);

		ArrayList<String> regMandatoryDetails = TestData.getColumnData("ReadDataFromExcel/MTN.xlsx", "Registration", "TestData");
		String password = regMandatoryDetails.get(0);
		String otp = regMandatoryDetails.get(1);
		String gender = regMandatoryDetails.get(2);
		String prefLang = regMandatoryDetails.get(3);
		String receiveCommunication = regMandatoryDetails.get(4);

		exploreIndexPage.enterAllDetailsForRegistration(firstName, middleName, lastName, username, password, otp, receiveCommunication, dob, gender,
				prefLang, altContactNum, altEmail);

		TestData.setCellData("ReadDataFromExcel/MTN_OrderDetails.xlsx", "Registration", username, 1, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_OrderDetails.xlsx", "Registration", password, 2, 1);

		String idValue = "2131321"+Common.generateRandomNumber(4);
		String issueDate = commonIndexPage.issueDate();
		String expiryDate = commonIndexPage.expiryDate();

		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx","Basic", idValue, 1, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx","Basic", issueDate, 2, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx","Basic", expiryDate, 3, 1);

		String squareNum = Common.generateRandomNumber(2);
		String houseNum = Common.generateRandomNumber(3);
		String street = Common.generateRandomChars(6);
		String zipcode = Common.generateRandomNumber(5);

		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx","Address", squareNum, 1, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx","Address", houseNum, 2, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx","Address", street, 3, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx","Address", zipcode, 4, 1);

		String contactName = Common.generateRandomChars(10);

		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx","Contact", contactName, 1, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx","Contact", altContactNum, 2, 1);

		if(planType.equalsIgnoreCase("postpaid")) {
			String billingContactNumber = altContactNum;
			TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx","BillingAccount", billingContactNumber, 1, 1);
		}

		commonIndexPage.secureCheckoutDetails(planType);

		String orderNumber = commonIndexPage.getOrderNumber();
		String paymentID = commonIndexPage.getPaymentID();
		String totalAmount = TestData.getColumnData("ReadDataFromExcel/MTN_Random.xlsx", "Payment", "TestData").get(0);

		log("Verifying order placed successfully.");
		if (commonVerification.verifyOrderPlaced()) {
			logStatus(1, " Success message is displayed like Order Successful! Your Order Number is: "+orderNumber+" and Payment Id is: "+paymentID);
		} else {
			logStatus(2, " Order Unsuccessful.");
			numOfFailure++;
		}

		TestData.setCellData("ReadDataFromExcel/MTN_OrderDetails.xlsx", "OrderDetails", username, 1, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_OrderDetails.xlsx", "OrderDetails", orderNumber, 2, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_OrderDetails.xlsx", "OrderDetails", paymentID, 3, 1);
		TestData.setCellData("ReadDataFromExcel/MTN_OrderDetails.xlsx", "OrderDetails", totalAmount, 4, 1);

		if (numOfFailure > 0) {
			Assert.assertTrue(false);
		}
	}
}