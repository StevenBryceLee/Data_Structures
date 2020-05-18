/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This class takes a stack of characters, sorts them by frequency, and outputs
 * an array of the length of the frequency table
 * @author stevl
 */
public class FreqTable {
    
    String[] S;
    String[] Freq;
    char[] Letters;
    int[] count;
    /**
     * Construct the frequency table based on a given file of frequencies
     * for the alphabet
     * @param S Is a list of the frequencies of each element
     */
    FreqTable(String[] S)
    {
        Freq = new String[S.length];
        Freq = S;
        //sort the table
        this.quickSort(Freq, 0, Freq.length-1);
        //Split the table into primitive types
        Letters = new char[S.length];
        count = new int[S.length];
        splitString();
    }
    
    /**
     * Helper function to print out current values of the frequency table
     */
    public void printFreq()
    {
        for(int i = 0; i < this.Freq.length; i++)
        {
            System.out.println(Freq[i]);
        }
    }
    
    /**
     * This function populates the letters and numbers arrays with their 
     * respective values, to eliminate unnecessary function calls to get
     * values
     */
    private void splitString()
    {
        for(int i = 0; i < this.Freq.length; i++){
        
            Letters[i] = Freq[i].charAt(0);
            this.count[i] = intVal(Freq[i]);
        }
    }
    
    /**
     * 
     * @return the letters in sorted order
     */
    public char[] getCharArr()
    {
        return this.Letters;
    }
    
    /**
     * 
     * @return the frequencies for each letter
     */
    public int[] getCountArr()
    {
        return this.count;
    }
    
    /**
     * 
     * @return the frequency table
     */
    public String[] getFreq()
    {
        return this.Freq;
    }
    
    /**
     * Implements quicksort on the string table
     * @param Freq is the Frequency table of alphabet characters
     * @param start is the starting index
     * @param end is the ending index
     */
    private void quickSort(String[] Freq, int start, int end)
    {
        
        //handle base case
        if(start >= end)
            return;
        
        //select partition
        int part = getPartition(Freq, start, end);
        
        //recursive case for before partition
        quickSort(Freq,start,part - 1);
        
        //recursive case for after partition
        quickSort(Freq,part + 1,end);
        
        
    }
    
    /**
     * This function gets the partition necessary to recursively do quicksort
     * @param Freq is the frequency table for the Strings
     * @param start is the starting array value
     * @param end is the ending array value
     * @return an integer for the next partition
     */
    private int getPartition(String[] Freq, int start, int end)
    {
        int pivot = intVal(Freq[end]);
        
        //assign pointer to the start
        int low = start-1;
        for(int i = start; i < end; i++)
        {
            //iterate until finding a value greater than the pivot
            if(intVal(Freq[i]) < pivot)
            {
                //increment the low pointer
                low++;
                
                //Swap item
                String temp = Freq[low];
                Freq[low] = Freq[i];
                Freq[i] = temp;
            }
            
        }
        
        //swap the low element with the pivot
        String temp = Freq[low + 1];
        Freq[low + 1] = Freq[end];
        Freq[end] = temp;
        
        return low + 1;
    }
    /**
     * Helper function to get the frequency from the string in the frequency table
     * @param S a string from the frequency table
     * @return an integer from the end of the string
     */
    private int intVal(String S)
    {
        return Integer.valueOf(S.substring(2));
    }
}
