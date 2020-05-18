/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This class stores nodes, both for use as a binary tree and a list
 * @author stevl
 */
public class Node {
    String letter;
    int freq;
    Node right;
    Node left;
    Node next;
    
    //Use the node class as a list
    Node(String data,Node next)
    {
        this.letter = data;
        this.next = next;
    }
    
    //use the node class as a binary tree
    Node(String letter, int freq,Node left,Node right)
    {
        this.letter = letter;
        this.freq = freq;
        this.right = right;
        this.left = left;
        //this.parent = parent;
    }
    
    //Create a node with no children
    Node(String letter, int freq)
    {
        this.letter = letter;
        this.freq = freq;
        this.right = null;
        this.left = null;
        this.next = null;
    }
    
    /**
     * This function sets the left child of a node
     * @param N is the left child
     */
    public void setLeft(Node N)
    {
        this.left = N;
    }
    
    /**
     * Set the left child of a node, given string and int for making a new node
     * @param data
     * @param freq 
     */
    public void setLeft(String data, int freq)
    {
        Node N = new Node(data, freq);
        this.left = N;
    }
    
    /**
     * This function sets the right child of the node
     * @param N is the right child
     */
    public void setRight(Node N)
    {
        this.right = N;
    }
    
    public void setRight(String data, int freq)
    {
        Node N = new Node(data, freq);
        this.right = N;
    }
    
    public void setData(String data)
    {
        this.letter = data;
    }
    
    public void setFreq(int data)
    {
        this.freq = data;
    }
    /**
     * 
     * @return the data in this node
     */
    public String getData()
    {
        return this.letter;
    }
    /**
     * 
     * @return the frequency in this node
     */
    public int getFreq()
    {
        return this.freq;
    }
}
