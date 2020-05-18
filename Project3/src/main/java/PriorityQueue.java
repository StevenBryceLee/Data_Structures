/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stevl
 */
public class PriorityQueue {
    Node head;
    Node tail;
    Node next;
    int numElements;
    
    /**
     * Construct an empty queue
     */
    PriorityQueue()
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
        return head.next == null;
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
        Node currNode = head.next;
        while(currNode != null)
        {
            temp += currNode.getData() + "\n";
            currNode = currNode.next;
        }
        return temp;
    }
    
    /**
     * push a value onto the stack
     * @param i is the integer to push onto the stack
     */
    public void push(String i)
    {
        //System.out.println(i);
        Node currNode;
        if(isEmpty())        //handle the empty list
        {
            currNode = new Node(i,null);    //next pointer is null
            this.head.next = currNode;
            this.tail.next = currNode;
        }
        else
        {
            Node prevNode = tail.next;
            Node nextNode = head.next;
            currNode = new Node(i,null);
            
            prevNode.next = currNode;
            tail.next = currNode;
        }
        numElements++;
        
    }
    /**
     * This function compares two node frequencies to see which is greater
     * @param first is the first node to compare
     * @param second is the second node to compare
     * @return is true if first's frequency is greater than second's frequency
     */
    private boolean FirstIsGreater(Node first, Node second)
    {
        
        int data1 = first.getFreq();
        int data2 = second.getFreq();
        if(data1 >= data2)
        {
            return true;
        }
        return false;
    }
    
    /**
     * This push function handles nodes containing strings and frequencies
     * @param i is the character to be pushed
     * @param freq is the frequency for that character
     */
    public void push(String i, int freq)
    {
        Node currNode = new Node(i,freq,null,null);
        if(isEmpty())        //handle the empty list
        {
            this.head.next = currNode;
            this.tail.next = currNode;
        }
        else
        {
            Node nextNode = head.next;
            //check priority and send currnode to correct location
            while(FirstIsGreater(currNode,nextNode))
            {
                if(nextNode.next == null)
                {
                    nextNode.next = currNode;
                    tail.next = currNode;
                    numElements++;
                    return;
                }
                
                nextNode = nextNode.next;
                
            }
            
            //perform insert, update currnode and nextnode
            currNode.next = nextNode.next;
            nextNode.next = currNode;
            
        }
        numElements++;
    }
    /**
     * This push function handles nodes containing strings and frequencies
     * @param N is the node to be pushed
     */
    public void push(Node N)
    {
        Node currNode = N;
        Node prevNode = head.next;
        if(isEmpty())        //handle the empty list
        {
            this.head.next = currNode;
            this.tail.next = currNode;
        }
        else
        {
            Node nextNode = head.next;
            //check priority and send currnode to correct location
            while(nextNode != null && FirstIsGreater(currNode,nextNode) )
            {
                prevNode = nextNode;
                nextNode = nextNode.next;
            }
            //perform insert, update currnode and nextnode
            currNode.next = prevNode.next;
            prevNode.next = currNode;
            if(currNode.next == null)
            {
                tail.next = currNode;
            }
        }
        numElements++;
    }
    
    
    /**
     * 
     * @return the first node in the queue
     */
    public Node peek()
    {
        return this.head.next;
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
            Node currNode = head.next;
            head.next = currNode.next;
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
            Node currNode = head.next;
            head.next = currNode.next;
            return currNode;
        }
    }
    
    /**
     * This function empties the queue and prints all entries
     */
    public void emptyQueue()
    {
        Node temp;
        while((temp = this.pop(true)) != null)
        {
            System.out.println(temp.getData());
        }
    }
    /**
     * This function goes through each queue element without emptying it 
     * printing to the command line
     */
    public void iterateQueue()
    {
        Node temp = head.next;
        String S = "";
        while(temp != null)
        {
            S +=(temp.getData()+":"+temp.getFreq() + " , ");
            temp = temp.next;
            
        }
        System.out.println(S);
    }
}
