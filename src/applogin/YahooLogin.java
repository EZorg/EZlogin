package applogin;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class YahooLogin {
	public static void email(WebDriver driver, String uname, String passwd){
		try{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			//Actual Launch
			driver.get("http://mail.yahoo.com");
			driver.findElement(By.id("login-username")).click();
			driver.findElement(By.id("login-username")).clear();
			driver.findElement(By.id("login-username")).sendKeys(uname);
			driver.findElement(By.id("login-passwd")).clear();
			driver.findElement(By.id("login-passwd")).sendKeys(passwd);
			driver.findElement(By.id("login-signin")).click();
		}catch(Exception e){}
	}
}
