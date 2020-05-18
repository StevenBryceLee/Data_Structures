/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This is a superclass for recursive and iterative hanoi solvers, and 
 * implements methods which they have in common
 * 
 * The steps String stores the list of steps necessary to solve
 * prevOdd and prevEven stores the previous odd and even solution
 * @author stevl
 */
abstract public class HanoiSolver {
    protected char[] steps;
    protected char[] prevOdd;
    protected char[] prevEven;
    protected int stepsCount;
    /**
     * This function sets the steps variable
     * @param s is what the steps variable will be reset to
     */
    abstract public void setNDisks(int i);
    
    /**
     * This function sets the steps variable of the object
     * @param s is a char array of previous moves which need to be stored 
     * for memoization
     */
    public void setSteps(char[] s)
    {
        this.steps = s;
    }
    
    /**
     * This function sets the stepsCount variable, for iterating through the steps
     * array
     * @param i is the number of steps to set the solver to
     */
    public void setStepsCount(int i)
    {
        this.stepsCount = i;
    }
    
    /**
     * Set the prevOdd variable to the previous, odd solution
     * @param s The string to which we set our solution
     */
    public void setPrevOdd(char[] s)
    {
        this.prevOdd = s;
    }
    
    /**
     * Set the prevOdd variable to the previous, even solution
     * @param s The string to which we set our solution
     */
    public void setPrevEven(char[] s)
    {
        this.prevEven = s;
    }
    
    /**
     * Converts the characters into a string as per program requirements
     * @return the list of steps for solving the tower of hanoi problem
     */
    public String getSteps()
    {
        String temp = "";
        for(int i = 0; i < stepsCount; i++)
        {
            char fromTower = steps[i++];
            char toTower = steps[i++];
            char disk = steps[i];
            
            //Handle first part of the phrase
            temp += "\nMove disk " + disk;
            
            //handle the second digit, if necessary
            if(Character.isDigit(steps[i+1]))
            {
                temp += steps[++i];
            }
            
            //Append the rest of the phrase
            temp += " from tower " + fromTower + " to tower " + toTower;
        }
        return temp;
    }
    
    /**
     * This function actually writes the steps when a valid move occurs
     * @param fromTower is a character representing the initial tower
     * @param toTower is a character representing the target tower
     * @param disk is the integer number of the disk
     */
    public void writeSteps(char fromTower, char toTower, int disk)
    {
        this.steps[stepsCount++] += fromTower;
        this.steps[stepsCount++] += toTower;
        //Handle double digit disks
        if(disk > 9)
        {
            this.steps[stepsCount++] += (char) (((int)disk / 10) + '0');
            this.steps[stepsCount++] += (char) (((int)disk % 10) + '0');
        }
        else
        {
            this.steps[stepsCount++] += (char) (disk + '0');
        }
    }
    
    /**
     * This is a troubleshooting function, helpful for figuring out what the 
     * current steps were if the program throws unexpected output
     */
    public void printSteps()
    {
        for(int i = 0; i < stepsCount; i++){
        
            System.out.print(this.steps[i]);
        }
    }
    
    
}
