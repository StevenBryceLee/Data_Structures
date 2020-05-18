

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *The purpose of this class is to test recursive vs iterative solvers of the
 * Towers of Hanoi problem, using n = 1,2,...n = 50
 * @author stevl
 */
public class RecursVSIterTester {
    
    int             testSize;
    RecursiveHanoi  RH;
    IterativeHanoi  IH;
    Metrics         Met;
    long            startTime;
    long            endTime;
    String          allSteps;
    
    /**
     * Set the initial conditions
     * @param nDisks the number of disks with which we start
     * @param emptyMet An empty metrics array
     */
    RecursVSIterTester(int nDisks, Metrics emptyMet)
    {
        testSize = nDisks;
        RH = new RecursiveHanoi(this.testSize);
        IH = new IterativeHanoi();
        
        //set number of disks for the iterative solver
        IH.setNDisks(nDisks);
        RH.setNDisks(nDisks);
        //create metrics holder
        Met = emptyMet;
        
        //Create and format the output header
        allSteps = "The list of all steps for each number of disks:";
    }
    /**
     * This function returns 
     * @return the set of all steps from each test to the output
     */
    public String getAllSteps()
    {
        return this.allSteps;
    }
    /**
     * This function clears the steps holder for both solvers
     */
    private void clearSteps()
    {
        RH.setStepsCount(0);
        IH.setStepsCount(0);
    }
    
    /**
     * Test a single number of disks, and store the results in the metrics
     * @param i is the number of disks to tests
     */
    public void beginTest(int i)
    {
        
        //Setting the disks prior to execution
        IH.setNDisks(i);
        RH.setNDisks(i);
            
            
        RH.HanoiSolver(i, 'A', 'B', 'C');
        IH.HanoiSolver();
        
        System.out.println(RH.getSteps());
        
        System.out.println(IH.getSteps());
    }
    /**
     * Test and store the range of numbers of disks given
     * prevOdd stores the previous odd output, allowing the program to reuse past 
     * solutions
     * prevEven stores the previous even output
     */
    public void testRange()
    {
        for(int i = 1; i <= this.testSize; i++)
        {
            System.out.println("Current iteration: " + i + " of " + this.testSize);
            
            //Setting the disks prior to execution
            IH.setNDisks(i);
            RH.setNDisks(i);
            
            //time recursive hanoi solver execution
            this.startTime = System.nanoTime();
            RH.HanoiSolver(i, 'A', 'B', 'C');
            this.endTime = System.nanoTime() - this.startTime;
            
            //System.out.println(RH.getSteps());
            
            //Store recursive hanoi in metrics
            this.Met.storeMetric(endTime, "recursion");
            
            //time Iterative Hanoi solver execution
            this.startTime = System.nanoTime();
            IH.HanoiSolver();
            this.endTime = System.nanoTime() - this.startTime;
            
            //System.out.println(IH.getSteps());
            //store iterative hanoi solver time in metrics
            this.Met.storeMetric(endTime, "iteration");
            
            //store steps from recursive hanoi solver without loss of generality
            this.allSteps += "\n\nDisk Number: " + String.valueOf(i);
            this.allSteps += RH.getSteps();
            
            //Clear the steps from both solvers
            clearSteps();
            //Clear the stacks from the iterative solver
            IH.clearStacks();
            
        }
        
    }
    
    /**
     * Prints individual, total, and average runtimes for recursive and iterative
     * hanoi solvers. If milliseconds > 1000000000, divide by the same to get seconds
     * @return all test results to a formatted string for printing
     */
    public String getMetrics()
    {
        var totalmet = Met.getTotalRuntime();
        var avemet = Met.getAverageRuntime();
        String temp = "Time measurements per disk in nanoseconds\n";
        temp += String.format("%-15s | %-15s | %-15s |%n","Number of Disks",
                "Recursion","Iteration");
        boolean twoSecondExceeded = true;
        
        //
        var allMetrics = Met.getAllMetrics();
        for(int i = 0; i < testSize; i++)
        {
            
            if(allMetrics[0][i] > 2000000000)
            {
                if(twoSecondExceeded)
                {
                    temp += "\nAll measurements now converted to seconds\n";
                    twoSecondExceeded = false;
                }
                allMetrics[0][i] /= 2000000000;
                allMetrics[1][i] /= 2000000000;
            }
            temp += String.format("%-15d | %-15d | %-15d |%n", 
                                   i+1,allMetrics[0][i],allMetrics[1][i]);
        }
        
        temp += "\n\nSummary measurements:\n";
        temp += String.format("%-15s | %-15s | %-15s |%n","Statistic",
                "Recursion","Iteration");
        temp += String.format("%-15s | %-15d | %-15d |%n",
                              "Total ",totalmet[0],totalmet[1]);
        
        temp += String.format("%-15s | %-15d | %-15d |%n",
                              "Average ",avemet[0],avemet[1]);
        
        return temp;
    }
}
