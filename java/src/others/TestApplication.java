package others;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestApplication {
	
	private static final String os = System.getProperty("os.name").toLowerCase();
	
	public static boolean isWindows(){
		return os.indexOf("win")>-1;
	}
	public static boolean isMac(){
		return os.indexOf("mac")>-1;
	}
	public static boolean isLinux(){
		return os.indexOf("nux")>-1 || os.indexOf("nix")>-1;
	}
	public static Integer getNumOfCores(){
		Integer num = -1;
		try {
			String cmd = "ipconfig",line;
			if(isWindows())
				cmd = "cmd /C WMIC CPU Get /Format:List";
			else if(isMac())
				cmd = "sysctl -n machdep.cpu.core_count";
			else if(isLinux())
				cmd = "lscpu";
			else System.out.println("What OS is this?");
			Process process = Runtime.getRuntime().exec(cmd);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while((line=reader.readLine())!=null){
				if(line.toLowerCase().indexOf("numberofcores")>-1){
					num = Integer.parseInt(line.split("=")[1]);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return num;
	}
	public void runCommand(String cmd){
		try {
			Runtime.getRuntime().exec(cmd);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws Exception{
		
		TestApplication app = new TestApplication();

		app.runCommand("cmd.exe /c mkdir C:\\work\\new_folder");
		app.runCommand("cmd.exe /c echo Number of CPU cores available is " + getNumOfCores() + " > C:\\work\\new_folder\\cores.txt");
		app.runCommand("cmd.exe /c start C:\\work\\new_folder");
	}
	
}
