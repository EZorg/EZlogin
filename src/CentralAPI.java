import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class is create is the cross over between our backend and
 * front facing gui all gui objects will interact exclusivly with centralapi
 * @author beshoi
 *
 */
public class CentralAPI {
	private Accounts account;
	private DB db;
	private SingleSign ss;
	private FormCompletion fc;
	
	private String userName;
	private String password;
	
	public CentralAPI(String uname, String passwd){
		init(uname, passwd);
	}
	
	public CentralAPI(){
		account = new Accounts();
	}
	
	private void init(String uname, String passwd){
		userName = uname;
		password = passwd;
		
		db = new DB(uname, passwd);
		ss = new SingleSign(uname);
		fc = new FormCompletion(uname);
		account = new Accounts();
	}
	
	public boolean login(){
		db.connect();//open db
                account.readFromFile();
		if(account.doesUserExist(userName)){
			if(db.auth()){
                             account.writeToFile();
				db.close(); //close db
				return true;
			}
		}
                account.writeToFile();
		db.close();//close db
		return false;
	}
	
	public boolean checkName(String uname){
		account.readFromFile();
		if(account.doesUserExist(uname)){
			account.writeToFile();
			return false;
		}
		account.writeToFile();
		return true;
	}
	
	public void createUser(String uname, String passwd, String first_n, String last_n,
							String bday, String phone, String email){
		init(uname,passwd);
		
		account.readFromFile();
		account.addUser(uname);
		account.writeToFile();
		
		db.connect();
		db.createTable();
		db.insertForum(first_n, last_n, bday, phone, email);
		db.close();
	}
	
	public void addToVault(String provider, String name, String pass, String comments){		
		db.connect();
		db.insertRow(2, provider, name, pass, comments);
		db.close();
	}
	
	public String[][] getVault(){
		db.connect();
		LinkedList<LinkedList<String>> temp = db.returnVault();
		String[][] vault = new String[temp.size()][5];
          
		for(int i = 0; i < temp.size(); i++){
			for(int j = 0; j < 5; j++){
				vault[i][j] = temp.get(i).get(j);
			}
		}
		db.close();
		return vault;
	}
	
	public void deleteFromVault(String l_num){
		db.connect();
		db.deleteRow(Integer.parseInt(l_num));
		db.close();
	}
	
	public void updateVault(String id, String provider, String name,String pass, String comments){
		db.connect();
		db.updateRow(Integer.parseInt(id), 2, provider, name, pass, comments);
		db.close();
	}
        
        public void updateVault(String id, boolean log,String provider, String name,
        		String pass, String comments){        	
        	int login = 0;
            if(log)login=1;
            
            db.connect();
            db.updateRow(Integer.parseInt(id),login,provider,name,pass,comments);
            db.close();
        }
        
        public void formCompletion(int[] a){
            fc.createAccounts(a);
        }
        
        //allows for the user to change which programs they want to auto login into
        public void updateLoginSS(String id, boolean log){
            int login = 0;
            if(log==true){
                login=1;
            }
            
            db.connect();
            db.updateSingleSign(Integer.parseInt(id), login);
            db.close();
        }
        
        //loads up the vaults login status for the 23 supported single sign applications
        public boolean[] loginStatus(){
            int[] uProfile = ss.getLoginProfile();
            boolean[] profile = new boolean[uProfile.length];
            
            for(int i = 0; i< uProfile.length; i++){
               if(uProfile[i]==1)profile[i]=true;
               else profile[i]=false;
            }
            return profile;
        }
        
        //launches the single sign on application to log into your accounts
        public void launchSS(){
            ss.login();
        }
        
        //allows for the user to update whatever info they have related to their account
        public void about(String first, String last, String bday, String phone, 
        		String email){     	
        	db.connect();
        	if(first.length() > 0){
                    System.out.println(first);
        		db.updateFirstName(first);
        	}
        	
        	if(last.length() > 0){
        		db.updateLastName(last);
        	}
        	
        	if(bday.length() > 0){
        		db.updateBday(bday);
        	}
        	
        	if(phone.length() > 0){
        		db.updatePhone(phone);
        	}
        	
        	if(email.length() > 0){
        		db.updateEmail(email);
        	}
        	db.close();
        }
        
        public boolean aboutPassword(String oldPass, String newPass){
        	boolean changedPassword = false;
        	
        	db.connect();
            if(oldPass.equals(password) && newPass.length() > 0){
        		db.changePass(newPass);
                password = newPass;
                changedPassword = true;
            }
            db.close();
            
            return changedPassword;
        }
        
        public String[] getUserInfo(){
            //temp is of size 6 temp[0]= uname | temp[1]= first | temp[2]= last 
            //temp[3]= bday | temp[4]= phone | temp[5]= email
            
            //userInf will have size 5 and will have all info except for the uname
            //userInf[0]= first | userInf[1]= last | userInf[2]= bday | userInf[3]=phone | userInf[4]= email
            String[] temp = fc.getProfile();
            String[] userInf = new String[temp.length-1];
            
            for(int i = 0; i< userInf.length; i++){
                userInf[i]=temp[i+1];
            }
            return userInf;
        }
        
        //deletes all of the users data that we have stored on them
        public void deleteAccount(){
        	//deletes the username from our .bsb file
        	account.readFromFile();
        	account.deleteUser(userName);
        	account.writeToFile();
        	
        	//this will delete all the tables and records we have on them
        	db.connect();
        	db.deleteForum();
        	db.removeTable();
        	db.close();
        }
}
