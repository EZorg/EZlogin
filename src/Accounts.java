import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
/**
 * All users who are curently registered
 * with WebScrap are accessed through this api
 * @author beshoi
 *
 */

public class Accounts {
	private LinkedList<String> users;
	
	public Accounts(){
		users = new LinkedList<String>();
	}
	
	//returns true if user is successfully added
	public boolean addUser(String n){
		n=n.toLowerCase();
		if(users.contains(n)) return false;
		sortedInsert(users, n);
		return true;
	}
	
	//returns true if user is successfully deleted
	public boolean deleteUser(String n){
		return users.remove(n);
	}
	
	//returns the list of users currently loaded in our application
	//public LinkedList<String> getAllUsers(){
	//	return users;
	//}
	
	//returns true if user exists false otherwise
	public boolean doesUserExist(String n){
		return users.contains(n);
	}
	
	//exports our linked list to a file
	public boolean writeToFile(){
		Path filePath = Paths.get("Accounts"+ File.separator + "allUsers.bsb");
		Charset charset = Charset.forName("US-ASCII");
		
		try{
			BufferedWriter writer = Files.newBufferedWriter(filePath, charset);
			
			writer.write("NumberOfUsers:"+ users.size());
			writer.newLine();
			while(users.size() > 0){
				writer.write(users.removeFirst());
				writer.newLine();
			}
			writer.close();
			System.out.println("File Writen Successfuly");
			return true;
		}
		catch(Exception e){
			System.out.println("Error Writing to the file '" + filePath + "'");
			return false;
		}
	}
	
	//import file into our linked list
	public boolean readFromFile(){
		users.clear();//makes sure the list is empty before reading from file
		Path filePath = Paths.get("Accounts" + File.separator + "allUsers.bsb");
		try{
			InputStream in = Files.newInputStream(filePath);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			String line = reader.readLine(); //geting the number of users
			while((line = reader.readLine()) != null){
				addUser(line);//inserting the users back
			}
			System.out.println("File Read Successfuly");
			return true;
		}
		catch(Exception e){
			System.out.println("Failed to read the file '"+filePath+"'");
			return false;
		}
		finally{
			Collections.sort(users);
		}
	}
	
	/**
	 * This is our hidden algorithm designed for
	 * efficiency in inserting users by allowing them to be
	 * alphabtecially ordered
	 * @param list
	 * @param val
	 */
	private static void sortedInsert(LinkedList<String> list, String val){
		if(list.size()==0) list.addFirst(val);
		else binaryInsert(list, 0, list.size()-1, val);	
	}
	private static void binaryInsert(LinkedList<String> list, int begin, int end, String val){
		     int mid = end + begin / 2;
		     if (list.get(mid).equals(val)) {
		       list.add(mid,val);
		       return;
		     } 
		     else if (list.get(mid).compareTo(val) < 0) {
		       begin = mid + 1; // its in the upper
		       if (begin > end){
		         list.add(mid+1,val);
		         return;
		       }
		       binaryInsert(list,begin,end,val);
		     } 
		     else {
		       end = mid - 1; // its in the lower
		       if (begin > end){
		         list.add(mid,val);
		         return;
		       }
		       binaryInsert(list,begin,end,val);
		     }	
	}
}

