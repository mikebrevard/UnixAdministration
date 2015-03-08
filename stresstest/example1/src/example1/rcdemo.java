package example1;

public class rcdemo {
	
	/*
	 * ARG0 = # of requests each thread submits
	 * ARG1 = # of threads
	 * ARG2 = # reads
	 * ARG3 = # writes
	 * ARG4 = # updates
	 * ARG5 = file name
	 * ARG6 = time (seconds) firefox waits till it closes browser
	 */
	
	public static void main(String[] args) {
		
		//number of threads to create
		int num_threads = Integer.parseInt(args[1]), counter = 0;
		myThread[] mytd_array = new myThread[num_threads]; 
		
		
		System.out.println("Creating threads...");
		//Create N threads store in array
		while(counter < num_threads){
			//requests, filename, read, write, update, thread id
			myThread mytd = new myThread(args[0], args[5],Integer.parseInt(args[2]),
					Integer.parseInt(args[3]), Integer.parseInt(args[4]), counter, Long.parseLong(args[6]));
			mytd_array[counter] = mytd;
			counter++;
		}
		
		counter = 0;
		System.out.println("Running threads...");
		//Run all N threads
		while(counter < num_threads){
			mytd_array[counter].start();
			counter++;
		}

		System.out.println("All threads have been ran!");
	}

}
