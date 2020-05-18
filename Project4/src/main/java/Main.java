
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This program implements 4 quick sort methods, times each one, and writes the 
 * results to a text file
 * @author stevl
 */
public class Main  {

    /**
     * Reads input, performs the 4 quicksort methods and heapsort, then
     * prints the output to standard out
     * @param args the command line arguments
     * @throws java.io.IOException if any file cannot be read
     */
    public static void main(String[] args) throws IOException{
        Queue[]     files;
        Sorter      Sorter;
        Metrics     Met;
        SortTimer   SortTimer;
        String      OutputFilePath;
        File        folder;
        File[]      fileList;
        try {
            OutputFilePath = args[1];
            //Get all file names
            folder = new File(args[0]);
            fileList = folder.listFiles();
            
            //Read the input folder name and store each string in a queue
            files = fileReader(fileList);
            Sorter = new Sorter();
            Met = new Metrics(fileList.length,Sorter.getNumAlgos());
            
            SortTimer = new SortTimer(Sorter, Met, files, Sorter.getNumAlgos());
            SortTimer.TimeAllAlgos();
            
            //Write to output
            outputInfo(Met,OutputFilePath,fileList);
        } 
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, 
                    null, ex);
            throw ex;
        }
    }
    /**
     * This function takes a folder path as input, and reads the names of all
     * of the files in the folder
     * @param folderPath is the path to a folder
     * @return a list of filenames in the folder
     * @throws IOException if the file cannot be read
     */
    private static Queue[] fileReader(File[] fileList) throws IOException
    {
        
        BufferedReader  br;
        FileReader      fr;
        String          read;
        int             count = 0;
        Queue[]         fileContents;
        Queue           tempQueue;
        int[]           tempArr;

        
        
        fileContents = new Queue[fileList.length];
        
        for (File file : fileList) {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            tempQueue = new Queue();
            while((read = br.readLine()) != null)
            {
                tempQueue.push(read.strip());
            }
            fileContents[count++] = tempQueue;
            
        }
        return fileContents;
    }
    
    /**
     * This function prints output from main to the final output file, 
     * including summary statistics
     */
    private static void outputInfo(Metrics Met,String OutputFilePath,
            File[] fileList) throws IOException
    {
        String Explainer = "Test results are in nanoseconds";
        String[] algos = {"quickSort1","quickSort50","quickSort100","heapsort"
                ,"quickSortMedian"};
        long[] totalmet = Met.getTotalRuntime();
        long[] avemet = Met.getAverageRuntime();
        
        for(int i = 0; i < fileList.length; i++)
            {
                Explainer += "\nTest results for this file:\t"+fileList[i]+'\n';
                for(int x = 0; x < algos.length; x++)
                {
                    Explainer += "\t"+algos[x]+"\t\t| "+Met.getOneMetric(x, i) + "\n";
                }
            }
            
        Explainer += "\n\nSummary measurements:\n";
        Explainer += String.format("%-15s | %-15s | %-15s |%n","Statistic",
                "Algorithm","Time");
        Explainer += "\nTotal\n";
        for(int x = 0; x < algos.length; x++)
        {
            Explainer += String.format("%-15s | %-15s | %-15s |%n",
                            "",algos[x],totalmet[x]);
        }
        
        Explainer += "\nAverage\n";
        for(int x = 0; x < algos.length; x++)
        {
            Explainer += String.format("%-15s | %-15s | %-15s |%n",
                            "",algos[x],avemet[x]);
        }
        
        //Set up file
        FileWriter fr = new FileWriter(OutputFilePath);
        //Print encoded strings
        try (BufferedWriter bw = new BufferedWriter(fr))
        {
            bw.write(Explainer);
        }
    }
    
}
