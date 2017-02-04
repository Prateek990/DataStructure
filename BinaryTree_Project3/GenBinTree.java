
/*
 * General Binary Tree class
 */
public class GenBinTree <R extends Comparable<R>>{

	Node root;
	// Node class to store data
	private class Node{
		R data;
		Node leftNode;
		Node rightNode;
		/*
		 * Class constructor
		 */
		Node(R d)
		{
			data = d;
		}
	}
	
	/*
	 * Add function to creates the root of the binary tree
	 */
	public void add(R data)
	{
		// If root is null create a new node
		if(root == null)
		{
			root = new Node(data);
		}
	}
	
	/*
	 * Overloaded add function to add the nodes of the binary tree
	 */
	public void add(String str, R data)
	{
		// Check for root, before calling insert
		if(root == null)
		{
			System.out.println("Root is missing!!!");
			return;
		}
		
		insert(str,data);
	}
	
	/*
	 * Insert the data at appropriate level in the binary tree 
	 */
	private void insert(String str, R data)
	{
		// Convert the string to the character array
		char[] cArray = str.toCharArray();
		Node temp = root;
	    for(int i = 0; i<cArray.length; i++ )
	    {
	    	// Check whether to add node in the left or on the right of the parent node
	    	if((cArray[i] == 'l' || cArray[i] == 'L') )
	    	{
	    		if(temp.leftNode == null && cArray.length - (i+1) == 0)
	    		{
	    			temp.leftNode = new Node(data);
                    break;
	    		}
	    		else
	    	    	if(temp.leftNode != null)
	    	    	{
	    	    		temp = temp.leftNode;	
	    	    	}
	    	    	 else
	    	    	{
	    	    		System.out.println("Parent is missing for " + data);
		    	    	break;
	    	    	}
	    	}
	    	else
	    		if(cArray[i] == 'r' || cArray[i] == 'R')
	    		{
	    			if(temp.rightNode == null && cArray.length - (i+1) == 0)
		    		{
		    			temp.rightNode = new Node(data);
	                    break;
		    		}
	    			else
	    				if(temp.rightNode != null)
	    				{
	    					temp = temp.rightNode;
	    				}
	    				else
	    				{
	    					System.out.println("Parent is missing for " + data);
	    					break;
	    				}
	    		}
	    }
	}

    /*
     * 	This is to print the tree node in order traversal
     */
    public void print()
    {
    	inOrderPrint(root);
    }
    
    /*
     * Private function to print the nodes
     */
    private void inOrderPrint(Node n)
    {
    	if( n != null )
        {
    		inOrderPrint( n.leftNode );
            System.out.println( n.data );
            inOrderPrint( n.rightNode );
        }
    }   
    
    /*
     * Returns root of the binary tree
     */
	public Node getRoot()
	{
	   return root;
	}
	
	/*
	 * Search the node of the tree with passed value
	 */
	public boolean find(R searchStr)
	{
		return search(root, searchStr);
	}
	
	/* 
	 * Return true if the node is found with the passed data
	 */
	private boolean search(Node n, R data)
	{
		boolean flag = false;
		if(n == null)
		{
		   // Do nothing	
		}
		else
			if(data.equals(n.data))
			{
				System.out.println("Match found");
				flag = true;
			}
			else
				if(n.leftNode != null && search(n.leftNode, data))
				{
					flag = true;
				}
				else
					if(n.rightNode != null && search(n.rightNode, data))
					{
						flag = true;
					}
		return flag;
	}
	
	/*
	 * Remove the node of the tree 
	 */
	public void remove(R str)
	{
		delete(root, str);
	}
	
	/*
	 * Deletes the node of the tree 
	 */
	private boolean delete(Node n, R str)
	{
		boolean flag = false;
		if(n == null)
		{
		   // Do nothing	
		}
		else
			if(str.equals(n.data) && (n.leftNode == null && n.rightNode == null))
			{
				flag = true;
			}
			else
				if(n.leftNode != null && delete(n.leftNode, str))
				{
					System.out.println("Successfully removed " + str);
					n.leftNode = null;
					flag = false;
				}
				else
					if(n.rightNode != null && delete(n.rightNode, str))
					{
						System.out.println("Successfully removed " + str);
						n.rightNode = null;
						flag = false;
					}
		return flag;
	}
	
	/*
	 * Swap child node of the given data
	 */
	public void swap(R data)
	{
		swapNodes(root, data);
	}
	
