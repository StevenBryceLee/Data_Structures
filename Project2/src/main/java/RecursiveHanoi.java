/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This class solves the tower of hanoi problem recursively, using a stack
 * @author stevl
 */
public class RecursiveHanoi extends HanoiSolver {
    private int nDisks;
    
    RecursiveHanoi(int NDisks)
    {
        setNDisks(NDisks);
        //the size of the steps array is proportional 
        //HanoiSolver(4,Towers[0],Towers[1],Towers[2],0);
    }
    
    public void setNDisks(int i)
    {
        this.nDisks = i;
        this.steps = new char[((int) (Math.pow(2,i)) - 1) * 5];
    }
    
    /**
     * This program solves the tower of Hanoi problem
     * and writes the result to the 'steps' string
     * @param n specifies the number of disks in the game
     * @param fromTower specifies the beginning tower
     * @param toTower specifies the ending tower
     * @param auxTower specifies the auxiliary tower 
     */
    public void HanoiSolver(int n, char fromTower, char toTower, char auxTower
            //, int recursDepth
                            )
    {
        //System.out.println("Recurs Depth: " + recursDepth);
        if(n == 1)  //base case
        {
            writeSteps(fromTower,toTower,n);
            return;
        }
        
        HanoiSolver(n - 1, fromTower, auxTower, toTower //recursive step
                //,recursDepth+1
                ); 
        writeSteps(fromTower,toTower,n);
        HanoiSolver(n - 1, auxTower, toTower, fromTower //Use 'A' as auxiliary
                //,recursDepth+1
                    ); 
        
    }
}
