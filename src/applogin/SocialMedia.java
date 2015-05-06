package applogin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SocialMedia {
	public static void facebook(WebDriver driver, String uname, String passwd){
		try{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("http://www.facebook.com");
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("email")).sendKeys(uname);
			driver.findElement(By.id("pass")).clear();
			driver.findElement(By.id("pass")).sendKeys(passwd);
			driver.findElement(By.id("u_0_n")).click();
		}catch(Exception e){}
	}
	
	public static void twitter(WebDriver driver, String uname, String passwd){
		try{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("https://www.twitter.com");
			driver.findElement(By.id("signin-email")).clear();
			driver.findElement(By.id("signin-email")).sendKeys(uname);
			driver.findElement(By.id("signin-password")).clear();
			driver.findElement(By.id("signin-password")).sendKeys(passwd);
			driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		}catch(Exception e){}
	}
	
	public static void tumblr(WebDriver driver, String uname, String passwd){
		try{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("https://www.tumblr.com/login");
			driver.findElement(By.id("signup_email")).clear();
			driver.findElement(By.id("signup_email")).sendKeys(uname);
			driver.findElement(By.id("signup_password")).clear();
			driver.findElement(By.id("signup_password")).sendKeys(passwd);
			driver.findElement(By.id("signup_forms_submit")).click();
		}catch(Exception e){}
	}
}
