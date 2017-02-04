public class LinkedList
{
   private Node head;
   private Node tail;
   int size;
   
   /*
    * Method is to add to the element to the front of the list.
    *  This method is called from addNum method. 
    */
   private void addToFront(int x)
   {
      head = new Node(x, head);
      if (tail == null)
        tail = head;
      size++;
   }

   /*
    * Method is to add to the element to the tail of the list.
    *  This method is called from addNum method. 
    */
   private void addToTail(int x)
   {
      if (tail == null)
         addToFront(x);
      else
      {
         tail.next = new Node(x, null);
         tail = tail.next;
         size++;
      }
   }
   
   /*
    * Method is to add the element at appropriate position. The list is
    * maintained in the ascending order. 
    */
   void addNum(int x)
	{
	   if(head == null)
	   {
		   addToFront(x);
	   }
	   else
	   {
			Node nextNode = new Node();
			Node prevNode = new Node();
			nextNode = head;
			prevNode = head;
			if( x <= head.data)
			{
				addToFront(x);
			}
			else if( x >= tail.data)
			{
				addToTail(x);
			}
			else
			{
				for( int i = 0; i < size; i++)
				{
					if( x >= nextNode.data )
					{
						prevNode = nextNode;
						nextNode = nextNode.next;
					}
					else 
					{
						Node newNode = new Node(x, nextNode);
						prevNode.next = newNode;
						size++;
						break;
					}
				}
			}
	   }
	}
   
   /*
    * Method removes the element inserted in the list 
    */
   public void cancel(int x)
   {
	   Node nextNode = new Node();
	   Node prevNode = new Node();
	   nextNode = head;
	   prevNode = head;
	   
	   // Check if the list is empty
	   if(head == null)  
       {
		   System.out.println("List is empty");
	   }
	   else 
		   if( head.data == x )
		   {
			   head = head.next;
			   size--;
		   }
		   else
		   {	   
			   // Search for the match 
			   for( int i = 0; i < size; i++)
			   {
				   if(nextNode.data == x)
				   {
					  size--;
					  if(tail.data == x)
					  {
						  tail = prevNode;
						  tail.next = null;
					  }
					  else
					  {
						  prevNode.next = nextNode.next;
						  
					  }  
					  break;
				   }
				   else
				   {
					   prevNode = nextNode;
					   nextNode = nextNode.next;
				   }
			   }
		   }
   }
   
   /*
    * Method returns true if the number is found in the list else returns false 
    */
   public boolean ifNumFound(int x)
   {
	   // Search a number in the list
	   Node nextNode = new Node();
	   nextNode = head;
	   boolean flag = false;
	   // Check if the number is tail number.
	   while (nextNode != null)
	   {
		   if(nextNode.data == x)
		   {
			   flag = true;
			   break;
		   }
		   else
		   {
			   nextNode = nextNode.next;
		   }
	   }
	   return flag;
   }

  /*
   * This method returns the smallest element in the list
   */
  public int getTheSmallest()
  {
	 int num = -1;
	 // Since the list is all ready sorted in ascending order.
	 if(head != null)
		 num = head.data;
	
	 return num;
  }

  /*
   * Method to remove the smallest element in the list 
   */
  public void remove()
  {
     if (head != null)
     {
    	// Since the list is in the sorted order removing the first element of the list
        head = head.next;
        size--;
     } 
   }
  
  /* 
   * This method returns the size of the list. 
   */
  public int getSize(){
	   return size;
  }

  /*
   * Method to print a linked list elements in a [ a, b, c ] format 
   */
   public String toString()
   {
		StringBuilder sb = new StringBuilder("[ ");
		  
		Node tempNode = head;      
		while (tempNode != null)
		{
		   if(tempNode == tail)
		   {
			   sb.append(tempNode.data + " ");
		   }
		   else
		   {
			   sb.append(tempNode.data + ", ");
		   }
		   tempNode = tempNode.next;
		}
		 
		sb.append("]");
		return new String(sb);
	}

   /*
    * A private inner class for the linked list nodes
    */
   private static class Node
   {
		int data;
		Node next;
		
		Node(int d, Node n)
		{
		   data = d;
		   next = n;
		}

		// Used for creating temporary nodes
		public Node() {		}
   }
}