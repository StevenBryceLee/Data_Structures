/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

/**
 *This class times various sorts, and stores metrics on their performance
 * @author stevl
 */
public class SortTimer {
    Sorter          Sort;
    Metrics         Met;
    Queue[]         files;
    long            startTime;
    long            endTime;
    int             numAlgos;
    
    /**
     * Contructor for the sortTimer class
     * @param Sorter Any sorter class instance containing sorting algorithms
     * @param Metrics an instance of the metrics class 
     * @param filesToSort A list of values to sort
     * @param numAlgs The number of algorithms to be tested
     */
    SortTimer(Sorter Sorter, Metrics Metrics, Queue[] filesToSort, int numAlgs)
    {
        this.Sort = Sorter;
        this.Met = Metrics;
        this.files = filesToSort;
        this.numAlgos = numAlgs;
        
    }
    /**
     * This function times the sort algorithms for each file, and stores
     * the time taken in the Metrics class
     * @throws java.io.IOException when text files in the local directory cannot be made
     */
    public void TimeAllAlgos() throws IOException
    {
        int[] numArray;
        int[] temp;
        for(int i = 0; i<this.files.length; i++)
        {
            //convert the file to an array
            numArray = files[i].toIntArray();
            String[] algoList = {"quickSort1","quickSort50","quickSort100"
                    ,"heapSort","quickSortMedian"};
            //Prevent problems with tail recursion by calling all sorts before timing
            this.Sort.quickSort1(numArray.clone(), 0, numArray.length-1);
            this.Sort.quickSort50(numArray.clone(), 0, numArray.length-1);
            this.Sort.quickSort100(numArray.clone(), 0, numArray.length-1);
            this.Sort.heapSort(numArray.clone());
            this.Sort.quickSortMedian(numArray.clone(), 0, numArray.length-1);
            for(int x = 0; x < this.numAlgos; x++)
            {
                //Clone the array so the original file is not sorted
                temp = singleAlgoTimer(x,numArray.clone());
                printResults(temp, numArray[0], numArray[1], algoList[x]);
            }
        }
    }
    
    /**
     * This function times a single algorithm, and returns the time taken
     * for that algorithm
     * @return the sorted array file
     */
    private int[] singleAlgoTimer(int algoNumber, int[] file)
    {
        switch (algoNumber) {
            case 0:  //quicksort1
                this.startTime = System.nanoTime();
                this.Sort.quickSort1(file, 0, file.length-1);
                this.endTime = System.nanoTime() - this.startTime;
                this.Met.storeMetric(endTime, algoNumber);
                break;
            case 1: //quickSort50
                this.startTime = System.nanoTime();
                this.Sort.quickSort50(file, 0, file.length-1);
                this.endTime = System.nanoTime() - this.startTime;
                this.Met.storeMetric(endTime, algoNumber);
                break;
            case 2: //quickSort100
                this.startTime = System.nanoTime();
                this.Sort.quickSort100(file, 0, file.length-1);
                this.endTime = System.nanoTime() - this.startTime;
                this.Met.storeMetric(endTime, algoNumber);
                break;
            case 3:    //heapSort
                this.startTime = System.nanoTime();
                this.Sort.heapSort(file);
                this.endTime = System.nanoTime() - this.startTime;
                this.Met.storeMetric(endTime, algoNumber);
                break;
            case 4: //quicksort with median partition
                this.startTime = System.nanoTime();
                this.Sort.quickSortMedian(file, 0, file.length-1);
                this.endTime = System.nanoTime() - this.startTime;
                this.Met.storeMetric(endTime, algoNumber);
                break;
            default:
                break;
        }
        return file;
    }
    /**
     * Writes the sorted file to an output file in the Outputs folder
     * @param Arr is the sorted array to write
     * @param first is the first value in the unsorted array
     * @param second is the second value in the unsorted array
     * @param algo is the name of the algorithm used to sort
     * @throws IOException if the .txt file cannot be made
     */
    private void printResults(int[] Arr, int first, int second, String algo)
            throws IOException
    {
        String print = "";
        String filePath = algo + "_";
        File dir = new File("Outputs");
        dir.mkdirs();
        switch(first - second)  //Find the file type by the difference of values
        {
            case 0:
                filePath += "Sam";
                break;
            case 1:
                filePath += "Des";
                break;
            case -1:
                filePath += "Asc";
                break;
            default:
                filePath += "Shu";
                break;
        }
        filePath += String.valueOf(Arr.length)+".txt";
        
        
        for(int i = 0; i < Arr.length; i++)
        {
            print += String.valueOf(Arr[i]) + " \n";
        }
        
        //Set up file
        File tmp = new File(dir, filePath);
        tmp.createNewFile();
        FileWriter fr = new FileWriter(filePath);
        //Print encoded strings
        try (BufferedWriter bw = new BufferedWriter(fr))
        {
            bw.write(print);
        }
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, 
                    null, ex);
            throw ex;
        }
    }
}
