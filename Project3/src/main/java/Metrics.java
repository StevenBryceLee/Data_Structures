

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
    
    private long[] encodedMetrics;
    private long[] decodedMetrics;
    private int rTop = -1;
    private int iTop = -1;
    private String[] inputString = new String[100];
    private int topString = -1;
    private int maxMetrics;
    
    
    Metrics()
    {
        maxMetrics = 50;
        encodedMetrics = new long[maxMetrics];
        decodedMetrics = new long[maxMetrics];
    }
    /**
     * override constructor to specify size of metrics
     * @param numMetrics is the size of the metrics array
     */
    Metrics(int numMetrics)
    {
        maxMetrics = numMetrics;
        encodedMetrics = new long[maxMetrics];
        decodedMetrics = new long[maxMetrics];
    }
    /**
     * Store the time taken to execute the tower of hanoi problem either
     * with recursion or iteration
     * @param n is the time taken to execute
     * @param type is the method, recursion or iteration, used
     */
    public void storeMetric(long n, String type)
    {
        switch (type) {
            case "encoded":
                encodedMetrics[++rTop] = n;
                break;
            case "decoded":
                decodedMetrics[++iTop] = n;
                break;
            default:
                break;
        }
    }
    
    public long[][] getAllMetrics()
    {
        var temp = new long[2][maxMetrics];
        temp[0] = this.encodedMetrics;
        temp[1] = this.decodedMetrics;
        return temp;
    }

    public String[] getOneMetric(int n)
    {
        var tempArr = new String[2];
        if(n > -1 && n <= this.rTop && n <= this.iTop)
        {
            tempArr[0] = String.valueOf(this.encodedMetrics[n]);
            tempArr[1] = String.valueOf(this.decodedMetrics[n]);
            return tempArr;
        }
        else
        {
            return null;
        }
    }

    /**
     * 
     * @return the total run time over the number of metrics
     */
    public long[] getAverageRuntime()
    {
        var tempArr = new long[2];      //default value is guaranteed to be 0
        if(this.rTop > 0 && this.iTop > 0)
        {
            tempArr[0] = getTotalRuntime()[0] / (this.rTop + 1);
            tempArr[1] = getTotalRuntime()[1] / (this.iTop + 1);
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
        var tempArr = new long[2];      //default value is guaranteed to be 0
        if(this.rTop > 0 && this.iTop > 0)
        {
            for(int i = 0; i <= this.rTop; i++)
            {
                tempArr[0] += this.encodedMetrics[i];
                tempArr[1] += this.decodedMetrics[i];
            }
            return tempArr;
        }
        else if (this.rTop == 0)
        {
            tempArr[0] += this.encodedMetrics[0];
            tempArr[1] += this.decodedMetrics[0];
            return tempArr;
        }
        else
        {
            return null;
        }
    }
    
}
