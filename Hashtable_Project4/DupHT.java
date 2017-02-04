
/* Using
 * java version "1.8.0_45"
 * Java(TM) SE Runtime Environment (build 1.8.0_45-b14)
 * Java HotSpot(TM) Client VM (build 25.45-b02, mixed mode) 
 */
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;

@SuppressWarnings("unused")
public class DupHT
{
public static void main(String args[])
{
   try 
   {
      Scanner sc = new Scanner(new File("ph.txt"));
      
     // count of hash table entries
     int count = 5003;
     MyHashTable hashList = new MyHashTable(count);
     
     long startTime = System.currentTimeMillis();

	 while (sc.hasNext())
	 {
		String ph = sc.nextLine();
		if(hashList.Contains(ph))
	    {
	    	System.out.println(ph + " is a duplicate entry");
	    }
	    hashList.Insert(ph);
      }
	 
	 
	 long endTime = System.currentTimeMillis();
	 
	 // Print the running time of the code
	 System.out.println("That took " + (endTime - startTime) + " milliseconds");
	 
	 sc.close();
	 
   }
   catch (Exception e)
   {
       System.out.println("Exception " + e);
	  System.exit(1);
   }
}
}
