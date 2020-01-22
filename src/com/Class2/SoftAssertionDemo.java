package com.Class2;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Util.CommonMethods;
import com.Util.Constants;

/**
 * open application
 * login 
 * verify logo displayed
 * enter creditantials
 * verify user successfully logged in
 * @author kadir
 *
 */
public class SoftAssertionDemo extends CommonMethods{
	@BeforeMethod
	public void open() {
		setUp("chrome", Constants.HRMS_URL);
		
	}
	@Test
	public void logoAndLogin() {
		boolean logoDisplayed=driver.findElement(By.xpath("//div[@id='divLogo']/img")).isDisplayed();
		logoDisplayed=false;//bu satýr olmadan bu code tru olarak sorunsuz çalýþýr
		//bunu false yapýnca Hard Assertion olduðundan codun kalaný çalýþmaz ancak 
		//Soft assertion yapýnca çalýþýr 
		SoftAssert softAssert=new SoftAssert();
		softAssert.assertTrue(logoDisplayed);
		
	Assert.assertTrue(logoDisplayed, "Logo is NOT displayed");
	 driver.findElement(By.name("txtUsername")).sendKeys("Admin");
	 driver.findElement(By.name("txtPassword")).sendKeys("Hum@nhrm123");
	 driver.findElement(By.name("Submit")).click();
	 
	 boolean welcomeDisplayed=driver.findElement(By.id("welcome")).isDisplayed();
	 softAssert.assertTrue(welcomeDisplayed, "Test Case Passed");
	 //Assert.assertTrue(welcomeDisplayed);// hard Assertte bu kullanýlýyor 
	 softAssert.assertAll();// soft assert tüm verileri toparlamakk ve listelemek için kullanýlýr 
	
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
