package applogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GWU {
	public static void gwmail(WebDriver driver, String uname, String passwd){
		try{
			driver.get("https://singlesignon.gwu.edu/idp/Authn/UserPassword");
			driver.findElement(By.name("j_username")).clear();
			driver.findElement(By.name("j_username")).sendKeys(uname);
			driver.findElement(By.name("j_password")).clear();
			driver.findElement(By.name("j_password")).sendKeys(passwd);
			driver.findElement(By.cssSelector("button.form-element.form-button")).click();
		}catch(Exception e){}
	}
	
	public static void blackboard(WebDriver driver, String uname, String passwd){
		try{
			driver.get("https://blackboard.gwu.edu/");
			driver.findElement(By.id("user_id")).clear();
			driver.findElement(By.id("user_id")).sendKeys(uname);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(passwd);
			driver.findElement(By.cssSelector("input[name=\"login\"]")).click();
		}catch(Exception e){}
	}
	
	public static void banweb(WebDriver driver, String uname, String passwd){
		try{
			driver.get("https://banweb.gwu.edu/");
			driver.findElement(By.id("UserID")).clear();
			driver.findElement(By.id("UserID")).sendKeys(uname);
			driver.findElement(By.name("PIN")).clear();
			driver.findElement(By.name("PIN")).sendKeys("afdas");
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		}catch(Exception e){}
	}

}