	/*
	 * Private function to swap the node of given data 
	 */
	private void swapNodes(Node n, R data)
	{
		Node tempNode = searchNode(n, data);
		
		// Check if the node exists or not
		if(tempNode == null)
		{
			System.out.println("Node with the passed data doesn't exist in the tree");
		}
		else
		{
			// If the node itself is a leave the print exception
			if(tempNode.leftNode == null && tempNode.rightNode == null)
			{
			    System.out.println("There are no children of the node to swap");
			}
			else
			{
			    // Swap the children of the given data
				Node temp;
				temp = tempNode.leftNode;
				tempNode.leftNode = tempNode.rightNode;
				tempNode.rightNode = temp;	
				System.out.println("The nodes are swapped");
			}
		}
	}
	

	/*
	 *  Function to create a mirror image of the binary tree 
	 */
	public void mirrorOfBt()
    {
		mirror(root);	    
    }
	  
	/*
	 *  Internal function to create a mirror image of the binary tree 
     */
	private Node mirror( Node n)
	{
		if (n == null)
		{
			return n;
		}
		else
		{
			// Swap the children of the nodes 
			Node temp = n.rightNode;
			n.rightNode = n.leftNode;
			n.leftNode = temp;
			// Recursive call on the swapped nodes
			mirror(n.rightNode);
			mirror(n.leftNode);
		}
		return n;
	  }
	  
	/*
	 * Returns the parent node of the given data
	 */
	private Node getParentNode(Node n, R data)
	{
		Node parentNode = null;
		if (n != null)
		{
			/*
			 * Traversing through the left node and then right node
			 * to find the parent node of the node the given data
			 */
			if (n.leftNode != null)
			{
				if(data.equals(n.leftNode.data))
				{
					return n;
				}
			}
			if (n.rightNode != null)
			{
				if(data.equals(n.rightNode.data))
				{
					return n;
				}
			}
			parentNode = getParentNode(n.leftNode, data);
			if(parentNode == null)
			{
				parentNode = getParentNode(n.rightNode, data);
			}
		}
		return parentNode;
	}
	  
	/*
	 * Returns the node of the given data
	 */
	private Node searchNode(Node n, R data)
	{
		Node foundNode = null;
		if(n == null)
			return null;
		else
			if(n.data.equals(data))
				foundNode = n;
			else
			{
				/*
				 * Traversing through the left node and then right node
				 * to find the node with the given data
				 */
				if(n.leftNode != null)
				{
					foundNode = searchNode(n.leftNode, data);
					if(foundNode != null)
					{
						return foundNode;
					}
				}
				if(n.rightNode != null)
				{
					foundNode = searchNode(n.rightNode, data);
					if(foundNode != null)
					{
						return foundNode;
					}
				}
			}
		return foundNode;
	}
	  
	/*
	 * Rotates the binary tree on the left at given node
	 */
	private void RotateOnLeft(Node n, R data)
	{
		Node rotateNode = searchNode(n, data);
		// When the to be rotated node is a children of the root node
		if(rotateNode == root.rightNode)
		{
			Node temp;
			temp = rotateNode.leftNode;
			root.rightNode = temp;
			rotateNode.leftNode = root;
			root = rotateNode;
		}
		else
		{
			/*The following if case handles left rotation of node 80
			 *           50                        50
                        /   \                     /   \
                      40     100                40     100
                      /      /  \        -->    /     /  \
                     45     80   150           45   60    150
                     /        \                /    /       
                    35         60             35   80      
			 */
			Node parentNode = getParentNode(root, data);
			if(rotateNode.rightNode != null && parentNode.leftNode == rotateNode)
			{
				Node tempNode = rotateNode.rightNode;
				rotateNode.rightNode = null;
				tempNode.leftNode = rotateNode;
				parentNode.leftNode = tempNode; 
			}
			else
				/*The following if case handles right rotation of node 120
				 *           50                    50
	                        /   \                 /  \
	                      40     100     -->    40    120
	                      /       \            /     /   \
	                    45        120        45    100   150
	                    /           \        /              
	                  35            150     35              
				 */
				if(rotateNode.rightNode != null && parentNode.rightNode == rotateNode)
				{
					Node tempNode = null;
					if(rotateNode.leftNode != null)
					{
						tempNode = rotateNode.leftNode;
					}
					
					Node grandParentNode = getParentNode(root, parentNode.data); 
					grandParentNode.rightNode = rotateNode;
					rotateNode.leftNode = parentNode; 
					parentNode.rightNode = tempNode;	
				}
				else
					/*The following if case handles right rotation of node 35
					 *           50                    50
		                        /   \                 /  \
		                      40     100     -->     40   100
		                      /       \              /     \
		                    45        120           45     150
		                    /           \           /      /  
		                  35            150        35     120
					 */
					if(rotateNode.rightNode == null && rotateNode.leftNode == null && parentNode.rightNode == rotateNode)
					{
						Node grandParentNode = getParentNode(root, parentNode.data); 
						grandParentNode.rightNode = rotateNode;
						rotateNode.leftNode = parentNode; 
					    parentNode.rightNode = null;
					}
					else
					{
						System.out.println("Node can not be rotated on the left");
					}
		}
	}
	 
