# About EZLogin
EZLogin is a java based application that uses an embeded derby database for account storage.
EZLogin offers automated login to 23 different online providers using selenium api. +
Password Storage and Online Login is EZLogins goal. Creators of EZLogin are Beshoi G, Sam G, and Ben D. For CentralAPI and all backend code was done by Beshoi G while for our front end user interaction was done by Ben D. and Sam G. 
<br><br>

#Running EZLogin
-Windows Users <br>
To run EZLogin on Windows Based Machines all that is needed is that you extract the EZLogin.zip file on your computer double click EZLogin.jar and create an account and your good to go. EZLogins database is stored locally so you can be sure that non of your data is being transmitted to any external locations. <br>
-Mac Users <br>
To run EZLogin on Mac based machines you must first have firefox preinstalled after which you can extract our EZLogin.zip file double click EZLogin.jar and your good to go.<br>
-Linux Users <br>
For Linux users you must first have firefox preinstalled after which you can extract our EZLogin.zip file. In order for EZLogin to run properly on linux machines you must run the EZLogin.jar file as administrator otherwise you will not be able to login to the application.<br>To run EZLogin.jar as administrator in Ubuntu type the following in a terminal<br>
sudo gnome-open EZLogin.jar &
<br><br>               

#Development In EZLogin
Inside the src folder you will find all of EZLogins source code for a wide understanding of our application you should start by looking through CentralAPI. All backend code is entracting with the gui exclusively through CentralAPI. Feel free to modify any and all code in this application. For compilation it is important to have the browser folder and the Accounts folder within the same directory as the .class files otherwise it will not be able to access the database or the firefox binaries. the browser and Accounts folder are located within our EZLogin.zip. Enjoy!                                                                                                                                                                                          
#To Do List
-Apply Encyption to the passwords used for login and reversable encryption for the password vaults of users
-Apply Updates for google login to adhere to googles new sign in page
-Apply new query format for database to prevent sql injection
