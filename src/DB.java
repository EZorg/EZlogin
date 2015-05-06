
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

/**
 * This is a derby embedded database api
 * used specifically for the needs of our webscrab tool
 * @author beshoi
 *
 */
public class DB {
	private final String DB_NAME = "db_userProfile";//database that is used for all clients
	
	private String uname;
	private String passwd;
	
	private Connection conn1;//used to open the database
	private Statement sta;//used to execute sql queries
	private ResultSet res;//used to store the results of an sql querie
	
	private LinkedList<LinkedList<String>> userProfile; 
	
	public DB(String n, String pass){
		uname = n;
		passwd = pass;
		
		userProfile = new LinkedList<LinkedList<String>>();
	}
	
	private DB(String n){
		//Empty Constructor Only Used to help with Compatability issues with SingleSign Class
	}
/************************DML API FOR Derby DB****************************************/
/**
 * the DML portion of the database is used to insert a row, update a row, or delete a row
 * @param num
 * @param login
 * @param provider
 * @param name
 * @param pass
 * @param comments
 * @return
 */
        //update singlesign
        public boolean updateSingleSign(int id, int login){
            String sqlStatment = "UPDATE tb_"+uname+" "+
                    "SET LOGIN="+login+" WHERE L_NUM="+id;
            try{
                sta.executeUpdate(sqlStatment);
                return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }
        
	//update row
	public boolean updateRow(int id, int login, String provider, String name,
							String pass, String comments){
		
		String sqlStatement = "UPDATE tb_"+cleanString(uname)+" "+
							"SET LOGIN="+login+", "+
							"PROVIDER='"+cleanString(provider)+"', "+
							"UNAME='"+cleanString(name)+"', "+
							"PASSWD='"+cleanString(pass)+"', "+
							"COMMENT='"+cleanString(comments)+"' "
							+"WHERE L_NUM="+id;
		try{
			sta.execute(sqlStatement);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//inserting a row into table
	public boolean insertRow(int login, String provider, String name, 
				String pass, String comments){
		
		String sqlStatement = "INSERT INTO tb_"+cleanString(uname)+" "+
							"(LOGIN, PROVIDER, UNAME, PASSWD, COMMENT) "+
							"VALUES("+login+", '"+cleanString(provider)+"', '"+
							cleanString(name)+"', '"+cleanString(pass)+
							"', '"+cleanString(comments)+"')";
		try{
			sta.executeUpdate(sqlStatement);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//delete a row from a table
	public boolean deleteRow(int id){
		String sqlStatment = "DELETE FROM tb_"+uname+" WHERE L_NUM="+id;
		try{
			sta.executeUpdate(sqlStatment);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	protected static void loadProfile(String username, int[] userPref, String[][] uAuth){
		DB temp = new DB(username);
                temp.connect();
		temp.actualProfileLoad(username, userPref, uAuth);
                temp.close();
		//Polymorphism at its finest and thats how programs explode
	}
	
	private void actualProfileLoad(String username, int[] userPref, String[][] uAuth){
				
		String sqlStatment = "SELECT LOGIN, UNAME, PASSWD FROM tb_"+
					cleanString(username)+" WHERE L_NUM<24";
		
		try{
			res = sta.executeQuery(sqlStatment);
			
			for(int i = 0; res.next(); i++){
				userPref[i]=res.getInt("LOGIN");
				uAuth[i][0]=res.getString("UNAME");
				uAuth[i][1]=res.getString("PASSWD");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{res.close();}catch(Exception e){}
		}
	}
	
	public LinkedList<LinkedList<String>> returnVault(){
		loadingVault();
		return userProfile;
	}
	
	private void loadingVault(){
		String sqlStatment = "SELECT * FROM tb_"+cleanString(uname);
		userProfile.clear();
		try{
			res = sta.executeQuery(sqlStatment);
			
			for(int i = 0; res.next(); i++){
				LinkedList<String> temp = new LinkedList<>();
				temp.addLast(res.getString("L_NUM"));
				temp.addLast(res.getString("PROVIDER"));
				temp.addLast(res.getString("UNAME"));
				temp.addLast(res.getString("PASSWD"));
				temp.addLast(res.getString("COMMENT"));
				
				userProfile.addLast(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{res.close();}catch(Exception e){}
		}
	}
/**********************authentication and Form methods**********************************/
/**
 * these add users
 * remove users
 * and authenticate users in our table_authentication table
 * it also takes care of forum fill out
 * @return
 */
	//adding user to table_authentication table
	private boolean addUser(){
		String sqlStatement = "INSERT INTO table_authentication "+
							"(UNAME, PASSWD) VALUES ('"+cleanString(uname)
							+"', '"+cleanString(passwd)+"')";
		try{
			sta.executeUpdate(sqlStatement);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//this is the only public method to access table_authentication because it
	//alows the user to change their password
	public boolean changePass(String pass){
		String sqlStatement = "UPDATE table_authentication "+
				"SET PASSWD='"+cleanString(pass)+"' "
				+"WHERE UNAME='"+cleanString(uname)+"'";
		try{
			sta.executeUpdate(sqlStatement);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//remove user from table_authentication table
	private boolean removeUser(){
		String sqlStatement = "DELETE FROM table_authentication WHERE UNAME='"+
				cleanString(uname)+"'"
				+ "AND PASSWD='"+cleanString(passwd)+"'";
		try{
			sta.execute(sqlStatement);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//authenticates to the database using that objects logins
	public  boolean auth(){
		String sqlStatement = "SELECT PASSWD FROM table_authentication WHERE UNAME='"+
						cleanString(uname)+"'";
		try{
			res = sta.executeQuery(sqlStatement);
			
			if(res.next()){
				if(res.getString("PASSWD").equals(passwd)){
					return true;
				}
				else
					return false;
			}
			else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally{
			try{res.close();}catch(Exception e){}
		}
	}
/**********************************END OF AUTHENTICATION AND FORUMS METHODS**************/

/*************************Exiting DML API FOR DERBY DB**********************************/
	
/*************************ENTERING THE DDL FOR DERBY DB********************************/
/**
 * THE DDL methods are for creating a database and removing a database
 * @return
 */
	//creates a table inside the db_userProfile database
	public boolean createTable(){
		String sqlStatment = "CREATE TABLE tb_"+cleanString(uname)+
								" (L_NUM INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
								+ "LOGIN INT NOT NULL, PROVIDER VARCHAR(40),"+
								"UNAME VARCHAR(80), PASSWD VARCHAR(40), COMMENT VARCHAR(200))";
		
		try{
			sta.executeUpdate(sqlStatment);
			addUser();//adds a user that this table belongs to
			allocateSingleSignSpace();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	private void allocateSingleSignSpace(){
		String[] ss = {"gmail", "youtube", "gdrive", "yahoo", "facebook", "twitter", "tumblr",
					"gwmail", "blackboard", "banweb", "amazon", "newegg", "ebay", "netflix", "hulu",
					"linkedin", "github", "piazza", "trello", "imgur", "reddit", "ello", "battlenet"};
		for(int i = 0; i<ss.length; i++){
			insertRow(0, ss[i], "", "", "");
		}
	}
	public boolean removeTable(){
		String sqlStatment = "DROP TABLE tb_"+cleanString(uname);
		try{
			sta.executeUpdate(sqlStatment);
			removeUser();//removes user that the table belongs to
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
/****************************EXIING DDL FOR DERBY DB***********************************/
	
/******************************Forum BASED METHODS*************************************/
	public static void loadForumData(String username,String[] userForum){
		DB temp = new DB(username);
                temp.connect();
		temp.actualForumLoad(username, userForum);
                temp.close();
	}
	
	private void actualForumLoad(String username, String[] userForum){
		String sqlStatement = "SELECT * FROM table_forums WHERE UNAME='"+cleanString(username)+"'";
		try{
                    if(sta==null)System.out.println("***********STA ISSUE*****************");
			res = sta.executeQuery(sqlStatement);
			
			if(res.next()){
				userForum[0] = res.getString("UNAME");
				userForum[1] = res.getString("FIRST_N");
				userForum[2] = res.getString("LAST_N");
				userForum[3] = res.getString("BDAY");
				userForum[4] = res.getString("PHONE");
				userForum[5] = res.getString("EMAIL");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{res.close();}catch(Exception e){}
		}
	}
	public boolean insertForum(String first_n, String last_n, String bday, String phone, String email){
		String sqlStatement = "INSERT INTO table_forums "+
				"(UNAME, FIRST_N, LAST_N, BDAY, PHONE, EMAIL) "+
				"VALUES('"+uname+"', '"+cleanString(first_n)+"', '"+cleanString(last_n)
				+"', '"+bday+
				"', '"+phone+"', '"+cleanString(email)+"')";
		try{
			sta.executeUpdate(sqlStatement);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateForum(String first_n, String last_n, String bday, String phone, String email){
		String sqlStatement = "UPDATE table_forums"+
				"SET FIRST_N='"+cleanString(first_n)+"', "+
				"LAST_N='"+cleanString(last_n)+"', "+
				"BDAY='"+bday+"', "+
				"PHONE='"+phone+"', "+
				"EMAIL='"+cleanString(email)+"' "
				+"WHERE UNAME='"+cleanString(uname)+"'";
		try{
			sta.executeUpdate(sqlStatement);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteForum(){
		String sqlStatement = "DELETE FROM table_forums WHERE UNAME='"+cleanString(uname)+"'";
		try{
			sta.executeUpdate(sqlStatement);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
/*******************************Exiting Forum METHODS**********************************/
	
/*********************************Random Last Minute Needed Methods*******************/
	/**
	 * The next series of methods should only be used when updating single things
	 * done on the forms side of things
	 * @param first_n
	 * @return
	 */
	
	public boolean updateFirstName(String first_n){
		String sqlStatement = "UPDATE table_forums "+
				"SET FIRST_N='"+cleanString(first_n)+"' "
				+"WHERE UNAME='"+cleanString(uname)+"'";
		try{
			sta.executeUpdate(sqlStatement);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateLastName(String last_n){
		String sqlStatement = "UPDATE table_forums "+
				"SET LAST_N='"+cleanString(last_n)+"' "
				+"WHERE UNAME='"+cleanString(uname)+"'";
		try{
			sta.executeUpdate(sqlStatement);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateBday(String bday){
		String sqlStatement = "UPDATE table_forums "+
				"SET BDAY='"+bday+"' "
				+"WHERE UNAME='"+cleanString(uname)+"'";
		try{
			sta.executeUpdate(sqlStatement);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updatePhone(String phone){
		String sqlStatement = "UPDATE table_forums "+
				"SET PHONE='"+phone+"' "
				+"WHERE UNAME='"+cleanString(uname)+"'";
		try{
			sta.executeUpdate(sqlStatement);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmail(String email){
		String sqlStatement = "UPDATE table_forums "+
				"SET EMAIL='"+cleanString(email)+"' "
				+"WHERE UNAME='"+cleanString(uname)+"'";
		try{
			sta.executeUpdate(sqlStatement);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
/*******************************END of RANDOM Fixes**********************************/
	public boolean connect(){
		try{
			String dbURL = "jdbc:derby:Accounts"+File.separator+
					"UsserDetails"+File.separator+DB_NAME+";"
					+"encryptionKeyLength=256;encryptionAlgorithm=AES/CBC/NoPadding;";
			
			conn1 = DriverManager.getConnection(dbURL);
			sta = conn1.createStatement();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Failure Connecting");
			return false;
		}
	}
	
	public boolean close(){
		try{
			sta.close();
			conn1.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Failure Closing");
			return false;
		}
	}
	
    //its a string cleaner that makes sure that all sql strings are parsed for consistency
    private String cleanString(String input){
    	input = input.replace("'", "''");//escape sequence for database
    	return input;
    }
    
/* USED ONE TIME ONLY
	//creates and encrypts the database for that user using AES256
	public boolean init(){
		try{
			String dbURL = "jdbc:derby:.."+File.separator+".."+File.separator+
					"Accounts"+File.separator+"UsserDetails"+File.separator+DB_NAME+";create=true;"
					+"encryptionKeyLength=256;encryptionAlgorithm=AES/CBC/NoPadding;";
			
			conn1 = DriverManager.getConnection(dbURL,uname,passwd);
			System.out.println("success");
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	authentication db sqlStatment = "CREATE TABLE table_authentication"+
									" (UNAME VARCHAR(40), PASSWD VARCHAR(40))";
	forums db sqlStatment = "CREATE TABLE table_forums"+
								" (UNAME VARCHAR(80) NOT NULL PRIMARY KEY,"
								+ "FIRST_N VARCHAR(40), LAST_N VARCHAR(40),"+
								"BDAY VARCHAR(8), PHONE VARCHAR(10), EMAIL VARCHAR(200))";

	
	public boolean tempTableCreate(){
		String sqlStatment = "CREATE TABLE table_authentication"+
				" (UNAME VARCHAR(40), PASSWD VARCHAR(40))";
		String sqlStatment2 = "CREATE TABLE table_forums"+
				" (UNAME VARCHAR(80) NOT NULL PRIMARY KEY,"
				+ "FIRST_N VARCHAR(40), LAST_N VARCHAR(40),"+
				"BDAY VARCHAR(8), PHONE VARCHAR(10), EMAIL VARCHAR(200))";
		try{
			sta.executeUpdate(sqlStatment);
			sta.executeUpdate(sqlStatment2);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
*/
}
