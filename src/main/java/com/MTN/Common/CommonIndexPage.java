package com.MTN.Common;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.STL.Init.AbstractPage;
import com.STL.Init.Common;
import com.STL.Utility.TestData;

public class CommonIndexPage extends AbstractPage {

	public CommonIndexPage(WebDriver driver) {
		super(driver);
	}

	public void startTest() {
		log("Open URL : "+testUrl);
	}

	@FindBy(xpath="//button[contains(@class,'Plan')]")
	WebElement viewMoreBtn;

	public CommonIndexPage openPlan(String plan) throws IOException {
		while(driver.findElements(By.xpath("//h4[text()='"+plan+"']/following-sibling::a")).size()<=0) {
			if (driver.findElements(By.xpath("//button[contains(@class,'Plan')]")).size()>0)
				Common.clickOn(driver, viewMoreBtn);
			else
				slog("No more plans to display.");
				break;
		}

		String selectPlan = "//h4[text()='"+plan+"']/following-sibling::a";
		Common.scrollToMiddle(driver, driver.findElement(By.xpath("//h4[text()='"+plan+"']")));
		Common.pause(1);
		Common.clickableElement(driver.findElement(By.xpath(selectPlan)), driver);
		Common.captureScreenshot(driver);
		Common.jsClick(driver, driver.findElement(By.xpath(selectPlan)));
		log("Selected Plan : "+plan);

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//button[contains(@class,'customBtn')]")
	WebElement buyNowBtn;

	public CommonIndexPage buyPlan() throws IOException {
		Common.clickableElement(buyNowBtn, driver);
		Common.scrollToMiddle(driver, buyNowBtn);
		Common.captureScreenshot(driver);
		Common.clickOn(driver, buyNowBtn);
		log("Clicked on Buy Now button.");

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//input[@name='simNumber']")
	WebElement simNumberTxtField;

	public void enterSimNumber(String simNum) {
		Common.waitForElement(simNumberTxtField, driver);
		simNumberTxtField.sendKeys(simNum);
		log("Entered ICCID : "+ simNum);		
	}

	@FindBy(xpath="//button[@type='submit']")
	WebElement validateSimNumBtn;

	public CommonIndexPage clickOnValidateBtn() {
		Common.clickableElement(validateSimNumBtn, driver);
		Common.clickOn(driver, validateSimNumBtn);
		log("Clicked on Validate button.");

		return new CommonIndexPage(driver);
	}

	public CommonIndexPage enterSimAndValidate(String simNum) {
		enterSimNumber(simNum);
		clickOnValidateBtn();

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//button[contains(@class,'proceed')]")
	WebElement proceedBtn;

	public CommonIndexPage clickOnProceed() {
		Common.clickableElement(proceedBtn, driver);
		Common.clickOn(driver, proceedBtn);
		log("Clicked on Proceed button.");

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//div[contains(@class,'checkout-btn')]//div[contains(@class,'checkout-btn')]")
	WebElement checkoutBtn;

	public CommonIndexPage clickOnCheckout() {
		Common.clickableElement(checkoutBtn, driver);
		Common.captureScreenshot(driver);
		Common.clickOn(driver, checkoutBtn);
		log("Clicked on Checkout button.");

		return new CommonIndexPage(driver);
	}

	public String getDate(int fromYear, int fromMonth, int fromDate, int toYear, int toMonth, int toDate) {
		LocalDate from = LocalDate.of(fromYear, fromMonth, fromDate);
		LocalDate to = LocalDate.of(toYear, toMonth, toDate);
		long days = from.until(to, ChronoUnit.DAYS);
		long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
		LocalDate randomDate = from.plusDays(randomDays);

		return randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public String dateAbove18Yrs() {
		return getDate(1960, 1, 2, 2003, 1, 1);
	}

	public String issueDate() {
		return getDate(2015, 1, 1, 2021, 1, 1);
	}

	public String expiryDate() {
		return getDate(2023, 1, 2, 2026, 1, 1);
	}

	public void selectBasicDetailItem(String item, String itemLbl ) throws IOException {
		WebElement itemDrpdwn = driver.findElement(By.xpath("//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-basicInfo')]//label[contains(text(),'"+itemLbl+"')]/..//input/../following-sibling::span"));
		Common.scrollToMiddle(driver, itemDrpdwn);
		Common.clickableElement(itemDrpdwn, driver);
		Common.clickOn(driver, itemDrpdwn);

		WebElement itemName = driver.findElement(By.xpath("//div[contains(@class,'rc-virtual-list-holder-inner')]//div[contains(@class,'content') and contains(text(),'"+item+"')]"));
		Common.clickableElement(itemName, driver);
		Common.clickOn(driver, itemName);
	}

	public void selectCategory(String category) throws IOException {
		String customerCategoryLbl = TestData.getValueFromConfig("message.properties", "CustomerCategory");
		selectBasicDetailItem(category, customerCategoryLbl);
		log("Selected Consumer Category : "+ category);
	}

	public void selectIdType(String idType) throws IOException {
		String personalIdLbl = TestData.getValueFromConfig("message.properties", "IDType");
		selectBasicDetailItem(idType, personalIdLbl);
		log("Selected ID Type : "+ idType);
	}

	@FindBy(xpath="//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-basicInfo')]//input[@name='personalIdValue']")
	WebElement idValueTxtField;

	public void enterIdValue(String idValue) {
		Common.waitForElement(idValueTxtField, driver);
		Common.forcedClear(idValueTxtField);
		idValueTxtField.sendKeys(idValue);
		idValueTxtField.sendKeys(Keys.TAB);
		log("Entered ID Value: "+ idValue);
	}

	public void enterIssueDate(String issueDate) throws IOException {
		String issueDateLbl = TestData.getValueFromConfig("message.properties", "IssueDateLbl");
		WebElement issueDateTxtField = driver.findElement(By.xpath("//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-basicInfo')]//label[contains(text(),'"+issueDateLbl+"')]/../..//input"));

		Common.waitForElement(issueDateTxtField, driver);
		issueDateTxtField.sendKeys(issueDate);
		issueDateTxtField.sendKeys(Keys.ENTER);
		log("Entered Issue Date : "+issueDate);
	}

	public void enterExpiryDate(String expiryDate) throws IOException {
		String expiryDateLbl = TestData.getValueFromConfig("message.properties", "ExpiryDateLbl");
		WebElement expiryDateTxtField = driver.findElement(By.xpath("//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-basicInfo')]//label[contains(text(),'"+expiryDateLbl+"')]/../..//input"));

		Common.waitForElement(expiryDateTxtField, driver);
		expiryDateTxtField.sendKeys(expiryDate);
		expiryDateTxtField.sendKeys(Keys.ENTER);
		log("Entered Expiry Date : "+expiryDate);
	}

	@FindBy(xpath="//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-basicInfo')]//button")
	WebElement saveBasicDetailsBtn;

	public CommonIndexPage clickOnSaveBasicDetailsBtn() {
		Common.clickableElement(saveBasicDetailsBtn, driver);
		Common.clickOn(driver, saveBasicDetailsBtn);
		log("Clicked on Save button.");

		return new CommonIndexPage(driver);
	}

	public CommonIndexPage fillUpBasicDetails(String idValue, String issueDate, String expiryDate) throws IOException {
		Common.captureScreenshot(driver);
		log("Fill up Basic Details");
		ArrayList<String> basicMandatoryDetails = TestData.getColumnData("data/MTN_Details.xlsx", "Basic", "TestData");
		String category = basicMandatoryDetails.get(1);
		String idType = basicMandatoryDetails.get(2);

//		ArrayList<String> basicDetails = TestData.getColumnData("data/MTN_RuntimeDetails.xlsx", "Basic", "TestData");
//		String idValue = basicDetails.get(0);
//		String issueDate = basicDetails.get(1);
//		String expiryDate = basicDetails.get(2);

		selectCategory(category);
		selectIdType(idType);
		enterIdValue(idValue);
		enterIssueDate(issueDate);
		enterExpiryDate(expiryDate);
		clickOnSaveBasicDetailsBtn();

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-addressInfo')]//input[@name='squareNumber']")
	WebElement squareNumTxtField;

	public void enterSquareNumber(String squareNum) {
		Common.waitForElement(squareNumTxtField, driver);
		squareNumTxtField.sendKeys(squareNum);
		log("Entered Square Number : "+ squareNum);
	}

	@FindBy(xpath="//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-addressInfo')]//input[@name='houseNumber']")
	WebElement houseNumTxtField;

	public void enterHouseNumber(String houseNum) {
		Common.waitForElement(houseNumTxtField, driver);
		houseNumTxtField.sendKeys(houseNum);
		log("Entered House number : "+ houseNum);
	}

	@FindBy(xpath="//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-addressInfo')]//input[@name='streetNumber']")
	WebElement streetNumTxtField;

	public void enterStreet(String street) {
		Common.waitForElement(streetNumTxtField, driver);
		streetNumTxtField.sendKeys(street);
		log("Entered Street : "+ street);
	}

	@FindBy(xpath="//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-addressInfo')]//input[@name='district']")
	WebElement districtTxtField;

	public void enterDistrict(String district) {
		Common.waitForElement(districtTxtField, driver);
		districtTxtField.sendKeys(district);
		log("Entered District : "+ district);
	}

	public void selectCity(String city) throws IOException {
		String cityLbl = TestData.getValueFromConfig("message.properties", "CityLbl");
		WebElement cityDrpdwn = driver.findElement(By.xpath("//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-addressInfo')]//label[contains(text(),'"+cityLbl+"')]/..//input/../following-sibling::span"));
		Common.clickableElement(cityDrpdwn, driver);
		Common.clickOn(driver, cityDrpdwn);

		WebElement cityName = driver.findElement(By.xpath("//div[contains(@class,'rc-virtual-list-holder-inner')]//div[contains(@class,'content') and contains(text(),'"+city+"')]"));
		Common.clickableElement(cityName, driver);
		Common.clickOn(driver, cityName);
		log("Selected City : "+ city);
	}

	@FindBy(xpath="//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-addressInfo')]//input[@name='zipcode']")
	WebElement zipcodeTxtField;

	public void enterZipcode(String zipcode) {
		Common.waitForElement(zipcodeTxtField, driver);
		zipcodeTxtField.sendKeys(zipcode);
		log("Entered Zipcode : "+ zipcode);
	}

	@FindBy(xpath="//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-addressInfo')]//button")
	WebElement saveAddressDetailsBtn;

	public CommonIndexPage clickOnSaveAddressDetailsBtn() {
		Common.clickableElement(saveAddressDetailsBtn, driver);
		Common.clickOn(driver, saveAddressDetailsBtn);
		log("Clicked on Save button");

		return new CommonIndexPage(driver);
	}

	public CommonIndexPage fillUpAddressDetails(String squareNum, String houseNum, String street, String zipcode) throws IOException {
		log("Fill up Address Details");
		/*
		 * ArrayList<String> addressDetails =
		 * TestData.getColumnData("data/MTN_RuntimeDetails.xlsx", "Address",
		 * "TestData"); String squareNum = addressDetails.get(0); String houseNum =
		 * addressDetails.get(1); String street = addressDetails.get(2); String zipcode
		 * = addressDetails.get(3);
		 */

		ArrayList<String> addressMandatoryDetails = TestData.getColumnData("data/MTN_Details.xlsx", "Address", "TestData");
		String district = addressMandatoryDetails.get(0);
		String city = addressMandatoryDetails.get(1);

		enterSquareNumber(squareNum);
		enterHouseNumber(houseNum);
		enterStreet(street);
		enterDistrict(district);
		selectCity(city);
		enterZipcode(zipcode);
		clickOnSaveAddressDetailsBtn();

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-contactInfo')]//input[@name='contactName']")
	WebElement contactNameTxtField;

	public void enterContactName(String contactName) {
		Common.waitForElement(contactNameTxtField, driver);
		contactNameTxtField.sendKeys(contactName);
		log("Entered Contact Name : "+contactName);
	}

	@FindBy(xpath="//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-contactInfo')]//input[@name='alternateContact']")
	WebElement phoneNumberTxtField;

	public void enterPhoneNumber(String phoneNumber) {
		Common.waitForElement(phoneNumberTxtField, driver);
		phoneNumberTxtField.sendKeys(phoneNumber);
		log("Entered Phone Number : "+phoneNumber);
	}

	@FindBy(xpath="//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-contactInfo')]//input[@type='checkbox']/..")
	WebElement receiveCommunicationContactChkBox;

	public void receiveCommunicationContact(String receiveCommunication) {
		Common.checkChkBox(receiveCommunicationContactChkBox);
		log("Receive Communication : "+receiveCommunication);
	}

	@FindBy(xpath="//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-contactInfo')]//button")
	WebElement saveContactDetailsBtn;

	public CommonIndexPage clickOnSaveContactDetailsBtn() {
		Common.clickableElement(saveContactDetailsBtn, driver);
		Common.clickOn(driver, saveContactDetailsBtn);
		log("Clicked on Save button");

		return new CommonIndexPage(driver);
	}

	public CommonIndexPage fillUpContactDetails(String contactName, String phoneNumber) throws IOException {
		log("Fill up Contact Details");
		/*
		 * ArrayList<String> contactDetails =
		 * TestData.getColumnData("data/MTN_RuntimeDetails.xlsx", "Contact",
		 * "TestData"); String contactName = contactDetails.get(0); String phoneNumber =
		 * contactDetails.get(1);
		 */

		ArrayList<String> contactMandatoryDetails = TestData.getColumnData("data/MTN_Details.xlsx", "Contact", "TestData");
		String receiveCommunication = contactMandatoryDetails.get(0);

		enterContactName(contactName);
		enterPhoneNumber(phoneNumber);
		receiveCommunicationContact(receiveCommunication);
		clickOnSaveContactDetailsBtn();

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//div[contains(@id,'accordion__panel-uploadDocument')]//span/parent::button")
	WebElement takePhotoBtn;

	public void takePhoto() {
		Common.clickableElement(takePhotoBtn, driver);
		Common.jsClick(driver, takePhotoBtn);
		log("Captured Photo.");
	}

	@FindBy(xpath="//div[contains(@id,'accordion__panel-uploadDocument')]//span/parent::button/following-sibling::button")
	WebElement uploadPhotoBtn;

	public void uploadPhoto() {
		Common.clickableElement(uploadPhotoBtn, driver);
		Common.jsClick(driver, uploadPhotoBtn);
		log("Photo Uploaded.");
	}

	public void uploadLivePhoto() {
		if(driver.findElements(By.xpath("//div[contains(@id,'accordion__panel-uploadDocument')]//span/parent::button")).size()>0) {
			takePhoto();
			uploadPhoto();
		}
	}

	private void uploadID(String id, String lbl) {
		File file = new File(id);
		String filePath = file.getAbsolutePath();

		WebElement attachIdImg = driver.findElement(By.xpath("//div[contains(@id,'accordion__panel-uploadDocument')]//label[text()='"+lbl+"']/following-sibling::div//input[@type='file']"));
		attachIdImg.sendKeys(filePath);

		WebElement uploadIdBtn = driver.findElement(By.xpath("//div[contains(@id,'accordion__panel-uploadDocument')]//label[text()='"+lbl+"']/following-sibling::div//button"));
		Common.clickableElement(uploadIdBtn, driver);
		Common.clickOn(driver, uploadIdBtn);
	}

	public void uploadFrontID(String frontId) throws IOException {
		String frontIdLbl = TestData.getValueFromConfig("message.properties", "PersonalIdFront");
		uploadID(frontId, frontIdLbl);
		log("Uploaded Front ID.");
	}

	private void uploadBackID(String backId) throws IOException {
		String backIdLbl = TestData.getValueFromConfig("message.properties", "PersonalIdBack");
		uploadID(backId, backIdLbl);
		log("Uploaded Back ID.");
	}

	@FindBy(xpath="//div[contains(@id,'accordion__panel-uploadDocument')]//div[contains(@class,'upBtn')]/button")
	WebElement nextBtn;

	public CommonIndexPage clickNextBtn() {
		Common.pause(20);
		Common.scrollToMiddle(driver, nextBtn);
		Common.clickableElement(nextBtn, driver);
		Common.jsClick(driver, nextBtn);
		log("Clicked on Next button.");

		return new CommonIndexPage(driver);
	}

	public CommonIndexPage uploadDocuments() throws IOException {
		log("Upload Documents");
		ArrayList<String> documentDetails = TestData.getColumnData("data/MTN_Details.xlsx", "UploadDocuments", "TestData");
		String frontId = documentDetails.get(0);
		String backId = documentDetails.get(1);

		uploadLivePhoto();
		uploadFrontID(frontId);
		uploadBackID(backId);
		clickNextBtn();

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//div[@id='accordion__panel-numberSelection']//li[text()='Free']")
	WebElement freeNumLbl;

	@FindBy(xpath="//div[@id='accordion__panel-numberSelection']//li[contains(text(),'GOLD')]")
	WebElement goldNumLbl;

	public String setRandomNumber() throws Exception {
		Common.scrollToMiddle(driver, goldNumLbl);
		Common.clickOn(driver, goldNumLbl);
		Common.waitForElement(driver.findElement(By.xpath("//div[@id='accordion__panel-numberSelection']//p/span")), driver);
		List<WebElement> numbers = driver.findElements(By.xpath("//div[@id='accordion__panel-numberSelection']//p/span"));
		List<String> numbersList = new ArrayList<String>();
		for(WebElement e : numbers){
			numbersList.add(e.getText());
		}
		Random rand = new Random();
	    String randomElement = numbersList.get(rand.nextInt(numbersList.size()));

//	    TestData.setCellData("data/MTN_RuntimeDetails.xlsx", "SelectNumber", randomElement, 1, 0);
	    return randomElement;
	}

	@FindBy(xpath="//div[@id='accordion__panel-numberSelection']//div[contains(@class,'number')]/b")
	WebElement selectedNumber;

	public CommonIndexPage selectNumber(String number) {
		WebElement searchedNumber = driver.findElement(By.xpath("//div[@id='accordion__panel-numberSelection']//p/span[contains(text(),'"+number+"')]"));
		Common.waitForElement(searchedNumber, driver);
		Common.clickOn(driver, searchedNumber);
		log("Selected Number: "+selectedNumber.getText().split(":")[1].trim());

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="(//div[@id='accordion__panel-numberSelection']//button[@type='submit'])[2]")
	WebElement saveNumberSelectionBtn;

	public CommonIndexPage clickOnSaveNumberBtn() {
		Common.scrollToMiddle(driver, saveNumberSelectionBtn);
		Common.waitForElement(saveNumberSelectionBtn, driver);
		Common.clickOn(driver, saveNumberSelectionBtn);
		log("Clicked Save button.");

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//div[@id='accordion__panel-numberSelection']//input[@id='search']")
	WebElement searchNumberBox;

	public void enterNumber(String number) throws IOException {
		Common.waitForElement(searchNumberBox, driver);
		Common.type(searchNumberBox, number);
		log("Searched Number: "+number);
	}

	@FindBy(xpath="(//div[@id='accordion__panel-numberSelection']//button[@type='submit'])[1]")
	WebElement searchNumberBtn;

	public CommonIndexPage clickOnSearchNumBtn() {
		Common.clickableElement(searchNumberBtn, driver);
		Common.clickOn(driver, searchNumberBtn);

		return new CommonIndexPage(driver);
	}

	public CommonIndexPage searchAndSelectNumber() throws Exception {
		log("Select Number");
		String number = setRandomNumber();
//		String number = TestData.getColumnData("data/MTN_RuntimeDetails.xlsx", "SelectNumber", "TestData").get(0);
		enterNumber(number);
		clickOnSearchNumBtn();
		selectNumber(number);
		clickOnSaveNumberBtn();

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//div[@id='accordion__panel-billingAccount']//input[@name='contactNumber']")
	WebElement billingContactNumberTxtField;

	public void enterBillingContactNumber(String billingContactNumber) {
		Common.waitForElement(billingContactNumberTxtField, driver);
		billingContactNumberTxtField.sendKeys(billingContactNumber);
		log("Entered billing contact number : "+billingContactNumber);
	}

	@FindBy(xpath="//div[@id='accordion__panel-billingAccount']//button[@type='submit']")
	WebElement saveBillingDetailsBtn;

	public CommonIndexPage clickOnSaveBillingDetailsBtn() {
		Common.clickableElement(saveBillingDetailsBtn, driver);
		Common.clickOn(driver, saveBillingDetailsBtn);
		log("Clicked on Save button.");

		return new CommonIndexPage(driver);
	}

	public CommonIndexPage enterBillingDetails(String billingContactNumber) throws IOException {
		log("Enter Billing Details");
//		String billingContactNumber = TestData.getColumnData("data/MTN_RuntimeDetails.xlsx", "BillingAccount", "TestData").get(0);

		enterBillingContactNumber(billingContactNumber);
		clickOnSaveBillingDetailsBtn();

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//div[@id='accordion__panel-termsAndConditions']//input/..")
	WebElement tncCheckbox;

	public void checkTnC(String acceptTnC) {
		if(acceptTnC.equalsIgnoreCase("yes")) {
			Common.waitForElement(tncCheckbox, driver);
			Common.checkChkBox(tncCheckbox);
			log("Checked T&C box.");
		}
	}

	@FindBy(xpath="//div[@id='accordion__panel-termsAndConditions']//button")
	WebElement saveTnCBtn;

	public CommonIndexPage saveTnC() {
		Common.clickableElement(saveTnCBtn, driver);
		Common.clickOn(driver, saveTnCBtn);
		log("Clicked on Save button.");

		return new CommonIndexPage(driver);
	}

	public CommonIndexPage acceptTermsAndConditions() throws IOException {
		log("Accept Terms & Conditions");
		String acceptTnC = TestData.getColumnData("data/MTN_Details.xlsx", "TnC", "TestData").get(0);

		checkTnC(acceptTnC);
		saveTnC();

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//div[@id='accordion__panel-orderSummery']//h3[@data-testid='package-plan']")
	WebElement planName;

	@FindBy(xpath="//div[@id='accordion__panel-orderSummery']//span[@data-testid='totalAmount']")
	WebElement totalAmount;

	@FindBy(xpath="//div[@id='accordion__panel-orderSummery']//button")
	WebElement NextOrderSummaryBtn;

	public String reviewOrderSummary() throws Exception {
		log("Review Order Summary");
		String total = totalAmount.getText();
//		TestData.setCellData("data/MTN_RuntimeDetails.xlsx","Payment", total, 1, 1);
		log("Total Amount: "+total);

		Common.scrollToMiddle(driver, NextOrderSummaryBtn);
		Common.clickableElement(NextOrderSummaryBtn, driver);
		Common.clickOn(driver, NextOrderSummaryBtn);
		log("Clicked on Next button.");

		return total;
	}

	public void doPayment() throws Exception {
		String paymentMethod = TestData.getColumnData("data/MTN_Details.xlsx", "PaymentMethod", "TestData").get(0);
		WebElement paymentMethodBtn = driver.findElement(By.xpath("//div[@id='accordion__panel-payment&PlaceOrder']//label[text()='"+paymentMethod+"']"));
		Common.scrollToMiddle(driver, paymentMethodBtn);
		Common.clickableElement(paymentMethodBtn, driver);
		Common.clickOn(driver, paymentMethodBtn);
		log("Selected Payment Method: "+paymentMethod);
	}

	@FindBy(xpath="//div[@id='accordion__panel-payment&PlaceOrder']//button[@type='submit']")
	WebElement payBtn;

	public CommonIndexPage clickOnPayBtn() {
		Common.clickableElement(payBtn, driver);
		Common.clickOn(driver, payBtn);
		log("Clicked Pay button.");

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//button[text()='Success Order']")
	WebElement successOrderBtn;

	public CommonIndexPage successOrder() throws Exception {
		Common.scrollToMiddle(driver, successOrderBtn);
		Common.clickableElement(successOrderBtn, driver);
		Common.clickOn(driver, successOrderBtn);
		log("Clicked on Success Order button.");

		return new CommonIndexPage(driver);
	}

	public CommonIndexPage paymentAndPlaceOrder() throws Exception {
		log("Payment & Place Order");
		doPayment();
		clickOnPayBtn();
		successOrder();

		return new CommonIndexPage(driver);
	}

	public String secureCheckoutDetailsForLoggedInUser(String planType, String billingContactNumber) throws Exception {
		uploadDocuments();
		searchAndSelectNumber();
		if(planType.equalsIgnoreCase("postpaid")) {
			enterBillingDetails(billingContactNumber);
		}
		acceptTermsAndConditions();
		String orderTotal = reviewOrderSummary();
		paymentAndPlaceOrder();

		return orderTotal;
	}

	public String secureCheckoutDetails(String planType, String idValue, String issueDate, String expiryDate, String squareNum, String houseNum, 
			String street, String zipcode, String contactName, String billingContactNumber) throws Exception {
		fillUpBasicDetails(idValue, issueDate, expiryDate);
		fillUpAddressDetails(squareNum, houseNum, street, zipcode);
		fillUpContactDetails(contactName, billingContactNumber);
		String orderTotal = secureCheckoutDetailsForLoggedInUser(planType, billingContactNumber);

		return orderTotal;
	}

	@FindBy(xpath="//p[contains(@class,'orderNumber')]/a")
	WebElement orderNumber;

	public String getOrderNumber() {
		Common.waitForElement(orderNumber, driver);

		return orderNumber.getText();
	}

	@FindBy(xpath="//p[contains(@class,'paymentID')]/a")
	WebElement paymentId;

	public String getPaymentID() {
		Common.waitForElement(paymentId, driver);

		return paymentId.getText();
	}
}