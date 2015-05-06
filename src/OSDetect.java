
public class OSDetect {
	private static String OS = System.getProperty("os.name").toLowerCase();
	 
	//fully supports windows
	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}
 
	//fully supports mac
	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}
 
	//currently not being used right now
	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}
 
	//currently not being used by our project
	public static boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}
}
