package forums;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SocialMediaCreate {
	private static final String[] month = {"January", "February", "March", "April", "May", "June",
		"July", "August", "September", "October", "November", "December"};
	
	private static final String[] monthSimple = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
		"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	
	public static void createFacebook(WebDriver driver, String first_n, String last_n, 
										String email, String bday){
		
		String mon = bday.charAt(0)+""+bday.charAt(1)+"";
		int monthToInt = Integer.parseInt(mon)-1;
		String day = bday.charAt(2)+""+bday.charAt(3)+"";
                int checkDay = Integer.parseInt(day);
                day = Integer.toString(checkDay);
		String year = bday.charAt(4)+""+bday.charAt(5)+""+bday.charAt(6)+""+bday.charAt(7)+"";

		try{
			driver.get("https://www.facebook.com/");
			driver.findElement(By.id("u_0_1")).clear();
			driver.findElement(By.id("u_0_1")).sendKeys(first_n);
			driver.findElement(By.id("u_0_3")).clear();
			driver.findElement(By.id("u_0_3")).sendKeys(last_n);
			driver.findElement(By.id("u_0_5")).clear();
			driver.findElement(By.id("u_0_5")).sendKeys(email);
			driver.findElement(By.id("u_0_8")).clear();
			driver.findElement(By.id("u_0_8")).sendKeys(email);
			new Select(driver.findElement(By.id("month"))).selectByVisibleText(monthSimple[monthToInt]);
			new Select(driver.findElement(By.id("day"))).selectByVisibleText(day);
			new Select(driver.findElement(By.id("year"))).selectByVisibleText(year);
		}catch(Exception e){}
	}
	
	public static void twitterCreate(WebDriver driver, String first_n, String email, String uname){
		try{
			driver.get("https://twitter.com/signup");
			driver.findElement(By.id("full-name")).clear();
			driver.findElement(By.id("full-name")).sendKeys(first_n);
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("email")).sendKeys(email);
			driver.findElement(By.id("username")).clear();
			driver.findElement(By.id("username")).sendKeys(uname);
		}catch(Exception e){}
	}
	
	public static void tumblrCreate(WebDriver driver, String email, String uname){
		try{
			driver.get("https://www.tumblr.com/");
			driver.findElement(By.id("signup_email")).clear();
			driver.findElement(By.id("signup_email")).sendKeys(email);
			driver.findElement(By.id("signup_username")).clear();
			driver.findElement(By.id("signup_username")).sendKeys(uname);
		}catch(Exception e){}
	}
}
