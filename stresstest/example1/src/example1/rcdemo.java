package example1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class rcdemo {

	public static void main(String[] args) {

		
		int users = 0, max_users = 5, read = 14, write = 2, update = 20;
		String file = "myfile";
		
		//Loops for 5 users
		while(users < max_users){
			//TODO: Add threads to create concurrent users
			
	        // Create a new instance of the firefox unit driver
	        // Notice that the remainder of the code relies on the interface, 
	        // not the implementation.
	        WebDriver driver = new FirefoxDriver();
	        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	        
	        // And now use this to visit our page
	        driver.get("http://127.0.0.1:8087/CS183WebApplication/?read=" + read + "&write=" + write +
	        			"&update=" + update + "&file=" + file);
	   
	        //Timer to wait till data is written and finally written to file
	        long end = System.currentTimeMillis() + 5000;
	        while (System.currentTimeMillis() < end) {}
	        
	        //Exit session
	        driver.quit();
	        
        	users++;
		}
	}

}
