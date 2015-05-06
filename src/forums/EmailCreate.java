package forums;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class EmailCreate {
	private static final String[] month = {"January", "February", "March", "April", "May", "June",
									"July", "August", "September", "October", "November", "December"};
	
	public static void yahooCreate(WebDriver driver, String first_n, String last_n, String uname,
									String phone, String bday){
		
		String mon = bday.charAt(0)+""+bday.charAt(1)+"";
		int monthToInt = Integer.parseInt(mon)-1;
		String day = bday.charAt(2)+""+bday.charAt(3)+"";
                int checkDay = Integer.parseInt(day);
                day = Integer.toString(checkDay);
		String year = bday.charAt(4)+""+bday.charAt(5)+""+bday.charAt(6)+""+bday.charAt(7)+"";
		
		try{
			driver.get("https://edit.yahoo.com/registration");
			driver.findElement(By.id("first-name")).clear();
			driver.findElement(By.id("first-name")).sendKeys(first_n);
			driver.findElement(By.id("last-name")).clear();
			driver.findElement(By.id("last-name")).sendKeys(last_n);
			driver.findElement(By.id("user-name")).clear();
			driver.findElement(By.id("user-name")).sendKeys(uname);
			driver.findElement(By.id("mobile")).clear();
			driver.findElement(By.id("mobile")).sendKeys(phone);
			new Select(driver.findElement(By.id("month"))).selectByVisibleText(month[monthToInt]);
			new Select(driver.findElement(By.id("day"))).selectByVisibleText(day);
			new Select(driver.findElement(By.id("year"))).selectByVisibleText(year);
		}catch(Exception e){}
	}
	
	public static void googleCreate(WebDriver driver, String first_n, String last_n, String uname,
								String phone, String bday){
		String mon = bday.charAt(0)+""+bday.charAt(1)+"";
		int monthToInt = Integer.parseInt(mon)-1;
		String day = bday.charAt(2)+""+bday.charAt(3)+"";
		String year = bday.charAt(4)+""+bday.charAt(5)+""+bday.charAt(6)+""+bday.charAt(7)+"";
		
		try{
			driver.get("https://accounts.google.com/signup");
			driver.findElement(By.id("FirstName")).clear();
			driver.findElement(By.id("FirstName")).sendKeys(first_n);
			driver.findElement(By.id("LastName")).clear();
			driver.findElement(By.id("LastName")).sendKeys(last_n);
			driver.findElement(By.id("GmailAddress")).clear();
			driver.findElement(By.id("GmailAddress")).sendKeys(uname);
			driver.findElement(By.id("BirthDay")).clear();
			driver.findElement(By.id("BirthDay")).sendKeys(day);
			driver.findElement(By.id("BirthYear")).clear();
			driver.findElement(By.id("BirthYear")).sendKeys(year);
			driver.findElement(By.id("RecoveryPhoneNumber")).click();
			driver.findElement(By.id("RecoveryPhoneNumber")).clear();
			driver.findElement(By.id("RecoveryPhoneNumber")).sendKeys(phone);
		}catch(Exception e){}

		
	}
}
