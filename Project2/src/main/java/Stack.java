/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * This interface makes sure that the array stack implements the 
 * required functions
 * @author stevl
 */
public interface Stack {
    public boolean isEmpty();
    public int peek();

    /**
     *Pushes a character operator onto the stack
     * @param c
     */
    public void push(int c);

    /**
     * Empties the latest entry in the stack and returns it
     * @return
     */
    public int pop();
    
    /**
     * 
     * @return the contents of the stack as a string
     */
    
}
