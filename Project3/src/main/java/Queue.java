/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This class implements a linked queue data structure
 * @author stevl
 */
public class Queue {
    
    Node head;
    Node tail;
    int numElements;
    
    /**
     * Construct an empty queue
     */
    Queue()
    {
        this.head = new Node("",null);
        this.tail = new Node("",null);
        this.numElements = 0;
    }
    
    /**
     * 
     * @return true if the stack has no nodes, otherwise return false
     */
    public boolean isEmpty()
    {
        return head.right == null;
    }
    
    /**
     * @return the number of elements in the queue
     */
    public int getNumElements()
    {
        return this.numElements;
    }
    
    /**
     *@return the full list of stack values
     */
    public String getQueue()
    {
        var temp = "";
        int count = 0;
        Node currNode = head.right;
        while(currNode != null)
        {
            temp += count + ":\t"+ currNode.getData() + "\n";
            currNode = currNode.right;
        }
        return temp;
    }
    
    /**
     * push a value onto the stack
     * @param i is the integer to push onto the stack
     */
    public void push(String i)
    {
        //System.out.println("Begin push");
        //System.out.println(i);
        Node currNode;
        if(isEmpty())        //handle the empty list
        {
            currNode = new Node(i,null);    //right pointer is null
            this.head.right = currNode;
            this.tail.right = currNode;
        }
        else
        {
            Node prevNode = tail.right;
            currNode = new Node(i,null);
            prevNode.right = currNode;
            tail.right = currNode;
        }
        numElements++;
        
    }
    
    /**
     * This push function handles nodes containing strings and frequencies
     * @param i is the character to be pushed
     * @param freq is the frequency for that character
     */
    public void push(String i, int freq)
    {
        Node currNode;
        if(isEmpty())        //handle the empty list
        {
            currNode = new Node(i,freq,null,null);
            this.head.right = currNode;
            this.tail.right = currNode;
        }
        else
        {
            Node prevNode = tail.right;
            currNode = new Node(i,freq,null,null);
            prevNode.right = currNode;
            tail.right = currNode;
        }
        numElements++;
    }
    
    
    /**
     * pops the top value of the stack
     * @return the integer in the stack
     */
    public String pop()
    {
        if(isEmpty())        //handle the empty list
        {
            return null;
        }
        else
        {
            numElements--;
            Node currNode = head.right;
            head.right = currNode.right;
            return currNode.getData();
        }
    }
    /**
     * This pop function is used if you want to return the node itself,
     * 
     * this allows access to both the character and frequency
     * @param True differentiates the type of pop operation
     * @return the node at the head of the queue
     */
    public Node pop(boolean True)
    {
        if(isEmpty())        //handle the empty list
        {
            return null;
        }
        else
        {
            numElements--;
            Node currNode = head.right;
            head.right = currNode.right;
            return currNode;
        }
    }
}
