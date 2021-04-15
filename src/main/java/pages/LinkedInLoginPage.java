package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import drivers.WebDriverUtils;
import utilities.WebElementActions;

public class LinkedInLoginPage {
	
	WebDriver driver;
	WebElementActions webele;
	
	@FindBy(how = How.ID, using="username") public WebElement userName;
	@FindBy(how = How.ID, using="password") public WebElement password;
	@FindBy(how = How.XPATH, using="/html/body/div/main/div[2]/div[1]/form/div[3]/button") public WebElement loginBtn;
	@FindBy(how = How.XPATH, using="//*[@id=\"global-nav-icon--mercado__home--active\"]/path[1]") 
	public WebElement homeIcon;
	
	
	public LinkedInLoginPage() {
		webele = new WebElementActions();
		WebDriverUtils wd = new WebDriverUtils();
		String browser = System.getProperty("browser");
		System.out.println("Browser: "+browser);
		driver = wd.getDriver(browser);
		PageFactory.initElements(driver, this);
		
	}
	
	public void launchUrl() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.linkedin.com/login");
		
		if (driver.getTitle().equals("LinkedIn India: Log In or Sign Up")) { 
			//test.log(Status.PASS, "Navigated to correct URL"); 
		} else { 
			//test.log(Status.FAIL, "Navigated to wrong URL"); 
		} 
	}
	
	public void loginCredentials(String username, String passwrd) {
		try {
			new WebElementActions().waitForElement(driver, userName, 3000);
			enterUserName(username);
			//test.log(Status.PASS, "UserName entered sucessfully"); 
		} catch (Exception e) {
			//test.log(Status.FAIL, "Failed to enter UserName"); 
		}
		
		try {
			enterPassword(passwrd);
			//test.log(Status.PASS, "Password entered sucessfully"); 
		} catch (Exception e) {
			//test.log(Status.FAIL, "Failed to enter Password"); 
		}
		
		clickLoginButton();	
		
		if(homeIcon.isDisplayed()) {
			//To check whether Home icon is displayed or not (Displays only when sucessfully logged in ) 
			
			System.out.println("Username : "+username+" , Password : "+passwrd+" are valid credentials");
			//test.log(LogStatus.PASS, "Successfully Logged in"); 
		}
	}
	
	private void clickLoginButton() {
		webele.click(driver, loginBtn);
		
	}

	public void enterUserName(String username) {
		webele.sendKeys(driver, userName, username);
	}
	
	public void enterPassword(String passwrd) {
		webele.sendKeys(driver, password, passwrd);
	}
}
