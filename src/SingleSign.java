import applogin.*; //lincludes our login classes

import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * This class is the main class used
 * to access all our single sign on applications
 * @author beshoi
 *
 */
public class SingleSign {
	//Setting Path and Loading up Firefox Binary
	private final String locationWindows = "browser" +
			File.separator + "FirefoxPortable" + File.separator + "FirefoxPortable.exe";
	private final String locationMac = "browser"+File.separator +
			"Firefox.app";
	
	private File pathToBinary;
	private FirefoxBinary ffBinary;
	private FirefoxProfile firefoxProfile;
	private WebDriver driver;
	
	//for multiple tab handling
	private boolean previousLogin;
	
	private String uname;
	private int[] uloginProfile;
	private String[][] loginAuth;
	
	public SingleSign(String n){
		uname = n;
		uloginProfile = new int[23];
		loginAuth = new String[23][2];
		previousLogin = false;
	}
        
	public int[] getLoginProfile(){
            grabProfile();
            return uloginProfile;
        }
        
	private void grabProfile(){
		DB.loadProfile(uname, uloginProfile, loginAuth);
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
	
	public void login(){
		grabProfile(); //loads the single sign profile needed
		init(); //used to initialize the web drivers
		int i;
		try{
			for(i = 0; i < uloginProfile.length; i++){
				//uloginProfile acts as a boolean where 1 is true and 0 is false
				if(uloginProfile[i] == 1 && loginAuth[i][0].length()>0 && loginAuth[i][1].length() >0 )
					loginAccount(i);
			}
		}catch(Exception e){}
        previousLogin=false;
	}
	
	private void handleTabs(){
		//this conditional is used to check if we need to open a new tab or not
		if(previousLogin)
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
		else previousLogin = true;
	}
	
	private void loginAccount(int num){
		switch(num){
		case 0: //gmail
			handleTabs();
			GoogleLogin.gmail(driver, loginAuth[0][0], loginAuth[0][1]);
			break;
			
		case 1: //youtube
			handleTabs();
			GoogleLogin.youtube(driver, loginAuth[1][0], loginAuth[1][1]);
			break;
			
		case 2: //gdrive
			handleTabs();
			GoogleLogin.gdrive(driver, loginAuth[2][0], loginAuth[2][1]);
			break;
			
		case 3: //yahoo email
			handleTabs();
			YahooLogin.email(driver, loginAuth[3][0], loginAuth[3][1]);
			break;
		
		case 4: //facebook
			handleTabs();
			SocialMedia.facebook(driver, loginAuth[4][0], loginAuth[4][1]);
			break;
			
		case 5: //twitter
			handleTabs();
			SocialMedia.twitter(driver, loginAuth[5][0], loginAuth[5][1]);
			break;
			
		case 6: //tumblr
			handleTabs();
			SocialMedia.tumblr(driver, loginAuth[6][0], loginAuth[6][1]);
			break;
			
		case 7: //gw email
			handleTabs();
			GWU.gwmail(driver, loginAuth[7][0], loginAuth[7][1]);
			break;
			
		case 8: //gw blackboard
			handleTabs();
			GWU.blackboard(driver, loginAuth[8][0], loginAuth[8][1]);
			break;
			
		case 9: //gw banweb
			handleTabs();
			GWU.banweb(driver, loginAuth[9][0], loginAuth[9][1]);
			break;
			
		case 10: //amazon
			handleTabs();
			Shopping.amazon(driver, loginAuth[10][0], loginAuth[10][1]);
			break;
			
		case 11: //newegg
			handleTabs();
			Shopping.newegg(driver, loginAuth[11][0], loginAuth[11][1]);
			break;
			
		case 12: //ebay
			handleTabs();
			Shopping.ebay(driver, loginAuth[12][0], loginAuth[12][1]);
			break;
			
		case 13: //netflix
			handleTabs();
			Watch.netflix(driver, loginAuth[13][0], loginAuth[13][1]);
			break;
			
		case 14: //hulu
			handleTabs();
			Watch.hulu(driver, loginAuth[14][0], loginAuth[14][1]);
			break;
			
		case 15: //linkedin
			handleTabs();
			Professional.linkedin(driver, loginAuth[15][0], loginAuth[15][1]);
			break;
			
		case 16: //github
			handleTabs();
			Professional.github(driver, loginAuth[16][0], loginAuth[16][1]);
			break;
			
		case 17: //piazza
			handleTabs();
			Professional.piazza(driver, loginAuth[17][0], loginAuth[17][1]);
			break;
			
		case 18: //trello
			handleTabs();
			Professional.trello(driver, loginAuth[18][0], loginAuth[18][1]);
			break;
			
		case 19: //imgur
			handleTabs();
			Random.imgur(driver, loginAuth[19][0], loginAuth[19][1]);
			break;
			
		case 20: //reddit
			handleTabs();
			Random.reddit(driver, loginAuth[20][0], loginAuth[20][1]);
			break;
			
		case 21: //ello
			handleTabs();
			Random.ello(driver, loginAuth[21][0], loginAuth[21][1]);
			break;
			
		case 22: //battle.net
			handleTabs();
			Random.battlenet(driver, loginAuth[22][0], loginAuth[22][1]);
			break;
			
			default:
				System.out.println("Invalid Statement");
				break;
		}
	}
}
