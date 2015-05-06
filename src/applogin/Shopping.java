package applogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Shopping {
	public static void amazon(WebDriver driver, String uname, String passwd){
		try{
			driver.get("https://www.amazon.com/gp/sign-in.html");
			driver.findElement(By.id("ap_email")).clear();
			driver.findElement(By.id("ap_email")).sendKeys(uname);
			driver.findElement(By.id("ap_password")).clear();
			driver.findElement(By.id("ap_password")).sendKeys(passwd);
			driver.findElement(By.id("signInSubmit-input")).click();
		}catch(Exception e){}
	}
	
	public static void newegg(WebDriver driver, String uname, String passwd){
		try{
			driver.get("http://www.newegg.com/");
			driver.findElement(By.className("tab-not-logged-in")).click();
			driver.findElement(By.id("UserName")).clear();
			driver.findElement(By.id("UserName")).sendKeys(uname);
			driver.findElement(By.id("UserPwd")).clear();
			driver.findElement(By.id("UserPwd")).sendKeys(passwd);
			driver.findElement(By.id("submit")).click();
		}catch(Exception e){}
	}
	
	public static void ebay(WebDriver driver, String uname, String passwd){
		try{
			driver.get("https://signin.ebay.com");
			driver.findElement(By.id("userid")).clear();
			driver.findElement(By.id("userid")).sendKeys(uname);
			driver.findElement(By.id("pass")).clear();
			driver.findElement(By.id("pass")).sendKeys(passwd);
			driver.findElement(By.id("sgnBt")).click();
		}catch(Exception e){}
	}
}
