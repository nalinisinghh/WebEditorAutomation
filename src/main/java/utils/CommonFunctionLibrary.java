package utils;

import org.openqa.selenium.By;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;





public class CommonFunctionLibrary {
  
	
	public static String LoggerFolderPath()
	{
		Date today = Calendar.getInstance().getTime();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh");
		 
		return "D:\\WebEditorAutomation\\WebEditor\\src\\main\\resources\\LogFolder\\logger"+formatter.format(today)+".log";
	}
	public static void Log(String message) 
	{
		
		 DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(LoggerFolderPath(), true)));
		    out.println(dateTimeInstance.format(Calendar.getInstance().getTime())+" :- "+message);
		    out.close();
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}

	
}

