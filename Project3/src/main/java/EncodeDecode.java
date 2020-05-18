/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This class encodes and decodes strings using a huffman tree which is constructed
 * in the huffTree class
 * letters is an array of alphabetical characters
 * codes is an array of the huffman encoding for each character
 * clear is a queue of clear text strings to encode
 * encoded is a queue of encoded text strings to decode
 * Encodedclear is a string of decoded text strings from encoded
 * @author stevl
 */
public class EncodeDecode {
    
    Metrics         Met;
    char[]          letters;
    String[]        codes;
    Node            root;
    Queue           clear;
    Queue           encoded;
    String[][]      clearEnc;
    String[][]      Encodedclear;
    String          traverse;
    
    /**
     * Constructor to encode or decode the queues
     * @param HT This is a huffman tree class containing a complete huffman tree
     * @param unEncoded Is the queue of unencoded strings
     * @param encoded is the queue of encoded strings
     */
    EncodeDecode(HuffTree HT, Queue unEncoded, Queue encoded,Metrics met)
    {
        this.Met = met;
        this.letters = HT.getChars();
        this.codes = HT.getCodes();
        this.root = HT.getRoot();
        this.clear = unEncoded;
        this.encoded = encoded;
        this.clearEnc = encodeQueue(this.clear);
        this.Encodedclear = decodeQueue(this.encoded);
        this.traverse = HT.getTraverse();       
        
    }
    /**
     * 
     * @return the preorder traverse from the Huffman encoding tree
     */
    public String getTraverse()
    {
        return this.traverse;
    }
    
    /**
     * Accepts a string, and encodes each character sequentially
     * @param S is the string to be encoded
     * @return Returns the binary sequence of encoded values
     */
    public String encodeString(String S)
    {
        String coded = "";
        char Letter;
        Node currNode = null;
        //System.out.println(S);
        //Encode each letter
        for(int i = 0; i < S.length(); i++)
        {
            Letter = S.toUpperCase().charAt(i);
            
            //Pass spaces
            if(!((Letter >= 'A' && Letter <= 'Z')))
            {
                coded += Letter;
            }
            else
            {
                currNode = this.root;
                while(currNode.right != null && currNode.left != null)
                {   //Search the tree
                    if(currNode.left.getData().indexOf(Letter) >= 0)
                    {
                        coded += '0';
                        currNode = currNode.left;
                    }
                    else
                    {
                        coded += '1';
                        currNode = currNode.right;
                    }
                }
                
                /**
                //Find the correct code for the letter, sorted by frequency
                for(int x = letters.length-1; x >= 0; x--)
                {
                    if(Letter == letters[x])
                    {
                        coded += this.codes[x];
                    }
                }
                */
            }
        }
        return coded;
    }
    /**
     * Takes a queue of strings, and encodes them all
     * @param Clear is the Queue to be encoded
     * @return is the string array of clear to encoded strings
     */
    private String[][] encodeQueue(Queue Clear)
    {
        String[][] Coded = new String[Clear.getNumElements()][2];
        String temp;
        Long startTime;
        Long endTime;
        int i = 0;
        //Time each encoding
        while((temp = Clear.pop()) != null)
        {
            Coded[i][0] = temp;
            startTime = System.nanoTime();
            Coded[i++][1] = encodeString(temp);
            endTime = System.nanoTime() - startTime;
            
            //System.out.println(RH.getSteps());
            
            //Store
            this.Met.storeMetric(endTime, "encoded");
        }
        
        return Coded;
    }

    /**
     * 
     * @return The encoded string array as a single string separated by '\n'
     */
    public String getClearEnc()
    {
        String temp = "";
        for(int i = 0; i < clearEnc.length; i++)
        {
            temp += clearEnc[i][0] + '\n';
            temp += clearEnc[i][1] + "\n\n";
        }
        return temp;
    }
    
    /**
     * 
     * @return The decoded string array as a single string separated by '\n'
     */
    public String getEncodedClear()
    {
        String temp = "";
        for(int i = 0; i < this.Encodedclear.length; i++)
        {
            temp += Encodedclear[i][0] + '\n';
            temp += Encodedclear[i][1] + "\n\n";
        }
        return temp;
    }
    /**
     * This function decodes a string which contains a binary encoded string
     * @param S the string to decode
     * @return the decoded string
     */
    public String decodeString(String S){
    
        String decoded = "";
        Node currNode = this.root;
        
        for(int i = 0; i < S.length(); i++)
        {
            
            //Search the tree
            if(S.charAt(i) == '0')
            {
                currNode = currNode.left;
            }
            else
            {
                currNode = currNode.right;
            }
            //Reach a leaf node and append the character
            if(currNode.right == null && currNode.left == null)
            {
                decoded += currNode.getData();
                currNode = this.root;
            }
        }
        
        return decoded;
    }
    /**
     * Decodes all encoded strings in a queue
     * @param Q is the queue containing the encoded strings
     * @return [0][i] is the encoded string [1][i] is the decoded string
     */
    private String[][] decodeQueue(Queue Q)
    {
        String[][] temp = new String[Q.getNumElements()][2];
        int i = 0;
        String S;
        long startTime;
        long endTime;
        while(!Q.isEmpty())
        {
            S = Q.pop();
            temp[i][0] = S;
            startTime = System.nanoTime();
            temp[i++][1] = decodeString(S);
            endTime = System.nanoTime() - startTime;
            
            //Store metrics
            this.Met.storeMetric(endTime, "decoded");
        }
        return temp;
    }
    
    /**
     * Prints individual, total, and average runtimes for encoding and decoding
     * huffman encoded strings. If milliseconds > 1000000000, divide by the same
     * to get seconds
     * @return all test results to a formatted string for printing
     */
    public String getMetrics()
    {
        var totalmet = Met.getTotalRuntime();
        var avemet = Met.getAverageRuntime();
        String temp = "Time measurements for each input\n";
        temp += String.format("%n%-15s %n %-15s %n%n","Clear String\\",
                "Time");
        boolean twoSecondExceeded = true;
        
        var allMetrics = Met.getAllMetrics();
        //Print clear metrics
        for(int i = 0; i < this.clearEnc.length; i++)
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
            temp += String.format("%-15d | %-50s", 
                                   allMetrics[0][i],this.clearEnc[i][0]);
            temp += '\n';
            
        }
        temp += "\n\nNow moving to strings which were decoded\n\n";
        //print encoded metrics
        for(int i = 0; i < this.Encodedclear.length; i++)
        {
            
            temp += String.format("%-15d | %-50s", 
                                   allMetrics[1][i],this.Encodedclear[i][1]);
            temp += '\n';
        }
        
        temp += "\n\nSummary measurements:\n";
        temp += String.format("%-15s | %-15s | %-15s |%n","Statistic",
                "Encoding","Decoding");
        temp += String.format("%-15s | %-15d | %-15d |%n",
                              "Total ",totalmet[0],totalmet[1]);
        
        temp += String.format("%-15s | %-15d | %-15d |%n",
                              "Average ",avemet[0],avemet[1]);
        
        return temp;
    }
}