	/*
	 * Rotate the node of the given data on to the left
	 */
	public void rotateLeft(R data)
	{
		RotateOnLeft(root, data);
	}
	
	/*
	 * Rotate the node of the given data on to the right
	 */
	public void rotateRight(R data)
	{
		RotateOnRight(root, data);
	}
	
	/*
	 * Rotates the binary tree on the right at given node
	 */
	private void RotateOnRight(Node n, R data)
	{
		Node rotateNode = searchNode(n, data);
		// When the to be rotated node is a children of the root node
		if(rotateNode == root.leftNode)
		{
			Node temp;
			temp = rotateNode.rightNode;
			root.leftNode = temp;
			rotateNode.rightNode = root;
			root = rotateNode;
		}
		else
		{
			/*The following if case handles right rotation of node 45
			 *           50                        50
                        /   \                     /  \
                      40     100                40    100
                         \     \        -->      \      \
                         45    150                35     150
                         /                          \
                        35                           45
			 */
			Node parentNode = getParentNode(root, data);
			if(rotateNode.leftNode != null && parentNode.rightNode == rotateNode)
			{
				Node tempNode = rotateNode.leftNode;
				rotateNode.leftNode = null;
				tempNode.rightNode = rotateNode;
				parentNode.rightNode = tempNode; 
			}
			else
				/*The following if case handles right rotation of node 45
				 *           50                    50
	                        /   \                 /  \
	                      40     100     -->    45    100
	                      /       \            /  \      \
	                    45        150        35    40     150
	                    /                         
	                  35                           
				 */
				if(rotateNode.leftNode != null && parentNode.leftNode == rotateNode)
				{
					Node tempNode = null;
					if(rotateNode.rightNode != null)
					{
						tempNode = rotateNode.rightNode;
					}
					
					Node grandParentNode = getParentNode(root, parentNode.data); 
					grandParentNode.leftNode = rotateNode;
					rotateNode.leftNode = parentNode; 
					parentNode.rightNode = tempNode;	
				}
				else
					/*The following if case handles right rotation of node 35
					 *           50                    50
		                        /   \                 /  \
		                      40     100     -->     40   100
		                      /       \              /     \
		                    45        150           35     150
		                    /                         \
		                  35                           45
					 */
					if(rotateNode.rightNode == null && rotateNode.leftNode == null && parentNode.leftNode == rotateNode)
					{
						Node grandParentNode = getParentNode(root, parentNode.data); 
						grandParentNode.leftNode = rotateNode;
						rotateNode.rightNode = parentNode; 
					    parentNode.leftNode = null;
					}
					else
					{
						System.out.println("Node can not be rotated on the left");
					}
		}
	}
	
	/*
	 * Count the number of nodes including root node
	 */
	public void countNodes()
	{
		System.out.println("Number of nodes in the tree is " + count(root));
	}
	
	/*
	 * Internal function to compute nodes recursively
	 */
	private int count(Node n)
	{
		int c = 0;
		if(n == null)
		{
			return 0;
		}
		else
		{ 
			// update c for each traversal if the node is not null
			c = 1;
			c += count(n.leftNode);
			c += count(n.rightNode);
		}
		return c;
	}
	
	/*
	 * Main method of the class
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Create a General Binary tree
		GenBinTree<String> myTree = new GenBinTree<String>();
		
		//Add nodes to the tree
		myTree.add("10");
		myTree.add("L", "50");
		myTree.add("R", "150");
		myTree.add("RR", "160");
		myTree.add("RRL", "25");
		myTree.add("RRLR", "2");
		myTree.add("LL", "40");
		myTree.add("LLR", "45");
		myTree.add("LLL", "100");
		myTree.add("RRR", "35");
		
		// Swap children of the node  
		myTree.find("35");
		// Print the tree
		myTree.print();
		// Create mirror of the tree
		myTree.mirrorOfBt();
		// Print the tree
		myTree.print();
		// Swap children of the node  
		myTree.swap("35");
		// Print the tree
		myTree.print();
		// Count the number of nodes
		myTree.countNodes();
		// Rotate left and right on the given data node
		myTree.rotateLeft("100");
		myTree.rotateRight("25");
		// Remove the node of the given data
		myTree.remove("35");
		// Count the number of nodes
		myTree.countNodes();
		// Print the tree
		myTree.print();
   }

}
