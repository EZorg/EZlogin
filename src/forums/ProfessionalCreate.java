package forums;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfessionalCreate {
	private static final String[] month = {"January", "February", "March", "April", "May", "June",
		"July", "August", "September", "October", "November", "December"};
	
	private static final String[] monthSimple = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
		"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	
	public static void githubCreate(WebDriver driver, String uname, String email){
		try{
			driver.get("https://github.com/join");
			driver.findElement(By.id("user_login")).clear();
			driver.findElement(By.id("user_login")).sendKeys(uname);
			driver.findElement(By.id("user_email")).clear();
			driver.findElement(By.id("user_email")).sendKeys(email);
		}catch(Exception e){}
	}
	
	public static void linkedinCreate(WebDriver driver, String first_n, String last_n, String email){
		try{
			driver.get("https://www.linkedin.com/reg/join");
			driver.findElement(By.id("firstName-coldRegistrationForm")).clear();
			driver.findElement(By.id("firstName-coldRegistrationForm")).sendKeys(first_n);
			driver.findElement(By.id("lastName-coldRegistrationForm")).clear();
			driver.findElement(By.id("lastName-coldRegistrationForm")).sendKeys(last_n);
			driver.findElement(By.id("email-coldRegistrationForm")).clear();
			driver.findElement(By.id("email-coldRegistrationForm")).sendKeys(email);
		}catch(Exception e){}
	}

}
