package com.Class2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Util.CommonMethods;
import com.Util.Constants;

/**
 *C 1: HRMS Add Employee: 

Open chrome browser
Go to “http://166.62.36.207/humanresources/symfony/web/index.php/auth/login”
Login into the application
Click on Add Employee
Verify labels: Full Name, Employee Id, Photograph are displayed
Provide Employee First and Last Name
Verify Employee has been added successfully
Close the browser
 * @author kadir
 *
 */
public class SoftAssertionHW1 extends CommonMethods{
	public static String url="http://166.62.36.207/humanresources/symfony/web/index.php/auth/login";
	@BeforeMethod
    public void open() {
        setUp("chrome", url);
    }
    @AfterMethod
    public void close() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
    @Test
    public void ValidationAndLogIn() {
        // log in
        driver.findElement(By.id("txtUsername")).sendKeys("admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
        driver.findElement(By.id("btnLogin")).click();
        // clicking on add employee
        Actions act = new Actions(driver);
        driver.findElement(By.linkText("PIM")).click();
        WebElement add = driver.findElement(By.linkText("Add Employee"));
        act.moveToElement(add).click().perform();
        // providing employee info
        driver.findElement(By.xpath("//*[@id=\"firstName\"]")).sendKeys("AKA");
        driver.findElement(By.xpath("//*[@id=\"lastName\"]")).sendKeys("Sema");
        String idExp = driver.findElement(By.xpath("//*[@id=\"employeeId\"]")).getAttribute("value");
        driver.findElement(By.xpath("//*[@id=\"photofile\"]")).sendKeys("C:\\Users\\kadir\\eclipse-workspace\\SeleniumBatchV\\screenshots\\HW-Class-9\\AA-SS.png");
        driver.findElement(By.xpath("//*[@id=\"btnSave\"]")).click();
        // verifying employee added succsessfully
        // name validation
        String nameEnt = "James Franco";
        String nameDisp = driver.findElement(By.xpath("//div[@id='profile-pic']/h1")).getText();
        SoftAssert softA = new SoftAssert();
        softA.assertEquals(nameDisp, nameEnt,"Names match");
        // id validation
        String idEnt = driver.findElement(By.xpath("//*[@id=\"personal_txtEmployeeId\"]")).getAttribute("value");
        softA.assertEquals(idEnt, idExp,"Id's match");
        boolean picDisp = driver.findElement(By.xpath("//*[@id=\"empPic\"]")).isDisplayed();
        //picDisp =false;
        softA.assertTrue(picDisp, "Picture is diplayed");
        
        softA.assertAll();
    }

	}


