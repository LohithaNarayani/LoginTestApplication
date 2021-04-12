package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import drivers.WebDriverUtils;
import utilities.WebElementActions;

public class LoginPage {
	
	WebDriver driver;
	WebElementActions webele;
	private ExtentTest test;

	
	@FindBy(how = How.XPATH, using="/html/body/app-root/app-example/div/div/div[2]/form/div[1]/div/input")
	public WebElement username;
	@FindBy(how = How.XPATH , using = "/html/body/app-root/app-example/div/div/div[2]/form/div[2]/div/input")
	public WebElement emailid;
	@FindBy(how = How.NAME,using ="passwrd") public WebElement pass;
	@FindBy(how = How.XPATH,using = "/html/body/app-root/app-example/div/div/div[2]/form/div[4]/div/button")
	public WebElement loginbtn;
	
	public LoginPage(ExtentTest testObj) {
		webele = new WebElementActions();
		WebDriverUtils wd = new WebDriverUtils();
		String browser = System.getProperty("browser");
		System.out.println("Browser: "+browser);
		driver = wd.getDriver(browser);
		PageFactory.initElements(driver, this);
		test=testObj;
		test.log(Status.PASS, "LoginPage constructor is created"); 
	}
	
	public void launchApp() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://localhost:4200/#/example");
		
	}
	
	public void sendLoginData(String uname,String mailid,String passwrd) {
		try {
			enterUserName(uname);
			test.log(Status.PASS, "UserName entered sucessfully"); 
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed to enter UserName"); 
		}
		
		try {
			enterEmailId(mailid);
			test.log(Status.PASS, "EmailId entered sucessfully"); 
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed to enter Emailid"); 
		}
		
		try {
			enterPassword(passwrd);
			test.log(Status.PASS, "Password entered sucessfully"); 
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed to enter Password"); 
		}
		
		clickOnLoginButton();		
	}
	
	public void enterUserName(String uname) {
		webele.sendKeys(driver, username, uname);
	}

	public void enterPassword(String passwrd) {
		webele.sendKeys(driver, pass, passwrd);
	}
	
	public void enterEmailId(String mailid) {
		webele.sendKeys(driver, emailid, mailid);
	}

	public void clickOnLoginButton() {
		webele.click(driver, loginbtn);
	}

}
