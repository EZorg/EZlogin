import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import forums.*;

public class FormCompletion {
	//Setting Path and Loading up Firefox Binary
	private final String locationWindows = "browser" +
			File.separator + "FirefoxPortable" + File.separator + "FirefoxPortable.exe";
	private final String locationMac = "browser"+ File.separator + "Firefox.app";
	
	private File pathToBinary;
	private FirefoxBinary ffBinary;
	private FirefoxProfile firefoxProfile;
	private WebDriver driver;
	
	//for multiple tab handling
	private boolean previousLogin;
	
	private String uname;
	private String[] userForum;
	
	public FormCompletion(String n){
		uname = n;
		userForum = new String[6];
	}
	
	private void init(){
		//Setting Path and Loading up Firefox Binary
		//note it sets the path depending on which os its on
		if(OSDetect.isWindows()){
			pathToBinary = new File(locationWindows);
			ffBinary = new FirefoxBinary(pathToBinary);
			firefoxProfile = new FirefoxProfile();
			driver = new FirefoxDriver(ffBinary,firefoxProfile);
		}
		else{
			driver = new FirefoxDriver();
		}
	}
	
	private void grabProfile(){
		DB.loadForumData(uname,userForum);
	}
	
	private void handleTabs(){
		//this conditional is used to check if we need to open a new tab or not
		if(previousLogin)
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
		else previousLogin = true;
	}
	
	public void createAccounts(int[] val){
		grabProfile();
		init();
		
		for(int i = 0; i < 7 && i<val.length; i++){
			if(val[i]==1) createAccount(i);
		}
                previousLogin=false;
	}
	
        public String[] getProfile(){
            grabProfile();//loads the array of data so we can use it
            return userForum;
        }
        
	private void createAccount(int num){
		//userForum[0]=uname | userForum[1]=first_n | userForum[2]=last_n
		//userForum[3]=bday | userForum[4]=phone | userForum[5]=email
		switch (num){
		case 0://google account
			handleTabs();
			EmailCreate.googleCreate(driver, userForum[1], userForum[2], userForum[0], userForum[4], userForum[3]);
			break;
			
		case 1: //yahoo account
			handleTabs();
			EmailCreate.yahooCreate(driver, userForum[1], userForum[2], userForum[0], userForum[4], userForum[3]);
			break;
			
		case 2: //facebook
			handleTabs();
			SocialMediaCreate.createFacebook(driver, userForum[1], userForum[2], userForum[5], userForum[3]);
			break;
			
		case 3: //twitter
			handleTabs();
			SocialMediaCreate.twitterCreate(driver, userForum[1], userForum[5], userForum[0]);
			break;
			
		case 4://tumblr
			handleTabs();
			SocialMediaCreate.tumblrCreate(driver, userForum[5], userForum[0]);
			break;
			
		case 5: //github
			handleTabs();
			ProfessionalCreate.githubCreate(driver, userForum[0], userForum[5]);
			break;
			
		case 6: //linked
			handleTabs();
			ProfessionalCreate.linkedinCreate(driver, userForum[1], userForum[2], userForum[5]);
			break;
			
			default:
				System.out.println("Wrong Input");
				break;
		}
	}
}
