package com.STL.Reports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	private ExtentManager() {}

	static ThreadLocal<ExtentTest> extTest = new ThreadLocal<ExtentTest>();

	static void setExtentTest(ExtentTest test) {
		extTest.set(test);
	}

	static ExtentTest getExtentTest() {
		return extTest.get();
	}

	static void unload() {
		extTest.remove();
	}
}