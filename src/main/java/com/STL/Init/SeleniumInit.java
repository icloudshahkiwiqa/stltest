package com.STL.Init;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.MTN.Account.AccountIndexPage;
import com.MTN.Account.AccountVerification;
import com.MTN.Category.CategoryIndexPage;
import com.MTN.Category.CategoryVerification;
import com.MTN.Common.CommonIndexPage;
import com.MTN.Common.CommonVerification;
import com.MTN.Explore.ExploreIndexPage;
import com.MTN.Explore.ExploreVerification;
import com.MTN.Login.LoginIndexPage;
import com.MTN.Login.LoginVerification;
import com.MTN.Search.SearchIndexPage;
import com.MTN.Search.SearchVerification;
import com.STL.Reports.ExtentLogger;
import com.STL.Utility.TestData;
import com.microsoft.edge.seleniumtools.EdgeDriver;
import com.microsoft.edge.seleniumtools.EdgeOptions;

public class SeleniumInit{
	public String suiteName = "";
	public String testName = "";
	public static String PayertestURL="";
	public static String testUrl;
	public static String seleniumHub; // Selenium hub IP
	public static String seleniumHubPort; // Selenium hub port
	protected String targetBrowser; // Target browser
	protected static String test_data_folder_path = null;
	public static String currentWindowHandle = "";// Get Current Window handle
	public static String browserName = "";
	public static String osName = "";
	public String HomeDir="";
	public static String browserVersion = "";
	public static String browseruse = "";
	public static String Url="";
	public static String AuthorName;
	public static String ModuleName;
	public static String Version="";
	//	public static String header="";
	public static int col=0;

	protected static String screenshot_folder_path = null;
	public static String currentTest; // current running test
	protected static Logger logger = Logger.getLogger("testing");
	protected static WebDriver driver;

	public static final String USERNAME = "badalshah1";
	public static final String AUTOMATE_KEY = "8usexWYvo4qMwzWAZAGz";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	protected ExploreIndexPage exploreIndexPage;
	protected ExploreVerification exploreVerification;

	protected SearchIndexPage searchIndexPage;
	protected SearchVerification searchVerification;

	protected CommonIndexPage commonIndexPage;
	protected CommonVerification commonVerification;

    protected CategoryIndexPage categoryIndexPage;
    protected CategoryVerification categoryVerification;

    protected LoginIndexPage loginIndexPage;
    protected LoginVerification loginVerification;

    protected AccountIndexPage accountIndexPage;
    protected AccountVerification accountVerification;

    @BeforeSuite(alwaysRun = true)
    public void fetchSuite(ITestContext testContext) throws IOException {
    	try {
    		TestData.deletePastScreenshots(System.getProperty("user.dir") +"\\test-output\\screenshots");
//    		TestData.deleteDirectory(new File(System.getProperty("user.dir") +"\\images"));
    	} catch(Exception e){
    	}

    	//URL remote_grid = new URL("http://" + seleniumHub + ":" + seleniumHubPort + "/wd/hub");
    	String SCREENSHOT_FOLDER_NAME = "screenshots";
    	String TESTDATA_FOLDER_NAME = "test_data";
    	test_data_folder_path = new File(TESTDATA_FOLDER_NAME).getAbsolutePath();
    	screenshot_folder_path = new File(SCREENSHOT_FOLDER_NAME).getAbsolutePath();
    	 String scrFolder = "images/img_"+Common.getCurrentTimeStampString();
    	 new File(scrFolder).mkdir();
    	 System.setProperty("scr.folder", scrFolder);
    }
	
    @BeforeTest(alwaysRun = true)
    public void fetchSuiteConfiguration(ITestContext testContext) throws IOException {
    	//seleniumHub = testContext.getCurrentXmlTest().getParameter("selenium.host");
    	//seleniumHubPort = testContext.getCurrentXmlTest().getParameter("selenium.port");
    	//testUrl=TestData.getValueFromConfig("config.properties","URL");
    	testUrl =TestData.getCellValue("data/MTN_Details.xlsx", "URL", 1, 0);
    	targetBrowser =TestData.getValueFromConfig("config.properties","Browser");
    	browserVersion = TestData.getValueFromConfig("config.properties", "version");

    	browserName=targetBrowser;
    }
	/**
	 * WebDriver initialization
	 * @return WebDriver object
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method, ITestContext testContext,ITestResult testResult) throws IOException, InterruptedException {
		try{
			DesiredCapabilities capability = null;		
			if (targetBrowser == null || targetBrowser.contains("firefox") || targetBrowser.equalsIgnoreCase("firefox")) {
				File driverpath = new File("Resource/geckodriver.exe");
				String path1 = driverpath.getAbsolutePath();	
				System.setProperty("webdriver.gecko.driver",path1);
/*
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("browser.download.dir", new File("DownloadData").getAbsolutePath());
				profile.setPreference("browser.download.folderList", 2);
				profile.setPreference("browser.helperApps.alwaysAsk.force",false);
				Map<String, Object> prefs = new HashMap<String, Object>();
				profile.setPreference("browser.download.manager.showWhenStarting",false);
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk", 
						"application/zip;application/octet-stream;application/x-zip;application/x-zip-compressed");
				profile.setPreference("pdfjs.disabled",true);
*/
				FirefoxOptions options = new FirefoxOptions();
				options.setAcceptInsecureCerts(true);

