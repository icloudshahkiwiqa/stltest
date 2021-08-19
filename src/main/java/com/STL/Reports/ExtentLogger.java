package com.STL.Reports;

import com.STL.Init.Common;
import com.STL.Init.SeleniumInit;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentLogger extends SeleniumInit{

	private ExtentLogger() {}

	public static void pass(String message) {
		ExtentManager.getExtentTest().pass(message);
	}

	public static void fail(String message) {
		ExtentManager.getExtentTest().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(Common.getBase64Image(driver)).build());
		ExtentManager.getExtentTest().log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED));
	}

	public static void skip(String message) {
		ExtentManager.getExtentTest().log(Status.SKIP, MarkupHelper.createLabel(message, ExtentColor.YELLOW));
	}
}