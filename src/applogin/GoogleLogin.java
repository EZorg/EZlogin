package applogin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleLogin {
	//logos into gmail
	public static void gmail(WebDriver driver, String uname, String passwd){
		try{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			//Actual Launch
			driver.get("http://www.gmail.com");
			driver.findElement(By.id("Email")).clear();
			driver.findElement(By.id("Email")).sendKeys(uname);
			driver.findElement(By.id("Passwd")).clear();
			driver.findElement(By.id("Passwd")).sendKeys(passwd);
			driver.findElement(By.id("signIn")).click();
		}catch(Exception e){}
	}
	
	public static void youtube(WebDriver driver, String uname, String passwd){
		try{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("https://accounts.google.com/ServiceLogin?service=youtube&continue=https%3A%2F%2Fwww.youtube.com%2Fsignin%3Ffeature%3Dsign_in_button%26hl%3Den%26action_handle_signin%3Dtrue%26next%3D%252F%26app%3Ddesktop&uilel=3&hl=en&passive=true");
			driver.findElement(By.id("Email")).clear();
			driver.findElement(By.id("Email")).sendKeys(uname);
			driver.findElement(By.id("Passwd")).clear();
			driver.findElement(By.id("Passwd")).sendKeys(passwd);
			driver.findElement(By.id("signIn")).click();
			driver.findElement(By.cssSelector("button.iph-dialog-close")).click();
		}catch(Exception e){}
	}
	
	public static void gdrive(WebDriver driver, String uname, String passwd){
		try{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("https://drive.google.com");
			driver.findElement(By.id("Email")).clear();
			driver.findElement(By.id("Email")).sendKeys(uname);
			driver.findElement(By.id("Passwd")).clear();
			driver.findElement(By.id("Passwd")).sendKeys(passwd);
			driver.findElement(By.id("signIn")).click();
		}catch(Exception e){}
	}
}