				options.addPreference("browser.download.folderList", 2);
				options.addPreference("browser.helperApps.alwaysAsk.force", false);
				options.addPreference("browser.download.dir", new File("DownloadData").getAbsolutePath()); 
				options.addPreference("browser.download.defaultFolder",new File("DownloadData").getAbsolutePath()); 
				options.addPreference("browser.download.manager.showWhenStarting", false);
				options.addPreference("browser.helperApps.neverAsk.saveToDisk",
						"multipart/x-zip,application/zip,application/x-zip-compressed,application/x-compressed,application/msword,application/csv,text/csv,image/png ,image/jpeg, application/pdf, text/html,text/plain,  application/excel, application/vnd.ms-excel, application/x-excel, application/x-msexcel, application/octet-stream");
				if(targetBrowser.contains("headless")) {
					options.setHeadless(true);
				}
				capability = DesiredCapabilities.firefox();
				capability.setJavascriptEnabled(true);
				osName = System.getProperty("os.name");
				HomeDir=System.getProperty("user.home");
				capability.setCapability("marionette", true);
				//driver= new FirefoxDriver(capability);
				driver = new FirefoxDriver(options);
				if(targetBrowser.contains("headless")) {
					driver.manage().window().setPosition(new Point(0,0));
					driver.manage().window().setSize(new Dimension(1920,1080));
				}

