/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This class creates nodes, in order to implement linked lists
 * @author stevl
 */
public class Node {
    int data;
    Node next;
    
    Node(int data,Node next)
    {
        this.data = data;
        this.next = next;
    }
}
