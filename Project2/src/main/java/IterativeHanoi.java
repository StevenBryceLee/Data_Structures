/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.Math;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *This class iteratively solves the tower of Hanoi problem using stacks
 * The liststacks implement the 3 stacks necessary to store 'disks'
 * nDisks contains the number of disks to be solved
 * numSteps contains the number of steps necessary to solve the problem
 * prevEvenSteps and prevOddSteps contain the previous solutions, if they exist
 * which can be reused
 * @author stevl
 */
public class IterativeHanoi extends HanoiSolver {
    private ListStack from, aux, to, evenTemp, oddTemp;
    protected int nDisks;
    private int numSteps;
    
    /**
     * initialize each variable
     */
    IterativeHanoi()
    {
        from = new ListStack();
        aux = new ListStack();
        to = new ListStack();
        evenTemp = new ListStack();
        oddTemp = new ListStack();
        
    }
    
    /**
     * This function sets the number of disks that we need to solve
     * In response, also sets the number of steps and the size of the char array
     * @param i is the number of disks
     * @throws OutOfMemoryError if the disk size is too big
     */
    public void setNDisks(int i)
    {
        this.nDisks = i;
        try
        {
            this.numSteps = (int) (Math.pow(2,i)) - 1;
            this.steps = new char[this.numSteps * 5];
        }
        catch(OutOfMemoryError e)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, 
                    null, e);
            throw e;
        }
        
    }
    
    /**
     * This function takes an integer, and pushes values from 1 to n into that stack
     */
    public void setEvenTemp(int n)
    {
        int i;
        for(i = n; i >= 1; i--){
        
            this.evenTemp.push(i);
        }
    }
    /**
     * This function takes an integer, and pushes values from 1 to n into that stack
     * @param n is the number of disks to push
     */
    public void setOddTemp(int n)
    {
        int i;
        for(i = n; i >= 1; i--)
            {
                this.oddTemp.push(i);
            }
    }
    
    /**
     * clears all values from the stacks
     */
    public void clearStacks()
    {
        while( (from.pop())!= Integer.MIN_VALUE)
        {
        }
        
        while( (to.pop()) != Integer.MIN_VALUE)
        {
        }
        
        while( (aux.pop()) != Integer.MIN_VALUE)
        {
        }
    }
    
    /**
     * This function takes a stack, and fills it with a number of disks
     */
    /**
     * Reuse a past solution, if one is available
     * Criteria for reusing a solution are the nDisks > 3, we have previously
     * computed a solution for nDisks - 1, and we are on step 1
     * @return sets the loop in the body forward by the number of steps completed
     */
    private int reuseSolution()
    {
        if(this.nDisks % 2 == 1)
        {
            this.steps = this.prevOdd;
            for(int x = this.nDisks - 2; x >= 1; x--)
            {
                to.push(x);
            }
        }
        else
        {
            this.steps = this.prevEven;
            for(int x = this.nDisks - 2; x >= 1; x--)
            {
                aux.push(x);
            }
        }
        
        //pop all values from the 'from' stack except the last 2
        while(from.peek() != this.nDisks - 1 )
        {
            from.pop();
        }

        //move i forward by the previous solution's steps
        return (int)Math.pow((this.nDisks - 2), 2);
    }
    /**
     * This function implements the tower movements to solve
     * Towers of Hanoi in an iterative way
     */
    public void HanoiSolver()
    {
        char f = 'A', a = 'C', d = 'B';
        
        //If n is even, switch the toTower and auxTower
        if(this.nDisks % 2 == 0)
        {
            char temp = d;
            d = a;
            a = temp;
        }
        
        //push disks into 'from' stack
        for(int i = this.nDisks; i >= 1; i--)
        {
            from.push(i);
        }
        
        //move disks between poles
        for (int i = 1; i <= this.numSteps; i++) 
        { 
            //reuse past solution and continue, if possible
            
            //reuse past solutions if available
            if(this.nDisks > 3 && 
                    i == 1 && 
                    evenTemp.isEmpty()==false)
            {
                i = this.reuseSolution();
            }

            switch (i % 3) {
                case 1:
                    moveDisks(from, to, f, d);
                    break;
                case 2:
                    moveDisks(from, aux, f, a);
                    break;
                case 0: 
                    moveDisks(aux, to, a, d);
                    break;
                default:
                    break;
            }
        }
        
        //store results for later
        saveSteps();
        
    }
    
    /**
     * This function stores the steps in the correct variable for later reuse
     * if possible
     */
    private void saveSteps()
    {
        if(this.nDisks % 2 == 1) //Store odd solutions, else store even
        {
            this.prevOdd = this.steps;
            
        }
        else
        {
            this.prevEven = this.steps;
        }
    }
    
    /**
     * This function changes the location of a disk from one
     * stack to another stack
     * @param From is the stack from which the disk comes
     * @param To is the stack to which the disk is moved
     * @param f is the character indicating which stack is the source
     * @param d is the character indicating which stack is the destination
     */
    private void moveDisks(ListStack From, ListStack To, char f, char d)
    {
        int fromTop = From.pop();
        int toTop = To.pop();
        
        if(fromTop == Integer.MIN_VALUE) //from pole is empty
        {
            From.push(toTop);
            writeSteps(d,f,toTop);
        }
        else if(toTop == Integer.MIN_VALUE) //to pole is empty
        {
            To.push(fromTop);
            writeSteps(f,d,fromTop);
        }
        else if (fromTop > toTop)
        {
            From.push(fromTop);
            From.push(toTop);
            writeSteps(d,f,toTop);
        }
        else
        {
            //System.out.println("Inside final\nDestTop\tSourceTop\n\t" + toTop + fromTop);
            To.push(toTop);
            To.push(fromTop);
            writeSteps(f,d,fromTop);
        }
    }
    
    
}
