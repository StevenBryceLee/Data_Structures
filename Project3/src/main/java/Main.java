
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This class reads unencoded strings from a text file and encodes them
 * It also reads encoded string and decodes them
 * It also reads in a frequency table to build the huffman encoding scheme
 * 
 * @author stevl
 */
public class Main {

    /**
     * Reads the input and calls the encoder and decoder for the huffman trees
     * @param args the command line arguments
     * @throws java.io.IOException if the files from args cannot be opened
     */
    public static void main(String[] args) throws IOException  {
        
        String          outputFile;
        String          freqTableFile;
        Queue[]         fileContents;  //Store the input files
        FreqTable       FT;
        HuffTree        Huff;
        EncodeDecode    Coder;
        Metrics         Met = new Metrics();
        
        try {
            //Read the input and store as Strings
            freqTableFile = args[0];    //freqtable.txt
            //clearTextFile = args[1];    //cleartext.txt
            //encodedTextFile = args[2];  //encodedtext.txt
            outputFile = args[3];       //Output.txt
            
            //Read in the Frequency table
            FT = new FreqTable(FreqReader(freqTableFile));   
            
            //Make the Huffman encoding tree
            Huff = new HuffTree(FT);
            
            //Read in encoded and decoded text
            fileContents = inputReader(args);
            
            //Encode string
            Coder = new EncodeDecode(Huff, fileContents[0],fileContents[1],Met);
            
            //Write to output
            outputInfo(Huff,Coder,outputFile);
        } 
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, 
                    null, ex);
            throw ex;
        }
    }
    
    /**
     * This function reads the text of the frequency table
     * @param filePath is the filepath to the frequency table
     * @return the contents of each file in a string array
     */
    private static String[] FreqReader(String filePath) throws IOException
    {
        
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String [] temp = new String[26]; 
        String read;
        
        int count = 0;
        
        while((read = br.readLine()) != null)
        {
            temp[count++] = read.replaceAll(" ", "");
                    }
        return temp;
    }
    /**
     * This function reads in input from text files containing encoded and 
     * decoded strings as args[1] and args[2]
     * @param args are the command line arguments
     * @return a Queue array containing the encoded and decoded strings
     * @throws IOException if the file cannot be found
     */
    private static Queue[] inputReader(String[] args)
            throws IOException
    {
        FileReader fr;
        BufferedReader br;
        Queue[] Texts = new Queue[2];
        String text = null;
        
        
        for(int i = 1; i <= args.length-2; i++)
        {
            fr  = new FileReader(args[i]);
            br = new BufferedReader(fr);
            Texts[i-1] = new Queue();
            while((text = br.readLine()) != null)
            {
                //System.out.println("MIR\t"+text);
                //System.out.println("IR\t" + text);
                Texts[i-1].push(text.toUpperCase());
            }
            
            //System.out.println(Texts[i-1].getQueue());
        }
        return Texts;
    }
    /**
     * This function writes all encoded and decoded strings to output
     * @param Huff 
     * @param filepath is the output text file path
     */
    private static void outputInfo(HuffTree Huff,EncodeDecode ED, String filepath) 
            throws IOException
    {
        String Explainer;
        //Set up file
        FileWriter fr = new FileWriter(filepath);
        //Print encoded strings
        try (BufferedWriter bw = new BufferedWriter(fr)) {
            //Print encoded strings
            Explainer  = "\nEncoded to decoded strings from Encoded.txt\n";
            bw.write(Explainer + ED.getEncodedClear());
            //print decoded strings
            Explainer = "\nDecoded to encoded strings from clearText.txt\n";
            bw.write(Explainer + ED.getClearEnc());
            //print preorder traversal
            Explainer = "\nThis is the preorder traversal for the tree.\n"
                    + "Each row is a node, along with its frequency\n\n";
            bw.write(Explainer + ED.getTraverse());
            
            //Print all node codes
            Explainer = "\n\nAs an enhancement, "
                    + "here are all the codes for the tree.\n\n";
            bw.write(Explainer + Huff.getEncoding());
            
            //Write metricx
            Explainer = "\n\nAs an enhancement, "
                    + "here are the metrics for each conversion.\n\n";
            bw.write(Explainer + ED.getMetrics());
            
            //Print statistics
        }
    }
    
}
