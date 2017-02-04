/**
 * A class that contains several sorting routines,
 * implemented as static methods.
 * Arrays are rearranged with smallest item first,
 * using compareTo.
 * @author Mark Allen Weiss
 * Implemented bucketSort function @author Prateek
 */
public final class Sort
{
    /**
     * Simple insertion sort.
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void insertionSort( AnyType [ ] a )
    {
        int j;

        for( int p = 1; p < a.length; p++ )
        {
            AnyType tmp = a[ p ];
            for( j = p; j > 0 && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
                a[ j ] = a[ j - 1 ];
            a[ j ] = tmp;
        }
    }

    /**
     * Quicksort algorithm.
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort( AnyType [ ] a )
    {
        quicksort( a, 0, a.length - 1 );
    }

    private static final int CUTOFF = 3;

    /**
     * Method to swap to elements in an array.
     * @param a an array of objects.
     * @param index1 the index of the first object.
     * @param index2 the index of the second object.
     */
    public static <AnyType> void swapReferences( AnyType [ ] a, int index1, int index2 )
    {
        AnyType tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }

    /**
     * Return median of left, center, and right.
     * Order these and hide the pivot.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    AnyType median3( AnyType [ ] a, int left, int right )
    {
        int center = ( left + right ) / 2;
        if( a[ center ].compareTo( a[ left ] ) < 0 )
            swapReferences( a, left, center );
        if( a[ right ].compareTo( a[ left ] ) < 0 )
            swapReferences( a, left, right );
        if( a[ right ].compareTo( a[ center ] ) < 0 )
            swapReferences( a, center, right );

            // Place pivot at position right - 1
        swapReferences( a, center, right - 1 );
        return a[ right - 1 ];
    }

    /**
     * Internal quicksort method that makes recursive calls.
     * Uses median-of-three partitioning and a cutoff of 10.
     * @param a an array of Comparable items.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void quicksort( AnyType [ ] a, int left, int right )
    {
        if( left + CUTOFF <= right )
        {
            AnyType pivot = median3( a, left, right );

                // Begin partitioning
            int i = left, j = right - 1;
            for( ; ; )
            {
                while( a[ ++i ].compareTo( pivot ) < 0 ) { }
                while( a[ --j ].compareTo( pivot ) > 0 ) { }
                if( i < j )
                    swapReferences( a, i, j );
                else
                    break;
            }

            swapReferences( a, i, right - 1 );   // Restore pivot

            quicksort( a, left, i - 1 );    // Sort small elements
            quicksort( a, i + 1, right );   // Sort large elements
        }
        else  // Do an insertion sort on the subarray
            insertionSort( a, left, right );
    }

    /**
     * Internal insertion sort routine for subarrays
     * that is used by quicksort.
     * @param a an array of Comparable items.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void insertionSort( AnyType [ ] a, int left, int right )
    {
        for( int p = left + 1; p <= right; p++ )
        {
            AnyType tmp = a[ p ];
            int j;

            for( j = p; j > left && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
                a[ j ] = a[ j - 1 ];
            a[ j ] = tmp;
        }
    }

    /**
     * Quick selection algorithm.
     * Places the kth smallest item in a[k-1].
     * @param a an array of Comparable items.
     * @param k the desired rank (1 is minimum) in the entire array.
     */     
    public static <AnyType extends Comparable<? super AnyType>>
    void quickSelect( AnyType [ ] a, int k )
    {
        quickSelect( a, 0, a.length - 1, k );
    }

    /**
     * Internal selection method that makes recursive calls.
     * Uses median-of-three partitioning and a cutoff of 10.
     * Places the kth smallest item in a[k-1].
     * @param a an array of Comparable items.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     * @param k the desired index (1 is minimum) in the entire array.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void quickSelect( AnyType [ ] a, int left, int right, int k )
    {
        if( left + CUTOFF <= right )
        {
            AnyType pivot = median3( a, left, right );

                // Begin partitioning
            int i = left, j = right - 1;
            for( ; ; )
            {
                while( a[ ++i ].compareTo( pivot ) < 0 ) { }
                while( a[ --j ].compareTo( pivot ) > 0 ) { }
                if( i < j )
                    swapReferences( a, i, j );
                else
                    break;
            }

            swapReferences( a, i, right - 1 );   // Restore pivot

            if( k <= i )
                quickSelect( a, left, i - 1, k );
            else if( k > i + 1 )
                quickSelect( a, i + 1, right, k );
        }
        else  // Do an insertion sort on the subarray
            insertionSort( a, left, right );
    }

    /*
     *  Implementing bucket sort algorithm
    */
    private static void bucketSort( Integer [ ] a )
    {
    	Integer [ ] tempBucket = new Integer[ a.length ];
    	
    	// initializing temporary array to 0
    	for (int i=0; i<tempBucket.length; i++) 
    	{
    		tempBucket[i]=0;
        }
    	
    	// Incrementing count of the integer if it exist at in the input array 
    	for(int i = 0; i < a.length; i++)
    	{	
    		tempBucket[a[i]] += 1; 
    	}
    	
    	// Updating input array based on the count of the integer
    	int index = 0;
    	for(int i = 0; i< tempBucket.length; i++)
    	{
   		   for(int j = 0; j< tempBucket[i]; j++)
    	    {
    			a[index++] = i;
    		} 
    	}
    }
    
