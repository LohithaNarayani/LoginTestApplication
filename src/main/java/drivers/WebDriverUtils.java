package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverUtils {
	static WebDriver driver = null;

	public static synchronized WebDriver getDriver(String browser) {
       if(driver==null) {
		if(browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//drivers//chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		else {
			System.out.println("Unsupported Browser !!!");
		}

		driver.manage().window().maximize();
		setDriver(driver);

		return driver;
       } else {
    	   return driver;
       }
	}
	
	public static synchronized void setDriver(WebDriver driver) {
		WebDriverUtils.driver=driver;
	}
	
	public static synchronized void close() {
		if(driver !=null) {
		driver.quit();
		driver=null;
		}
	}
	
}
