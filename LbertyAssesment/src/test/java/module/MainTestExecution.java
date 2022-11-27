package module;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class MainTestExecution {

	public static WebDriver driver = null;
	public static String homePageXpath;
	public static String getInTouchTabXpath;
	public static String careersXPath;
	public static String careersHomePageXpath;
	public static String southAfricaXpath;
	public static String firstCareerOption;
	public static String firstNameXpath;
	public static String lastNameXpath;
	public static String emailXpath;
	public static String numberXpath;
	public static String phoneXpath;
	public static String submitButtonXpath;
	public static String errorMessageXpath;
	public static String acceptCoockiesXpath;

	public static void main(String[] args) throws InterruptedException {

		// start reporters
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
				"C:\\Users\\Legend\\eclipse-workspace\\FirstTest\\TestResults\\TestResults.html");

		// create ExtentReports and attach reporter(s)
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// creates a toggle for the given test, adds all log events under it
		ExtentTest test1 = extent.createTest("Load the iLab site",
				"Verify that user is able to load the iLab home page");

		//Chrome run and Firefox, one is commented out for execution purposes
		// set the system property
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Legend\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");
		
		// load the webdriver details
		driver = new ChromeDriver();
		
		//firefox 
		/*
		 * System.setProperty("webdriver.gecko.driver","C:\\Users\\Legend\\Downloads\\geckodriver-v0.32.0-win32\\geckodriver.exe");
		
		WebDriver driver = new FirefoxDriver(); //Creating an object of FirefoxDriver
		
		*/

		// start with report capturing
		test1.log(Status.INFO, "Start the End to End Flow for the test case");

		// declare url and load the url
		String url = " https://www.ilabquality.com/";
		driver.get(url);

		// maximize the screen
		driver.manage().window().maximize();

		// declare xpaths to be used in the E2E flow
		homePageXpath = "//img[@alt='iLAB']";
		getInTouchTabXpath = "(//a[contains(.,'Get in Touch')])[1]";
		careersXPath = "//div[@class='et_pb_blurb_content'][contains(.,'Careers')]";
		careersHomePageXpath = "//h1[contains(.,'Careers')]";
		southAfricaXpath = "//a[contains(.,'South Africa')]";
		firstCareerOption = "//a[contains(.,'Software Quality Assurance (SQA) Lead')]";
		firstNameXpath = "//input[@name='firstname']";
		lastNameXpath = "//input[@name='lastname']";
		phoneXpath = "//input[@name='phone']";
		emailXpath = "//input[@name='email']";
		submitButtonXpath = "//input[contains(@value,'Submit')]";
		errorMessageXpath = "//label[contains(.,'Please complete this required field.')]";
		acceptCoockiesXpath = "/html/body/div[3]/div[1]/div/div/div[2]/button[3]";


	
		// verify that user is logged in
		if (driver.findElement(By.xpath(homePageXpath)).isDisplayed()) {

			// pass login verification
			test1.pass("Successfully logged in to ilab.com");

		} else {

			// fail login verification
			System.out.println("Login failed");
			test1.fail("Login failed");

		} // End of login verification

		// navigate to Careers by clicking the get in touch button
		driver.findElement(By.xpath(getInTouchTabXpath)).click();

		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(getInTouchTabXpath)));

		// maximize the screen
		driver.manage().window().maximize();

		if (myDynamicElement.isDisplayed()) {

			// pass the step for page loaded successfully
			test1.pass("Successfully navigated to the get in touch page");

		} else {

			// fail the navigation stage
			test1.fail("Failed to navigate to the get in touch page");

		} // end of verification for get in touch page

		// click the careers section
		driver.findElement(By.xpath(careersXPath)).click();

		// implicit wait for the new page to be loaded
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// maximize the screen
		driver.manage().window().maximize();
		
		// verify if Careers home page is loaded
		if (driver.findElement(By.xpath(careersHomePageXpath)).isDisplayed()) {

			// pass the step for page loaded successfully
			test1.pass("Successfully navigated to the Careers home page");

		} else {

			// fail the navigation stage
			test1.fail("Failed to navigate to the Careers home page");

		} // end of verification for Careers home page

		// action to scroll down the page
		Actions a = new Actions(driver);

		// scroll down a page
		a.sendKeys(Keys.PAGE_DOWN).build().perform();

		// implicit wait for the new page to be loaded
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// click the SA option after scrolling
		driver.findElement(By.xpath(southAfricaXpath)).click();

		// pass test for clicking SA
		test1.pass("South Africa option selected sucessfully from careers page");

		// implicit wait for the new page to be loaded
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		
		// click the first career opening from the list
		driver.findElement(By.xpath(firstCareerOption)).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		test1.pass("Successfully navigated to the first option under SA Careers");
		// maximize the screen
		driver.manage().window().maximize();


		// scroll down the page to fine the details to be completed
		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		
		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		
		 
		// enter the first name
		driver.findElement(By.xpath("(//input[@class='hs-input'])[1]"))
				.sendKeys("Sheldon");
				test1.pass("Successfully Entered First Name " + "Sheldon");

		// enter the second name
		driver.findElement(By.xpath(lastNameXpath)).sendKeys("Cooper");
		test1.pass("Successfully Entered last Name" + "Cooper");

		// enter the phone number with random number generation
		int randomNumber = 0;
		String phoneNumber = "08";
		
		for(int x = 0; x < 8; x++) {
			
			randomNumber = (int) (Math.random() * 10);
			phoneNumber = phoneNumber + randomNumber;
			
		}
		driver.findElement(By.xpath(phoneXpath)).sendKeys(phoneNumber);
		test1.pass("Successfully Entered phone number" + phoneNumber);

		// enter the email
		driver.findElement(By.xpath(emailXpath)).sendKeys("SheldoCooper@gmail.com");
		test1.pass("Successfully Entered email" + "Sheldon.cooper@gmail.com");

		
		// click the submit button
		driver.findElement(By.xpath(submitButtonXpath)).click();
		test1.pass("Successfully clicked the submit button");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// verify if error is displayed after clicking submit with missing cv to be
		// uploaded
		if (driver.findElement(By.xpath(errorMessageXpath)).isDisplayed()) {

			// pass scenario since the expected error message is displayed
			test1.pass("Error message successfully displayed for missing document to be uploaded");
		} else {

			// fail scenario if error message is not displayed
			test1.fail("Error message not displayed after user clicking submit without uploading document");

		}
		

		driver.close();
		driver.quit();
		test1.pass("Browser successfully closed");

		test1.info("Test Complete");
		// calling flush writes everything to the log file
		extent.flush();
		// ----------------End of Script--------------for sure it works hahaa//

	}

}
