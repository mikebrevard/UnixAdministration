package example1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class myThread extends Thread {
	
	public String MAXUSERS, FILENAME;
	public int TID;
	
	public myThread(String max_U, String filename, int threadID){
		MAXUSERS = max_U;
		FILENAME = filename;
		TID = threadID;
	}
	
	public  void runTest(){
		int users = 0, max_users = Integer.parseInt(MAXUSERS), read = 14, write = 2, update = 20;
		String file = FILENAME;
		String url = "http://127.0.0.1:8087/CS183WebApplication/?read=" + read + "&write=" + write +
    			"&update=" + update + "&file=" + file;

		System.out.println("Thread ID: " + TID + " " + url);
		
		//Loops for 5 users
		while(users < max_users){
			//TODO: Add threads to create concurrent users
			
	        // Create a new instance of the firefox unit driver
	        // Notice that the remainder of the code relies on the interface, 
	        // not the implementation.
	        WebDriver driver = new FirefoxDriver();
	        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	        
	        // And now use this to visit our page
	        driver.get(url);
	   
	        //Timer to wait till data is written and finally written to file
	        long end = System.currentTimeMillis() + 5000;
	        while (System.currentTimeMillis() < end) {}
	        
	        //Exit session
	        driver.quit();
	        
        	users++;
		}
	}
	
	
	public void run(){
		runTest();
	}
}
