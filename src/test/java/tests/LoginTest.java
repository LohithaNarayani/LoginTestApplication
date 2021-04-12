package tests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import drivers.WebDriverUtils;
import pages.LoginPage;
import utilities.ExcelUtils;

public class LoginTest {

	WebDriver driver;
	LoginPage loginpage;
	ExcelUtils eu;
	
	ArrayList<String> uname=new ArrayList<String>();
	ArrayList<String> mailid=new ArrayList<String>();
	ArrayList<String> passwrd=new ArrayList<String>();
	private File testReports;
	private File extentReports;
	private String extentReportsPath;
	private String timeStamp;
	private File timeStamp1;
	private ExtentReports report;
	private ExtentTest test;
	
	@BeforeSuite
	public void beforeSuite() {
		String path="";
		// Making folder with name as testReports
		testReports=new File("testReports");
		if(!testReports.exists()) {
			
			testReports.mkdir();
		}
		path=testReports.getAbsolutePath();

		// Making folder with name as screenshots in testReports folder
		extentReports=new File(path+"/extentReports");
		if(testReports.exists() && (!extentReports.exists()))
		{
			extentReports.mkdir();
		}
		extentReportsPath=extentReports.getAbsolutePath();
	}
	
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception {
		
		if(testReports.exists() && extentReports.exists()) {

			// Time stamp creation
			timeStamp= new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

			// Making folder with timestamp (ddMMyyyHHmmss) as nomenclature.
			timeStamp1=new File(extentReportsPath+"/"+timeStamp);
			timeStamp1.mkdirs();
			String timeStampPath = timeStamp1.getAbsolutePath();
			
			// Creating ExtentReportResults.html file in current timeStamp folder
			File extentReportsFile=new File(timeStampPath+"/LoginExtentReport.html");
			extentReportsFile.createNewFile();
			
			report = new ExtentReports(); 
			test = report.createTest("testLoginPage");
		}
		loginpage=new LoginPage(test);
	}
	
	@DataProvider
	@Test
	public void testLoginPage() {
		loginpage.launchApp();
		String username = null,emailid=null,pass=null;
        eu= new  ExcelUtils();  
		try {
			uname=eu.getLoginData("Dev","Username");
			mailid=eu.getLoginData("Dev","Emailid");
			passwrd=eu.getLoginData("Dev","Password");
			
			test.log(Status.PASS, "Username and Password fields are fetched from excel"); 

		} catch (Exception e1) {
			test.log(Status.FAIL, "Failed to fetch Username and Password fields from excel"); 
		}

		for(int i=0;i<mailid.size() && i<passwrd.size() ;i++) {
			try {
				username=uname.get(i);
				emailid=mailid.get(i);
				pass=passwrd.get(i);
				loginpage.sendLoginData(username, emailid, pass);
				
				
			} catch (Exception e) {
				System.out.println("Emailid : "+emailid+" , Password : "+pass+" are invalid credentials");
				test.log(Status.FAIL, "Login Failed.... Invalid Credentials"); 
			}
		}
	}
	
	@AfterSuite
	public void afterSuite() {
		WebDriverUtils.close();
		report.flush(); 
	}
}
