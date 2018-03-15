package threadsyncing;

class PrintG {
	private static int G;
	public int V;
	String[] args = lab5.getArgs();
	   public void printCount(){
		   
		    try {
		    	
		    	String[] threadSet = args[3].split("=");
		         for(G = Integer.parseInt(threadSet[1]); G > 0; G--) {
		            System.out.println("G   ---   "  + G );
		         }
		     } catch (Exception e) {
		         System.out.println("Thread  interrupted.");
		     }
		    System.out.println(G);
		    if (G!=0) {
		    	V++;
		    	System.out.println("Fail!");
		    	System.out.println("Number of violations: " + V);
		    	
		    	//System.out.println("Number of violations: "+G);
		    } else {
		    	System.out.println("OK!");
		    }
		   }
	

		} 

	class ThreadWithoutProt extends Thread {
	   private Thread t;
	   private String threadName;
	   PrintG  PD;

	   ThreadWithoutProt( String name,  PrintG pd){
	       threadName = name;
	        PD = pd;
	   }
	   public void run() {
	     PD.printCount();
	     System.out.println("Thread " +  threadName + " exiting.");
	   }

	   public void start ()
	   {
	      System.out.println("Starting " +  threadName );
	      if (t == null)
	      {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }

	}
	
	class ThreadProtection extends Thread {
		   private Thread t;
		   private String threadName;
		   PrintG  PD;

		
		ThreadProtection( String name,  PrintG pd){
		       threadName = name;
		       PD = pd;
		   }
		   public void run() {
		     synchronized(PD) {
		        PD.printCount();
		     }
		     System.out.println("Thread " +  threadName + " exiting.");
		   }

		   public void start ()
		   {
		      System.out.println("Starting " +  threadName );
		      if (t == null)
		      {
		         t = new Thread (this, threadName);
		         t.start ();
		      }
		   }

		}

	public class lab5 {
		private static String[] savedArgs;
		public static String[] getArgs() {
			return savedArgs;
		}
	   public static void main(String args[]) {
		   savedArgs = args;
		   
	      
	      if(args[0].equals("protectjava")&&args[1].equals("--without-protection")) {
	    	  PrintG PD = new PrintG();
	    	  
	    	  String[] threadSet = args[2].split("=");
	    	  for (int i = 0; i<Integer.parseInt(threadSet[1]); i++) {
	      ThreadWithoutProt T1 = new ThreadWithoutProt( "Thread - " + i, PD );
	      //ThreadWithoutProt T2 = new ThreadWithoutProt( "Thread - 2 ", PD );
	      T1.start();
	      //T2.start();
	      try {
		         T1.join();
		     //    T2.join();
		      } catch( Exception e) {
		         System.out.println("Interrupted");
		      }
	    	  }
		   
	      } else if(args[0].equals("protectjava") && args[1].equals("--with-protection")) {
	    	//  String val = args[2];
	    	 // int temp = Integer.parseInt(val);
	    	  PrintG PD = new PrintG();
	    	 // for (int i = 0; i<temp;i++) {
	    		  
	    	  //}
	    	  String[] threadSet = args[2].split("=");
	    	  for (int j = 0; j<Integer.parseInt(threadSet[1]); j++) {
	    	  ThreadProtection T1 = new ThreadProtection( "Thread - " + j, PD );
	    	  //ThreadProtection T2 = new ThreadProtection( "Thread - 2 ", PD );
	    	  
		      T1.start();
		      //T2.start();
		      try {
			         T1.join();
			         //T2.join();
			      } catch( Exception e) {
			         System.out.println("Interrupted");
			      }
	    	  }
	    	  System.out.println(PD.V);
	    	  
			   } else {
	    	  System.out.println("not enough arguments");
	      }
	      }
	
	      

	      // wait for threads to end
	     
	}
