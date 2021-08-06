package com.MTN.Common;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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
		log("Select a plan to buy.");
		List<String> planName = new ArrayList<String>();
		Common.pause(2);
		do {
			List<WebElement> plans = driver.findElements(By.xpath("//div[contains(@class,'Plan')]//h4"));
			for(WebElement e : plans){
				planName.add(e.getText());
			}
			if(!planName.contains(plan)) {
				try {
					if(viewMoreBtn.isDisplayed()) {
						Common.clickOn(driver, viewMoreBtn);
						Common.pause(2);
					} else {
						break;
					}
				} catch(Exception e) {
					break;
				}
			}
		} while(!planName.contains(plan));

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
		log("Click on Buy Now button.");
//		Common.scrollToMiddle(driver, buyNowBtn);
		Common.clickableElement(buyNowBtn, driver);
		Common.scrollToMiddle(driver, buyNowBtn);
		Common.captureScreenshot(driver);
		Common.clickOn(driver, buyNowBtn);

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

		return new CommonIndexPage(driver);
	}

	public CommonIndexPage enterSimAndValidate(String simNum) {
		log("Enter SIM Number and click on Validate.");
		enterSimNumber(simNum);
		clickOnValidateBtn();

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//button[contains(@class,'proceed')]")
	WebElement proceedBtn;

	public CommonIndexPage clickOnProceed() {
		log("Click on 'Proceed' button.");
		Common.clickableElement(proceedBtn, driver);
		Common.clickOn(driver, proceedBtn);

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//div[contains(@class,'checkout-btn')]//div[contains(@class,'checkout-btn')]")
	WebElement checkoutBtn;

	public CommonIndexPage clickOnCheckout() {
		log("Click on 'Checkout' button.");
		Common.clickableElement(checkoutBtn, driver);
		Common.captureScreenshot(driver);
		Common.clickOn(driver, checkoutBtn);

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

	public void selectCategory(String category) throws IOException {
		String customerCategoryLbl = TestData.getValueFromConfig("message.properties", "CustomerCategory");
		WebElement categoryDrpdwn = driver.findElement(By.xpath("//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-basicInfo')]//label[contains(text(),'"+customerCategoryLbl+"')]/..//input/../following-sibling::span"));
		Common.scrollToMiddle(driver, categoryDrpdwn);
		Common.clickableElement(categoryDrpdwn, driver);
		Common.clickOn(driver, categoryDrpdwn);

		WebElement categoryName = driver.findElement(By.xpath("//div[contains(@class,'rc-virtual-list-holder-inner')]//div[contains(@class,'content') and contains(text(),'"+category+"')]"));
		Common.clickableElement(categoryName, driver);
		Common.clickOn(driver, categoryName);
		log("Selected Consumer Category : "+ category);
	}

	public void selectIdType(String idType) throws IOException {
		String personalIdLbl = TestData.getValueFromConfig("message.properties", "IDType");
		WebElement idTypeDrpdwn = driver.findElement(By.xpath("//div[@id='accordion__panel-customerRegistration']//div[contains(@id,'accordion__panel-basicInfo')]//label[contains(text(),'"+personalIdLbl+"')]/..//input/../following-sibling::span"));
		Common.clickableElement(idTypeDrpdwn, driver);
		Common.clickOn(driver, idTypeDrpdwn);

		WebElement idTypeName = driver.findElement(By.xpath("//div[contains(@class,'rc-virtual-list-holder-inner')]//div[contains(@class,'content') and contains(text(),'"+idType+"')]"));
		Common.scrollToMiddle(driver, idTypeName);
		Common.clickableElement(idTypeName, driver);
		Common.clickOn(driver, idTypeName);
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

	public CommonIndexPage fillUpBasicDetails() throws IOException {
		ArrayList<String> basicMandatoryDetails = TestData.getColumnData("ReadDataFromExcel/MTN.xlsx", "Basic", "TestData");
		String category = basicMandatoryDetails.get(1);
		String idType = basicMandatoryDetails.get(2);

		ArrayList<String> basicDetails = TestData.getColumnData("ReadDataFromExcel/MTN_Random.xlsx", "Basic", "TestData");
		String idValue = basicDetails.get(0);
		String issueDate = basicDetails.get(1);
		String expiryDate = basicDetails.get(2);

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

	public CommonIndexPage fillUpAddressDetails() throws IOException {
		ArrayList<String> addressDetails = TestData.getColumnData("ReadDataFromExcel/MTN_Random.xlsx", "Address", "TestData");
		String squareNum = addressDetails.get(0);
		String houseNum = addressDetails.get(1);
		String street = addressDetails.get(2);
		String zipcode = addressDetails.get(3);

		ArrayList<String> addressMandatoryDetails = TestData.getColumnData("ReadDataFromExcel/MTN.xlsx", "Address", "TestData");
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

	public CommonIndexPage fillUpContactDetails() throws IOException {
		ArrayList<String> contactDetails = TestData.getColumnData("ReadDataFromExcel/MTN_Random.xlsx", "Contact", "TestData");
		String contactName = contactDetails.get(0);
		String phoneNumber = contactDetails.get(1);

		ArrayList<String> contactMandatoryDetails = TestData.getColumnData("ReadDataFromExcel/MTN.xlsx", "Contact", "TestData");
		String receiveCommunication = contactMandatoryDetails.get(0);

		enterContactName(contactName);
		enterPhoneNumber(phoneNumber);
		receiveCommunicationContact(receiveCommunication);
		clickOnSaveContactDetailsBtn();

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="(//div[contains(@id,'accordion__panel-uploadDocument')]//button)[1]")
	WebElement takePhotoBtn;

	public void takePhoto() {
		Common.clickableElement(takePhotoBtn, driver);
		Common.jsClick(driver, takePhotoBtn);
		log("Captured Photo");
	}

	@FindBy(xpath="//div[contains(@id,'accordion__panel-uploadDocument')]//input[@name='personalIdFront']")
	WebElement attachIdFrontImg;

	public void uploadFrontID(String frontId) {
		File file = new File(frontId);
		String frontFilePath = file.getAbsolutePath();//Common.filePath(frontId);
		attachIdFrontImg.sendKeys(frontFilePath);
		log("Uploaded Front ID.");
	}

	@FindBy(xpath="//div[contains(@id,'accordion__panel-uploadDocument')]//input[@name='personalIdBack']")
	WebElement attachIdBackImg;

	private void uploadBackID(String backId) {
		File file = new File(backId);
		String backFilePath = file.getAbsolutePath();//Common.filePath(backId);
		attachIdBackImg.sendKeys(backFilePath);
		log("Uploaded Back ID.");
	}

	@FindBy(xpath="//div[contains(@id,'accordion__panel-uploadDocument')]//div[contains(@class,'upBtn')]/button")
	WebElement nextBtn;

	public CommonIndexPage clickNextBtn() {
		Common.clickableElement(nextBtn, driver);
		Common.clickOn(driver, nextBtn);
		log("Clicked on Next button.");
		Common.pause(3);

		return new CommonIndexPage(driver);
	}

	public CommonIndexPage uploadDocuments() throws IOException {
		ArrayList<String> documentDetails = TestData.getColumnData("ReadDataFromExcel/MTN.xlsx", "UploadDocuments", "TestData");
		String frontId = documentDetails.get(0);
		String backId = documentDetails.get(1);

		takePhoto();
		uploadFrontID(frontId);
		uploadBackID(backId);
		clickNextBtn();

		return new CommonIndexPage(driver);
	}

	@FindBy(xpath="//div[@id='accordion__panel-numberSelection']//div[contains(@class,'number')]/b")
	WebElement selectedNumber;

	@FindBy(xpath="(//div[@id='accordion__panel-numberSelection']//button[@type='submit'])[2]")
	WebElement saveNumberSelectionBtn;

	public CommonIndexPage selectNumber() {
		log("Selected Number: "+selectedNumber.getText().split(":")[1].trim());
		Common.waitForElement(saveNumberSelectionBtn, driver);
		Common.clickOn(driver, saveNumberSelectionBtn);
		log("Clicked Save button.");

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

	public CommonIndexPage enterBillingDetails() throws IOException {
		String billingContactNumber = TestData.getColumnData("ReadDataFromExcel/MTN_Random.xlsx", "BillingAccount", "TestData").get(0);

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
		String acceptTnC = TestData.getColumnData("ReadDataFromExcel/MTN.xlsx", "TnC", "TestData").get(0);

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

	public CommonIndexPage reviewOrderSummary() throws Exception {
		String total = totalAmount.getText();
		TestData.setCellData("ReadDataFromExcel/MTN_Random.xlsx","Payment", total, 1, 1);
		log("Total Amount: "+total);

		Common.scrollToMiddle(driver, NextOrderSummaryBtn);
		Common.clickableElement(NextOrderSummaryBtn, driver);
		Common.clickOn(driver, NextOrderSummaryBtn);
		log("Clicked on Next button.");

		return new CommonIndexPage(driver);
	}

	public CommonIndexPage secureCheckoutDetails(String planType) throws Exception {
		Common.captureScreenshot(driver);
		log("Fill up Basic Details");
		fillUpBasicDetails();

		log("Fill up Address Details");
		fillUpAddressDetails();

		log("Fill up Contact Details");
		fillUpContactDetails();

		log("Upload Documents");
		uploadDocuments();

		log("Select Number");
		selectNumber();

		if(planType.equalsIgnoreCase("postpaid")) {
			log("Enter Billing Details");
			enterBillingDetails();
		}

		log("Accept Terms & Conditions");
		acceptTermsAndConditions();

		log("Review Order Summary");
		reviewOrderSummary();

		return new CommonIndexPage(driver);
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