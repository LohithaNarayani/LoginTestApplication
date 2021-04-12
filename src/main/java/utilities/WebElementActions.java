package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementActions {
	public void click(WebDriver driver, WebElement element) {

		scrollElementIntoView(driver, element);
		waitForElement(driver, element, 5);
		element.click();
	}
	
	public void waitForElement(WebDriver driver, WebElement element, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void scrollElementIntoView(WebDriver driver, WebElement element) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void scrollingDownWindow(WebDriver driver) {
		// Scrolling window to down by 20 pixels

		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,20)");

	}

	public void sendKeys(WebDriver driver, WebElement element, String keys) {
		//Send keys to field by taking WebElement as parameter
		scrollingDownWindow(driver);
		dataClear(driver,element);
		element.sendKeys(keys);
	}

	public String fetchText(WebDriver driver, WebElement element) {
		//Send fetches text by taking WebElement as parameter
		scrollingDownWindow(driver);
		String text= element.getText();
		return text;
	}

	public void dataClear(WebDriver driver, WebElement element) {
		//Data clear function by taking WebElement as parameter
		scrollingDownWindow(driver);
		element.clear();
	}
}
