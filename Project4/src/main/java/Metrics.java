

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *The purpose of this class is to store metrics on run time for each string
 * @author stevl
 */
public class Metrics {
    
    private final long[][] sorts;
    private int Top = 0;
    private final int maxMetrics;
    private final int numAlgos;
    
    /**
     * Default constructor for 4 algorithms and 15 files to sort
     */
    Metrics()
    {
        maxMetrics = 15;
        numAlgos = 4;
        sorts = new long[numAlgos][maxMetrics];
    }
    
    /**
     * Constructor for allowing manual setting of the files and algorithms
     */
    Metrics(int maxMet, int numAlg)
    {
        this.maxMetrics = maxMet;
        this.numAlgos = numAlg;
        sorts = new long[numAlgos][maxMetrics];
    }
    
    /**
     * Store the time taken to execute each sorting algorithm
     * @param n is the time taken to execute
     * @param algoNumber is the numbered algorithm which is executed 
     */
    public void storeMetric(long n, int algoNumber)
    {
        if(sorts[sorts.length-1][this.Top]!= 0)
        {
            this.Top++;
        }
        sorts[algoNumber][this.Top] = n;
        
    }
    /**
     * 
     * @return the top of the list
     */
    public int getTop()
    {
        return this.Top;
    }
    
    /**
     * 
     * @return all available metrics
     */
    public long[][] getAllMetrics()
    {
        return this.sorts;
    }
    
    /**
     * @return a single metric, given the algorithm number and file number
     */
    public long getOneMetric(int algoNumber, int fileNumber)
    {
        return this.sorts[algoNumber][fileNumber];
    }

    /**
     * 
     * @return the total run time over the number of metrics
     */
    public long[] getAverageRuntime()
    {
        var tempArr = new long[this.numAlgos];
        if(this.Top > 0)
        {
            for(int i = 0; i < tempArr.length; i++)
            {
                tempArr[i] = getTotalRuntime()[i] / (this.Top + 1);
            }
            return tempArr;
        }
        else
        {
            return getTotalRuntime();
        }
    }
    
    /**
     * 
     * @return the sum of run times stored as a long array
     */
    public long[] getTotalRuntime()
    {
        var tempArr = new long[this.numAlgos]; 
        if(this.Top > 0)
        {
            for(int i = 0; i < this.numAlgos; i++)
            {
                for(int x = 0; x < this.Top; x++)
                {
                    tempArr[i] += this.sorts[i][x];
                }
            }
            return tempArr;
        }
        else if (this.Top == 0)
        {
            for(int i = 0; i < this.numAlgos; i++)
            {
                tempArr[i] += this.sorts[i][0];
            }
            return tempArr;
        }
        else
        {
            return null;
        }
    }
    
}
