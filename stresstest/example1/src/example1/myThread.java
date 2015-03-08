package example1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class myThread extends Thread {
	
	public String REQUESTS, FILENAME;
	public int TID, R , W, U;
	public long SECONDS;
	
	public myThread(String request, String filename,int read, int write, int update, int threadID, long seconds){
		REQUESTS = request;
		FILENAME = filename;
		TID = threadID;
		R = read;
		W = write;
		U = update;
		SECONDS = seconds;
	}
	
	public  void runTest(){
		int request = 0, max_requests = Integer.parseInt(REQUESTS), read = R, write = W, update = U;
		String file = FILENAME;
		String url = "http://127.0.0.1:8087/CS183WebApplication/?read=" + read + "&write=" + write +
    			"&update=" + update + "&file=" + file;
		long start_time = System.currentTimeMillis();
		long finish_time = 0;
		//Loops for 5 users
		while(request < max_requests){
			//TODO: Add threads to create concurrent users
			
	        // Create a new instance of the firefox unit driver
	        // Notice that the remainder of the code relies on the interface, 
	        // not the implementation.
	        WebDriver driver = new FirefoxDriver();
	        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	        
	        // And now use this to visit our page
	        driver.get(url);
	   
	        //Timer to wait till data is written and finally written to file
	        long end = System.currentTimeMillis() + SECONDS;
	        while (System.currentTimeMillis() < end) {}
	        
	        //Exit session
	        driver.quit();
	        
	        request++;
		}
		
		finish_time = System.currentTimeMillis();
		long duration = finish_time - start_time;
		duration = duration / 1000; //convert to seconds
		
		System.out.println("Thread Complete, Thread ID: " + TID + " " + url +" Duration: " + duration + " seconds");

	}
	
	
	public void run(){
		runTest();
	}
}
