package com.STL.Reports;

import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentReportListener implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		try {
			ExtentReport.initReport();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushReports();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getDescription());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentManager.getExtentTest().log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getDescription() + " is passed.", ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentLogger.fail(result.getMethod().getDescription() + " is failed.");
		Throwable exception = result.getThrowable();
		boolean hasThrowable = exception != null;
		if (hasThrowable) {
			ExtentManager.getExtentTest().log(Status.FAIL, exception);
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentLogger.skip(result.getMethod().getDescription() + " is skipped.");
		Throwable exception = result.getThrowable();
		boolean hasThrowable = exception != null;
		if (hasThrowable) {
			ExtentManager.getExtentTest().log(Status.SKIP, exception);
		}
	}
}