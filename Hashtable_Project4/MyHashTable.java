import java.util.LinkedList;


public class MyHashTable {

	// Hash table size
	private int size;
	// Hash table 
	private LinkedList<String>[] llHashArray;
	
	/*
	 * Initializing hash table
	 */
	@SuppressWarnings("unchecked")
	public MyHashTable(int count)
	{
		size = nextPrime(count);
		llHashArray = new LinkedList[size];
		for (int i = 0; i < size; i++)
		      llHashArray[i] = new LinkedList<String>();
	}
	
	/*
	 * Find the next prime number
	 * This function is to initialize size of the Hash table
	 */
	private int nextPrime( int num )
    {
		if( num % 2 == 0 )
			num++;

		for( ; !isPrime( num ); num += 2 )
            ;
        
        return num;
     }

	/*
	 * This function is to check whether a number is a prime number or not 
	 */
	private boolean isPrime( int num )
	{
		boolean flag = true;
		if( num == 2 || num == 3 )
			flag =  true;

		if( num == 1 || num % 2 == 0 )
			flag = false;

		for( int i = 3; i * i <= num; i += 2 )
			if( num % i == 0 )
				flag = false;

		return flag;
     }
	
	/*
	 * This function is to insert key into the hash table
	 */
	public void Insert(String key)
	{
	   int index = hashFunc(key);
	   LinkedList<String> LL = llHashArray[index]; 
	   // Adding the key to hash table
	   LL.add(key);
	}
	
	/*
	 * This function is called before inserting to
	 * check if the key already present in the Hash table or not
	 */
	public boolean Contains(String key)
	{
	   int index = hashFunc(key);
	   boolean flag = false;
	   flag = (llHashArray[index].contains(key))?true:false;
	   return flag;
	}
	
	/*
	 * Hash function, returns position of the key in the hash table
	 */
	private int hashFunc(String key) 
	{
		int val = key.hashCode();
        for(int i=0; i < key.length(); i++)
        {
        	val %= 37 * val + key.charAt(i);
        }
        val %= size;
        if( val < 0 )
            val += size;

        return val;
	}
}
