package com.Util;

import com.hrms.util.CommonMethods;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//command+o--> to see all methods within the class 
public class CommonMethods extends BaseClass{
	

	/** 
	 * Use this method in need of opening browser and target url
	 * @param browser The desired browser
	 * @param url	 The desired url
	 */
	public static void setUp(String browser, String url){
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromeDriver.exe");
			driver=new ChromeDriver();
					
		}else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			driver=new FirefoxDriver();
			 
		}else {
			System.err.println("Browser not supported");
		}
		driver.manage().window().maximize();
//		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		
		driver.get(url);
	}
	/**
	 * This Method finds the number of rows 
	 * This Method Finds employee in a given table
	 */
	public static void findEmployeClick(String url2, String empName) {
		driver.navigate().to(url2);
		 List<WebElement> rows=driver.findElements(By.xpath("//table[@id = 'resultTable']/tbody/tr"));
	     String firstPart="//table[@id = 'resultTable']/tbody/tr[";
	     String secondPart="]/td[3]";
	     String thirdPart="]/td[1]";
	    
	     int numberOfRows=rows.size();
	     System.out.println(numberOfRows);
		
		 for(int i=1; i<=numberOfRows; i++) {
	         String namePresent=driver.findElement(By.xpath(firstPart+i+secondPart)).getText();
	        
	         if(namePresent.contains(empName)) {
	        	 System.out.println(namePresent);
	             driver.findElement(By.xpath(firstPart+i+thirdPart)).click();
	             System.out.println("Already clicked");
	             break;
	         }
//             else {
//             System.out.println("Text not present");
//         }
     }
}
	/**
	 * This method will accept the alert
	 * 
	 * @throws NoAlertPresentException if alert is not present 
	 */
	public static void acceptAlert() {
		
		try {
			Alert alert=driver.switchTo().alert();
			alert.accept();
		}catch (NoAlertPresentException e) {
			System.out.println("Alert is not present");
		}
	}
	
	/**
	 * This methods will dismiss the alert
	 * 
	 * @throws NoAlertPresentException if alert is not present 
	 */
	public static void dismissAlert() {
		
		try {
			Alert alert=driver.switchTo().alert();
			alert.dismiss();
		}catch(NoAlertPresentException e) {
			System.out.println("Alert is not prresent");
		}
	}
	
	/**
	 * This method will get a text from the alert
	 * 
	 * @return text of the alert
	 * @throws NoAlertPresentException if alert is not present
	 */
	
	public static String getAlertText() {
		
		try {
			Alert alert=driver.switchTo().alert();
			return alert.getText();
		}catch(NoAlertPresentException e) {
			System.out.println("Alert is not present");
			return null;
		}
	}
	
	/**
	 * This method with switch to the frame 
	 * @param 
	 */
	public static void switchToFrame(WebElement element){
		try {
			driver.switchTo().frame(element);
		}catch (NoSuchFrameException e) {
			System.out.println("Frame is not Present");
		}
	}
	
	/**
	 * This method with switch to the frame 
	 * @param element
	 */
	public static void switchToFrame(int index){
		try {
			driver.switchTo().frame(index);
		}catch (NoSuchFrameException e) {
			System.out.println("Frame is not Present");
		}
	}
	/**
	 * JavaScript Methods
	 * @param element
	 */
	public static void jsClick(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;	
		js.executeScript("arguments[0].click()", element);
	}
	
	public static void scrollIntoElement(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);		
	}
	
	public static void scrollDown(int pixel) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0," +pixel+ ")");		
	}
	public static void scrollUp (int pixel) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, -" +pixel+ ")");		
	}
	
	/**
     * This method will take a screenshot
     * @param fileName
     */
    public static void takeScreenshot(String fileName) {
        TakesScreenshot ts=(TakesScreenshot)driver;
        File file=ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("screenshot/"+fileName+".png"));
        } catch (IOException e) {
            System.out.println("Cannot take a screenshot");
        }
    }

}
