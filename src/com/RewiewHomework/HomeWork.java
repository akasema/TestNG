package com.RewiewHomework;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Util.CommonMethods;
import com.Util.Constants;

/**
 * TC 1: HRMS Add Employee: 

Open chrome browser
Go to “http://166.62.36.207/humanresources/symfony/web/index.php/auth/login”
Login into the application
Add 5 different Employees and create login credentials by providing: 
First Name
Last Name
Username
Password
Provide Employee First and Last Name
Verify Employee has been added successfully and take screenshot (you must have 5 different screenshots)
Close the browser
Specify group for this test case, add it into specific suite and execute from xml file.
 */

public class HomeWork extends CommonMethods{
	//public String url="http://166.62.36.207/humanresources/symfony/web/index.php/auth/login";
	@BeforeMethod
	public void openAndNavigate() throws InterruptedException {
		setUp("chrome", Constants.HRMS_URL);
			driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
		driver.findElement(By.id("btnLogin")).click();
		 // clicking on add employee
//        Actions act = new Actions(driver);
//        driver.findElement(By.linkText("PIM")).click();
//        WebElement add = driver.findElement(By.linkText("Add Employee"));
//        act.moveToElement(add).click().perform();
		Thread.sleep(1000);
		driver.findElement(By.linkText("PIM")).click();
		Thread.sleep(500);
		driver.findElement(By.linkText("Add Employee")).click();
	} 
	@AfterMethod
	    public void close() throws InterruptedException {
	        Thread.sleep(3000);
	        driver.close();
	    }
	
	@Test(dataProvider="getData")
	public void enteringEmloyee(String empName,String empLastname) {
		   // providing employee info
        driver.findElement(By.xpath("//*[@id=\"firstName\"]")).sendKeys(empName);
        driver.findElement(By.xpath("//*[@id=\"lastName\"]")).sendKeys(empLastname);
        String idExp = driver.findElement(By.xpath("//*[@id=\"employeeId\"]")).getAttribute("value");
      //  driver.findElement(By.xpath("//*[@id=\"photofile\"]")).sendKeys("C:\\Users\\kadir\\eclipse-workspace\\SeleniumBatchV\\screenshots\\HW-Class-9\\AA-SS.png");
        driver.findElement(By.xpath("//*[@id=\"btnSave\"]")).click();
      
        // verifying employee added succsessfully
        // name validation
        String nameEnt = empName+" "+empLastname;
        String nameDisp = driver.findElement(By.xpath("//div[@id='profile-pic']/h1")).getText();
        SoftAssert softA = new SoftAssert();
        AssertJUnit.assertEquals(nameDisp, nameEnt,"Names match");
        TakesScreenshot ts = (TakesScreenshot) driver;
		softA.assertAll();
		// call method getScreenshotAs and specify output type
		File screen = ts.getScreenshotAs(OutputType.FILE);
		// copy file to the specified destination and give name and extension
		try {
		FileUtils.copyFile(screen, new File("screenshots/HRMSAddEmp/"+empName+".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//to create a data provider we need to create a method that will be returning 
		// Object 2D array
		@DataProvider
		public Object[][] getData(){
			
			Object[][] data= {
					{"Aka", "AKAlastNAME"},
					{"Aka1", "AKAlastNAME1!"},
					{"Aka2", "AKAlastNAME2!"}
			};
			return data;
		}
	
	
}
