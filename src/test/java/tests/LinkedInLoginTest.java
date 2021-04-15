package tests;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import drivers.WebDriverUtils;
import pages.LinkedInLoginPage;
import utilities.ExcelUtils;
import utilities.WebElementActions;

public class LinkedInLoginTest {

	LinkedInLoginPage liloginpage;
	WebElementActions webele;
	ExcelUtils eu;

	@BeforeSuite
	public void beforeSuite() {

		liloginpage = new LinkedInLoginPage();
	}
	
	@DataProvider(name ="exceldata")
  	public Object[][] excelDP() throws IOException{
			eu= new  ExcelUtils(); 
        	//We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
        	Object[][] arrObj = eu.getExcelData("/MyProject/src/test/resources/testdata/LinkedInLoginData.xlsx", "Sheet1");
        	return arrObj;
  	}
	
	
	
	@Test(dataProvider ="exceldata")
  	public void sendLoginCredentials(String userName,String password){
		liloginpage.launchUrl();	
		liloginpage.loginCredentials(userName,password);
	}
	
	@AfterSuite
	public void afterSuite() {
		WebDriverUtils.close();
		//report.flush(); 
	}
}