				//driver = new RemoteWebDriver(remote_grid, capability);
			} else if (targetBrowser.contains("Edge")||targetBrowser.equalsIgnoreCase("Edge")) {
				capability = DesiredCapabilities.edge();
				File driverpath = new File("Resource/msedgedriver.exe");
				String path1 = driverpath.getAbsolutePath();
				System.setProperty("webdriver.edge.driver",path1 );

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("download.default_directory", new File("DownloadData").getAbsolutePath());
				prefs.put("download.prompt_for_download", false);

				EdgeOptions options = new EdgeOptions();
				options.setExperimentalOption("prefs",prefs);
				options.addArguments("inPrivate");
				options.addArguments("start-maximized");
				if(targetBrowser.contains("headless")) {
					options.addArguments("headless");
					options.addArguments("window-size=1920,1080");
				}
				options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});	//This removes "MSEdge is being controlled by automated test software."
				capability.setBrowserName("Microsoft Edge");
				//capability.setJavascriptEnabled(true);
				osName = capability.getPlatform().name();
				driver= new EdgeDriver(options);

				//driver = new RemoteWebDriver(remote_grid, capability);
			} else if (targetBrowser.contains("ie11")||targetBrowser.equalsIgnoreCase("ie11")) {
				InternetExplorerOptions options = new InternetExplorerOptions();
				capability = DesiredCapabilities.internetExplorer();
				File driverpath = new File("Resource/IEDriverServer.exe");
				String path1 = driverpath.getAbsolutePath();
				System.setProperty("webdriver.ie.driver",path1 );
				capability.setBrowserName("internet explorer");
				capability.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capability.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				capability.setCapability("nativeEvents", false);
				capability.setJavascriptEnabled(true);
				osName = capability.getPlatform().name();
				driver= new InternetExplorerDriver(options);
				//driver = new RemoteWebDriver(remote_grid, capability);
			} else if (targetBrowser.contains("chrome") || targetBrowser.equalsIgnoreCase("chrome")) {
				capability = DesiredCapabilities.chrome();
				File driverpath = new File("Resource/chromedriver.exe");
				String path1 = driverpath.getAbsolutePath();
				System.setProperty("webdriver.chrome.driver",path1);
				final ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--incognito");
				chromeOptions.addArguments("--start-maximized");
				if(targetBrowser.contains("headless")) {
					chromeOptions.addArguments("headless");
					chromeOptions.addArguments("window-size=1920,1080");
				}
				chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});	//This removes "Chrome is being controlled by automated test software." 
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("download.default_directory", new File("DownloadData").getAbsolutePath());
				chromeOptions.setExperimentalOption("prefs", prefs);
				chromeOptions.setBinary("C:\\Users\\User\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
				capability.setBrowserName("chrome");
				capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
				//capability.setCapability("disable-popup-blocking", true);
				osName = capability.getPlatform().name();
				capability.setJavascriptEnabled(true);
				browserVersion = capability.getVersion();
				//driver = new RemoteWebDriver(remote_grid, capability);
				driver= new ChromeDriver(chromeOptions);
			} else if (targetBrowser.contains("safari")) {
				SafariOptions options = new SafariOptions();
				options.setUseTechnologyPreview(true);
				capability = DesiredCapabilities.safari();
				capability.setJavascriptEnabled(true);
				capability.setBrowserName("safari");
				driver = new SafariDriver(options);
			} else if (targetBrowser.contains("browserstack")) {
				//String USERNAME = "colkiwi1";
				//String AUTOMATE_KEY = "5Z4YxnQTXyGay8CSzr6L";
				String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

				DesiredCapabilities caps = new DesiredCapabilities();
				try {
					String bb = testContext.getCurrentXmlTest().getParameter("browserstack.browser");
					if(bb.contains("chrome")){
						caps.setCapability("browser", "Chrome");
						caps.setCapability("browser_version", browserVersion);
						caps.setCapability("browserstack.selenium_version", "3.141.59");
						caps.setCapability("os", "Windows");
						caps.setCapability("os_version", "10");

						ChromeOptions options = new ChromeOptions();

						HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

						options.setExperimentalOption("prefs", chromePrefs);
					} else if(bb.contains("firefox")){
						caps.setCapability("browser", "Firefox");
						caps.setCapability("browser_version", browserVersion);
						caps.setCapability("browserstack.selenium_version", "3.141.59");
						caps.setCapability("os", "Windows");
						caps.setCapability("os_version", "10");
					} else if(bb.contains("ie11")){
						caps.setCapability("browser", "IE");
						caps.setCapability("browser_version", browserVersion);
						caps.setCapability("browserstack.selenium_version", "3.141.59");
						caps.setCapability("os", "Windows");
						caps.setCapability("os_version", "10");
					} else if(bb.contains("edge")){
						caps.setCapability("browser", "Edge");
						caps.setCapability("browser_version", browserVersion);
						caps.setCapability("os", "Windows");
						caps.setCapability("os_version", "10");
						caps.setCapability("browserstack.selenium_version", "3.141.59");
					}
				} catch(Exception e ){
				}
				caps.setCapability("resolution", "1920x1080");
				caps.setCapability("browserstack.debug", "true");
				caps.setCapability("browserstack.timezone", "Kolkata");
				caps.setCapability("project", "STL");
				//caps.setCapability("build",suiteNameBS);
				caps.setCapability("name", testContext.getName());
				caps.setCapability("acceptSslCerts", "true");
				caps.setCapability("acceptInsecureCerts", "true");
				caps.setCapability("browserstack.networkLogs", "true");

				driver = new RemoteWebDriver(new URL(URL), caps);
			}

			//suiteName = testContext.getSuite().getName();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(testUrl);

			/*To verify and set Default Language to English */
			checkAndChangeDefaultLanguage(driver);
		} catch(Exception e){
			e.printStackTrace();
		}

		suiteName = testContext.getSuite().getName();

		exploreIndexPage = new ExploreIndexPage(driver);
		exploreVerification = new ExploreVerification(driver);

		searchIndexPage = new SearchIndexPage(driver);
		searchVerification = new SearchVerification(driver);

		commonIndexPage = new CommonIndexPage(driver);
		commonVerification = new CommonVerification(driver);

		categoryIndexPage = new CategoryIndexPage(driver);
		categoryVerification = new CategoryVerification(driver);

		loginIndexPage = new LoginIndexPage(driver);
		loginVerification = new LoginVerification(driver);

		accountIndexPage = new AccountIndexPage(driver);
		accountVerification = new AccountVerification(driver);
	}
	/**
	 * After Method
	 * 
	 * @param testResult
	 */
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult testResult,ITestContext testContext) {
		String screenshotName="";
		testName = testResult.getName();
		try {
			screenshotName = Common.getCurrentTimeStampString(); //+ testName;
			if (!testResult.isSuccess()) {
				/* Print test result to Jenkins Console */
				System.out.println();
				System.out.println("TEST FAILED - " + testName);
				System.out.println();
				System.out.println("ERROR MESSAGE: " + testResult.getThrowable());
				System.out.println("\n");
				Reporter.setCurrentTestResult(testResult);
				/* Make a screenshot for test that failed */
				if(testResult.getStatus()==ITestResult.FAILURE) {
					System.out.println("1 message from tear down");
					slog("Please look to the screenshot :- "+ Common.makeScreenshot(driver, screenshotName));
				}
			}
			driver.manage().deleteAllCookies();
			driver.close();
			driver.quit();
		} catch (Throwable throwable) {
			System.out.println(throwable);
		}
	}
	/**
	 * Log given message to Reporter output.
	 * 
	 * @param msg
	 *            Message/Log to be reported.
	 */
