package applogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Professional {
	public static void piazza(WebDriver driver, String uname, String passwd){
		try{
			driver.get("https://piazza.com/account/login");
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("email")).sendKeys(uname);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(passwd);
			driver.findElement(By.id("submit")).click();
		}catch(Exception e){}
	}
	
	public static void github(WebDriver driver, String uname, String passwd){
		try{
			driver.get("https://github.com/login");
			driver.findElement(By.id("login_field")).clear();
			driver.findElement(By.id("login_field")).sendKeys(uname);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(passwd);
			driver.findElement(By.name("commit")).click();
		}catch(Exception e){}
	}
	
	public static void trello(WebDriver driver, String uname, String passwd){
		try{
			driver.get("https://trello.com/login");
			driver.findElement(By.id("user")).clear();
			driver.findElement(By.id("user")).sendKeys(uname);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(passwd);
			driver.findElement(By.id("login")).click();
		}catch(Exception e){}
	}
	
	public static void linkedin(WebDriver driver, String uname, String passwd){
		try{
			driver.get("https://www.linkedin.com/uas/login");
			driver.findElement(By.id("session_key-login")).clear();
			driver.findElement(By.id("session_key-login")).sendKeys(uname);
			driver.findElement(By.id("session_password-login")).clear();
			driver.findElement(By.id("session_password-login")).sendKeys(passwd);
			driver.findElement(By.id("btn-primary")).click();
		}catch(Exception e){}
	}
}