    private static void checkSort( Integer [ ] a )
    {
        for( int i = 0; i < a.length; i++ )
            if( a[ i ] != i )
                System.out.println( "Error at " + i );
        System.out.println( "Finished checksort" );
    }

    public static void main( String [ ] args )
    {
         
    	Integer [ ] a = new Integer[ 100 ];
        for( int i = 0; i < a.length; i++ )
            a[ i ] = i;

        Integer [ ] c = new Integer[ 1000 ];
        for( int i = 0; i < c.length; i++ )
            c[ i ] = i;
        
        Integer [ ] d = new Integer[ 2000 ];
        for( int i = 0; i < d.length; i++ )
            d[ i ] = i;
        
        Integer [ ] e = new Integer[ 5000 ];
        for( int i = 0; i < e.length; i++ )
            e[ i ] = i;
        
        Integer [ ] f = new Integer[ 10000 ];
        for( int i = 0; i < f.length; i++ )
            f[ i ] = i;
        
        Random.permute( a );
        long start = System.currentTimeMillis( );
        Sort.insertionSort( a );
        long end = System.currentTimeMillis( );
        
        System.out.println( "Timing for "+ a.length + " items " );
        System.out.println( "Insertion sort for the Selection for N = " + a.length + " takes " + 
                             ( end - start ) + "ms." );
        
        Random.permute( a );
        start = System.currentTimeMillis( );
        Sort.quicksort( a );
        end = System.currentTimeMillis( );
        System.out.println( "Quick sort for the Selection for N = " + a.length + " takes " + 
                             ( end - start ) + "ms." );
        
        Random.permute( a );
        start = System.currentTimeMillis( );
        Sort.bucketSort( a );
        end = System.currentTimeMillis( );
        checkSort( a );
        System.out.println( "Bucket sort for the Selection for N = " + a.length + " takes " + 
                             ( end - start ) + "ms." );
        
        Random.permute( c );
        start = System.currentTimeMillis( );
        Sort.insertionSort( c );
        end = System.currentTimeMillis( );
        
        System.out.println( "Timing for "+ c.length + " items "  );
        System.out.println( "Insertion sort for the Selection for N = " + c.length + " takes " + 
                             ( end - start ) + "ms." );
        
        Random.permute( c );
        start = System.currentTimeMillis( );
        Sort.quicksort( c );
        end = System.currentTimeMillis( );
        System.out.println( "Quick sort for the Selection for N = " + c.length + " takes " + 
                             ( end - start ) + "ms." );
        
        Random.permute( c );
        start = System.currentTimeMillis( );
        Sort.bucketSort( c );
        end = System.currentTimeMillis( );



        System.out.println( "Bucket sort for the Selection for N = " + c.length + " takes " + 
                             ( end - start ) + "ms." );
        
        Random.permute( d );
        start = System.currentTimeMillis( );
        Sort.insertionSort( d );
        end = System.currentTimeMillis( );
        
        System.out.println( "Timing for "+ d.length + " items "  );
        System.out.println( "Insertion sort for the Selection for N = " + d.length + " takes " + 
                             ( end - start ) + "ms." );
        
        Random.permute( d );
        start = System.currentTimeMillis( );
        Sort.quicksort( d );
        end = System.currentTimeMillis( );
        System.out.println( "Quick sort for the Selection for N = " + d.length + " takes " + 
                             ( end - start ) + "ms." );
        
        Random.permute( d );
        start = System.currentTimeMillis( );
        Sort.bucketSort( d );
        end = System.currentTimeMillis( );

        System.out.println( "Bucket sort for the Selection for N = " + d.length + " takes " + 
                             ( end - start ) + "ms." );
        
        Random.permute( e );
        start = System.currentTimeMillis( );
        Sort.insertionSort( e );
        end = System.currentTimeMillis( );
        
        System.out.println( "Timing for "+ e.length + "items "  );
        System.out.println( "Insertion sort for the Selection for N = " + e.length + " takes " + 
                             ( end - start ) + "ms." );
        
        Random.permute( e );
        start = System.currentTimeMillis( );
        Sort.quicksort( e );
        end = System.currentTimeMillis( );
        System.out.println( "Quick sort for the Selection for N = " + e.length + " takes " + 
                             ( end - start ) + "ms." );
        
        Random.permute( e );
        start = System.currentTimeMillis( );
        Sort.bucketSort( e );
        end = System.currentTimeMillis( );

        System.out.println( "Bucket sort for the Selection for N = " + e.length + " takes " + 
                             ( end - start ) + "ms." );
        
        Random.permute( f );
        start = System.currentTimeMillis( );
        Sort.insertionSort( f );
        end = System.currentTimeMillis( );
        
        System.out.println( "Timing for "+ f.length + " items "  );
        System.out.println( "Insertion sort for the Selection for N = " + f.length + " takes " + 
                             ( end - start ) + "ms." );
        
        Random.permute( f );
        start = System.currentTimeMillis( );
        Sort.quicksort( f );
        end = System.currentTimeMillis( );
        System.out.println( "Quick sort for the Selection for N = " + f.length + " takes " + 
                             ( end - start ) + "ms." );
        
        Random.permute( f );
        start = System.currentTimeMillis( );
        Sort.bucketSort( f );
        end = System.currentTimeMillis( );

        System.out.println( "Bucket sort for the Selection for N = " + f.length + " takes " + 
                             ( end - start ) + "ms." );
    }
}