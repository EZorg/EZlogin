package applogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Watch {
	public static void netflix(WebDriver driver, String uname, String passwd){
		try{
			driver.get("https://www.netflix.com/Login");
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("email")).sendKeys(uname);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(passwd);
			driver.findElement(By.id("login-form-contBtn")).click();
		}catch(Exception e){}
	}
	
	public static void hulu(WebDriver driver, String uname, String passwd){
		try{
			driver.get("https://secure.hulu.com/account/signin");
			driver.findElement(By.id("login")).clear();
			driver.findElement(By.id("login")).sendKeys(uname);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(passwd);
			driver.findElement(By.className("login-submit")).click();
		}catch(Exception e){}
	}
}