//	@AfterSuite(alwaysRun = true)
//	public void postConfigue() {
//		driver.manage().deleteAllCookies();
//		driver.close();
//	}

	public void log(String msg) {
		ExtentLogger.pass(msg);
		Reporter.log(msg + "</br>");
		System.out.println(msg);
	}

	public void logStep(int msg1, String msg2) {
//		Reporter.log("Step-"+msg1+" : "+msg2 + "</br>");
		Reporter.log(msg2 + "</br>");
		System.out.println("Step-"+msg1+" : "+msg2);// for jenkins  
	}

	public void logCase(String msg) {
		Reporter.log("<strong><b><font color=#22a1c4>TC : "+msg+"</font></b></strong></br>");
		System.out.println("Test Case : "+msg);
	}

	public static void slog(String msg)	{
		Reporter.log(msg + "</br>");
		System.out.println(msg);
	}

	public void logStatus(final int test_status,String msg)	{
		switch (test_status) {
		case ITestStatus.PASSED:
			//test.log(Status.PASS,"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp "+msg+" ");
			log(msg+" <Strong><font color=#32cd32><b> &#10004 Pass</b></font></strong>");
			Common.captureScreenshot(driver);
			break;
		case ITestStatus.FAILED:
			String screenshotName = Common.getCurrentTimeStampString();
			log(msg+" <Strong><font color=##dc3545><b> &#10008 Fail</b></font></strong> -> Please look to the screenshot :- "+ Common.makeScreenshot(driver, screenshotName));
			//MakeScreenshots();
			break;
		case ITestStatus.SKIPPED:
			log(msg);
			break;
		default:
			break;
		}
	}

	public void MakeScreenshots() {
		String screenshotName = Common.getCurrentTimeStampString() + testName;
		Common.makeScreenshot2(driver, screenshotName);
	}

	public void checkAndChangeDefaultLanguage(WebDriver driver) throws IOException {
		WebElement languageDrpdwn = driver.findElement(By.xpath("//a[@id='navbarScrollingDropdown']/img/.."));
		Common.clickableElement(languageDrpdwn, driver);
		String languageToExecute= TestData.getCellValue("data/MTN_Details.xlsx", "URL", 1, 1);
		String defLanguage = languageDrpdwn.getText();
		System.out.println("Language shown is "+defLanguage);
		File source = null;
		if(languageToExecute.equalsIgnoreCase(defLanguage)) {
		} else {
			if(languageToExecute.equalsIgnoreCase("English")) {
				Common.clickOn(driver, languageDrpdwn);
				Common.jsClick(driver, driver.findElement(By.xpath("//a[@id='navbarScrollingDropdown']/img/../..//div//a/img[contains(@src,'en')]")));
				Common.pause(7);
				slog("Language changed to English");
			} else if(languageToExecute.equalsIgnoreCase("Arabic") || languageToExecute.equalsIgnoreCase("عربى")) {
				Common.clickOn(driver, languageDrpdwn);
				Common.jsClick(driver, driver.findElement(By.xpath("//a[@id='navbarScrollingDropdown']/img/../..//div//a/img[contains(@src,'ar')]")));
				Common.pause(7);
				slog("Changed language to Arabic.");
			}
		}
		if(languageToExecute.equalsIgnoreCase("English")) {
			source = new File("en.properties");
			System.out.println("English file copied to Message");
		} else if(languageToExecute.equalsIgnoreCase("Arabic") || languageToExecute.equalsIgnoreCase("عربى")) {
			source = new File("ar.properties");
			System.out.println("Arabic file copied to Message");
		}
		File dest = new File("message.properties");
		FileUtils.copyFile(source, dest);
	}
}