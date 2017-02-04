import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintStream;
public class Project2 {
	
	public static LinkedList list1;
    public static LinkedList list2;
    public static LinkedList list3;
	
    /*
     * Initializing linked list objects.
     */
	Project2()
	{
		Project2.list1 = new LinkedList();
	    Project2.list2 = new LinkedList();
	    Project2.list3 = new LinkedList();
	}
   
  /*
  * This function adds the number in the smallest list
  */
   private void add(int x){
	   
	   // Calling the addNum function of the smallest list
	   if(list1.getSize() <= list2.getSize() &&  list1.getSize() <= list3.getSize() )
	   {
		   list1.addNum(x);
		   System.out.println("Added " + x + " to queue L1");
	   }
	   else 
		   if(list2.getSize() <= list3.getSize() &&  list2.getSize() <= list1.getSize())
		   {
			   list2.addNum(x);
			   System.out.println("Added " + x + " to queue L2");
		   }
		   else
		   {
			   list3.addNum(x);
			   System.out.println("Added " + x + " to queue L3");
		   }
   }
   
   /*
    * This function removes the number from the list if it is found
    * else it throws an error message
    */
   private void cancel(int x)
   {
	   if( list1.ifNumFound(x))
	   {
		   list1.cancel(x);
		   System.out.println("Deleted " + x);
		   
	   }
	   else
		   if (list2.ifNumFound(x))
		   {
		       list2.cancel(x);
		       System.out.println("Deleted " + x);
		   }
		   else   
			   if(list3.ifNumFound(x))
			   {
				   list3.cancel(x);
				   System.out.println("Deleted " + x);
			   }
			   else
			   {
				   System.out.println("Number not found in any of the lists");
			   }
   }
   
   /*
    * This function print the elements of the lists
    */
   private void print()
   {
       System.out.println(list1);
       System.out.println(list2);
       System.out.println(list3); 
   }
   
   /*
    * This function removes the smallest element of the list from the list 
    */
   private void remove()
   {
       int num1 = list1.getTheSmallest();
       int num2 = list2.getTheSmallest();
       int num3 = list3.getTheSmallest();
       
       // Need to make it nested, check size of each list before comparing values.
       if(list1.getSize() > 0 && list2.getSize() > 0 && list3.getSize() > 0)
       {
	       if( num1 <= num2  && num1 <= num3 )
	       {
	    	   list1.remove();
	    	   System.out.println("Removed " + num1);
	       }
	       else
	    	   if( num2 <= num1 && num2 <= num3  )
	           {
	        	   list2.remove();
	        	   System.out.println("Removed " + num2);
	           }
	    	   else
	    		   if( num3 <= num1 && num3 <= num2)
	    	       {
	    	    	   list3.remove();
	    	    	   System.out.println("Removed " + num3);
	    	       }	    		  
       }
       else
    	   if(list1.getSize() > 0 && list2.getSize() > 0)
    	   {
    	       if( num1 <= num2 && list1.getSize() > 0 )
    	       {
    	    	   list1.remove();
    	    	   System.out.println("Removed " + num1);
    	       }
    	       else
    	    	   if( num2 <= num1 && list3.getSize() > 0 )
    	    	   {
    	    	   	   list2.remove();
    	    	   	   System.out.println("Removed " + num2);
    	    	   }
    	   }
    	   else 
    		   if(list1.getSize() > 0 && list3.getSize() > 0 )
    		   {
        	       if( num1 <= num3 && list1.getSize() > 0 )
        	       {
        	    	   list1.remove();
        	    	   System.out.println("Removed " + num1);
        	       }
        	       else
        	    	   if( num3 <= num1 && list2.getSize() > 0 )
        	           {
        	        	   list3.remove();
        	        	   System.out.println("Removed " + num3);
        	           }
    		   }
    		   else 
    			   if(list2.getSize() > 0 && list3.getSize() > 0)
	    		   {
	    			   if( num2 <= num3 && list2.getSize() > 0 )
	    	           {
	    	        	   list2.remove();
	    	        	   System.out.println("Removed " + num2);
	    	           }
	    	    	   else
	    	    		   if( num3 <= num2 && list3.getSize() > 0 )
	    	    	       {
	    	    	    	   list3.remove();
	    	    	    	   System.out.println("Removed " + num3);
	    	    	       }
	    		   }
	    		   else 
	    			   if(list1.getSize() > 0)
	    			   {
	    				   list1.remove();
	        	    	   System.out.println("Removed " + num1);
	    			   }
	    			   else
	    				   if(list2.getSize() > 0)
	        			   {
	        				   list2.remove();
	            	    	   System.out.println("Removed " + num2);
	        			   }
	    				   else
	    					   if(list3.getSize() > 0)
	            			   {
	            				   list3.remove();
	                	    	   System.out.println("Removed " + num3);
	            			   }
	    					   else
	    					   {
	    						   System.out.println("Remove called on empty queues");
	    					   }
   }
   
   /*
    * Main method of project2
    */
	   public static void main(String args[]) throws IOException
	   {
	    
		   Project2 project = new Project2();
		   
		   // To print the out in the output file
		   File file = new File(System.getProperty("user.dir"),"output.txt");
		   FileOutputStream fos = new FileOutputStream(file);
		   PrintStream ps = new PrintStream(fos);
		   System.setOut(ps);
		   
	      // The name of the file to open.
	      File starting = new File(System.getProperty("user.dir"));
	      File fileToBeRead = new File(starting,"input.txt");

	        // This will reference one line at a time
	        String line = null;

	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(fileToBeRead);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);

	            while((line = bufferedReader.readLine()) != null) {
	            	String temp = line;
	            	
	                if(temp.contains("A"))
	                {
	                	String[] parts = temp.split(" ");
		            	int x = Integer.parseInt(parts[1]);
	                	// Call add
	                	project.add(x);
	                    
	                }
	                else
	                	if(temp.contains("P"))
	                	{
	                		project.print();
	                	}
	                	else
	                		if(temp.contains("R"))
	                		{
	                		    project.remove();
	                		}
	                		else
	                			if(temp.contains("C"))
	                			{
	                				String[] parts = temp.split(" ");
	        		            	
	        		            	int x = Integer.parseInt(parts[1]);
	                				project.cancel(x);
	                			}
	                	
	            }  
	            // Always close files.
	            bufferedReader.close(); 
	            fos.close();
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                		fileToBeRead + "'");                
	        }
	        
	   }
}