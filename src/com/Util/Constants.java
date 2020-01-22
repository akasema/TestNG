package com.Util;

import org.openqa.selenium.By;

public class Constants extends CommonMethods{
	public static final String HRMS_URL="http://166.62.36.207/humanresources/symfony/web/index.php/auth/login";
	public static final String Practice_URL="http://166.62.36.207/syntaxpractice/index.html";
	public static final String TOOLSQA="https://www.toolsqa.com/";
	
	public static void logInHRMS() throws InterruptedException {
		driver.findElement(By.id("txtUsername")).sendKeys("admin");
		 driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
		 driver.findElement(By.id("btnLogin")).click();
				 
	}
	 


}
