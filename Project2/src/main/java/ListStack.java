/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *  This class implements the stack interface with a singly linked list
 * head points to the top of the stack
 * @author stevl
 */
public class ListStack implements Stack {
    
    Node head;
    int numElements;
    
    ListStack()
    {
        this.head = new Node(0,null);
        this.numElements = 0;
    }
    /**
     * 
     * @return true if the stack has no nodes, otherwise return false
     */
    public boolean isEmpty()
    {
        return head.next == null;
    }
    /**
     *@Return the full list of stack values
     */
    public String getStack()
    {
        var temp = "";
        Node currNode = head.next;
        while(currNode != null)
        {
            temp += currNode.data + " ";
            currNode = currNode.next;
        }
        return temp;
    }
    
    /**
     * 
     * @return the top of the stack without popping the stack value
     */
    public int peek()
    {
        if(!isEmpty())
        {
            return head.next.data;
        }
        return Integer.MIN_VALUE;
    }
    /**
     * push a value onto the stack
     * @param i is the integer to push onto the stack
     */
    public void push(int i)
    {
        Node currNode;
        if(isEmpty())        //handle the empty list
        {
            currNode = new Node(i,null);    //next pointer is null
        }
        else
        {
            currNode = new Node(i,this.head.next);
        }
        numElements++;
        this.head.next = currNode;
    }
    /**
     * pops the top value of the stack
     * @return the integer in the stack
     */
    public int pop()
    {
        if(isEmpty())        //handle the empty list
        {
            return Integer.MIN_VALUE;
        }
        else
        {
            numElements--;
            Node currNode = head.next;
            head.next = currNode.next;
            return currNode.data;
        }
    }
    /**
     * This function pushes the contents of another stack into this stack
     * WARNING: The new stack will be in reverse order
     * @param s 
     */
    public void reverseCopy(ListStack s)
    {
        int temp;
        while((temp = s.pop()) != Integer.MIN_VALUE)
        {
            this.push(temp);
        }
    }
    
    /**
     * This function empties out the contents of the stack
     */
    public void emptyStack()
    {
        int temp;
        while((temp = this.pop()) != Integer.MIN_VALUE)
        {
            System.out.println(temp);
        }
    }
}
