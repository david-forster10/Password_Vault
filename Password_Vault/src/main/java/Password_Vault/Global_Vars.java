package Password_Vault;

public class Global_Vars 
{
	public static String getWorkingDirectory()
	{
		String workingDirectory; //variable for where everything is stored
		String OS = (System.getProperty("os.name")).toUpperCase(); //capitalises OS version
		
		if (OS.contains("WIN")) //if windows
		{
			workingDirectory = System.getenv("AppData"); //workingdirectory is set to appdata within computer
		}
		else //else if UNIX Based
		{
			workingDirectory = System.getProperty("user.home"); //get user's home
			workingDirectory += "\\Library\\Application Support"; //and reference to the Application files
		}
		
		workingDirectory = workingDirectory.replaceAll("\\\\", "\\\\\\\\"); //replace all \ with \\ so that the file path can be used without errors
		
		return workingDirectory +"\\Password_Vault";
	}
}
