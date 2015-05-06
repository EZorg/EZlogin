package applogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Random {
	public static void imgur(WebDriver driver, String uname, String passwd){
		try{
			driver.get("https://imgur.com/signin");
			driver.findElement(By.id("username")).clear();
			driver.findElement(By.id("username")).sendKeys(uname);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(passwd);
			driver.findElement(By.name("submit")).click();
		}catch(Exception e){}
	}
	
	public static void reddit(WebDriver driver, String uname, String passwd){
		try{
			driver.get("http://www.reddit.com/");
			driver.findElement(By.name("user")).clear();
			driver.findElement(By.name("user")).sendKeys(uname);
			driver.findElement(By.name("passwd")).clear();
			driver.findElement(By.name("passwd")).sendKeys(passwd);
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		}catch(Exception e){}
	}
	
	public static void ello(WebDriver driver, String uname, String passwd){
		try{
			driver.get("https://ello.co/enter");
			driver.findElement(By.id("user_email")).clear();
			driver.findElement(By.id("user_email")).sendKeys(uname);
			driver.findElement(By.id("user_password")).clear();
			driver.findElement(By.id("user_password")).sendKeys(passwd);
			driver.findElement(By.name("commit")).click();
		}catch(Exception e){}
	}
	
	public static void battlenet(WebDriver driver, String uname, String passwd){
		try{
			driver.get("https://us.battle.net/login/");
			driver.findElement(By.id("accountName")).clear();
			driver.findElement(By.id("accountName")).sendKeys(uname);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(passwd);
			driver.findElement(By.id("submit")).click();
		}catch(Exception e){}
	}
}
