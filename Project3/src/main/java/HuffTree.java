/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *This class builds a huffman encoding tree based on a given frequency table
 * of characters
 * @author stevl
 */
public class HuffTree {
    char[] chars;
    int[] freqs;
    String[] codes;
    Node root;
    String traverse;
    String encoding;
    
    
    /**
     * Construct the Huffman encoding tree using a binary tree and a frequency table
     * @param FT is the frequency table of characters to be used
     */
    HuffTree(FreqTable FT)
    {
        chars = FT.getCharArr();
        freqs = FT.getCountArr();
        codes = new String[chars.length];
        root = makeTreeV2(chars, freqs);
        traverse =  preOrderTraverse(root);
        encoding = fullEncoding(chars,root);
    }
    /**
     * 
     * @return a pointer to the root of the coded tree
     */
    public Node getRoot()
    {
        return this.root;
    }
    /**
     * 
     * @return the sorted character list
     */
    public char[] getChars()
    {
        return this.chars;
    }
    /**
     * 
     * @return the sorted code list
     */
    public String[] getCodes()
    {
        return this.codes;
    }
    /**
     * This function makes a huffman encoding tree, given a list of alphabet
     * characters and their associated frequency
     * @param letters is the list of alphabetical characters
     * @param freqs is the frequency of that character
     * @return a pointer to the root node of the tree
     */
    private Node makeTreeV2(char[] letters, int[] freqs)
    {
        PriorityQueue HuffQ = makeHuffQueue(letters, freqs);
        Node first, second, currNode = null;
        
        String data;
        int freq;
        //Iterate through the array, checking each node and adding it to the tree
        while(!HuffQ.isEmpty())
        {
            first = HuffQ.pop(true);
            //If there is only one element, return it as the root node
            if(HuffQ.isEmpty() )
            {
                return first;
            }
            second = HuffQ.pop(true);
            
            
            //new frequency will be the sum of the nodes' frequency
            freq = first.getFreq() + second.getFreq();
            data = first.getData() + second.getData();
            data = String.valueOf(this.quickSort(data.toCharArray(),0,data.length()-1));
            
            //handle frequency ties
            if(first.getFreq() == second.getFreq())
            {
                currNode = tieBreak(first,second,freq);
            }
            
            //place lower frequency in left child
            else if(first.getFreq() < second.getFreq())
            {
                currNode = new Node(data, freq, first,second);
            }
            else
            {
                currNode = new Node(data, freq, second,first);
            }
            
            HuffQ.push(currNode);
            //HuffQ.iterateQueue(); 
            
        }
        
        return currNode;
    }
    /**
     * Returns the left child as the 0th entry and the right child as the 
     * first entry. 
     * @param first node to compare
     * @param second node to compare. Order does not matter
     * @param freq Frequency with which the sum of the two nodes occur
     * @return An array of the two nodes
     */
    
    private Node tieBreak(Node first, Node second, int freq)
    {
        Node tie = null;
        String data = first.getData() + second.getData();
        data = String.valueOf(this.quickSort(data.toCharArray(),0,data.length()-1));
        
                //lower length is the left child
                if(first.getData().length() < second.getData().length())
                {
                    tie = new Node(data, freq, first,second );
                }
                else if(first.getData().length() > second.getData().length())
                {
                    tie = new Node(data, freq, second,first );
                }
                //String lengths are equal, so go by alphabet
                //all strings are alphabetized, so first character will suffice
                //the string
                else if(first.getData().charAt(0) < second.getData().charAt(0))
                {
                    tie = new Node(data, freq, first,second );
                }
                else
                {
                    tie = new Node(data, freq, second,first );
                }
                
        
        return tie;
    }
    /**
     * This function turns the char and frequency arrays into nodes for use in
     * the tree as a stack
     * @param letters is an array of alphabetical characters
     * @param freqs is an array of character frequencies
     * @return a node stack composed of letters and frequencies
     */
    private PriorityQueue makeHuffQueue(char[] letters, int[] freqs)
    {
        PriorityQueue temp = new PriorityQueue();
        int frequency;
        //TODO change to freqs.length
        for(int i = 0; i < freqs.length; i++)
        {
            frequency = freqs[i];
            temp.push(String.valueOf(letters[i]),freqs[i]);
        }
        //temp.emptyQueue();
        return temp;
    }
    /**
     * This function copies all but the last value of the given array into a new
     * array, thus truncating the previous array by 1
     * @param C is the given character array
     * @returns  C - 1
     */
    private char[] copyCh(char[] C)
    {
        char[] temp = new char[C.length-1];
        
        for(int i = 0; i < temp.length; i++)
        {
            temp[i] = C[i];
        }
        return temp;
    }
    
    /**
     * This function copies all but the last value of the given array into a new
     * array, thus truncating the previous array by 1
     * @param I is the given integer array
     * @returns  I - 1
     */
    private int[] copyInt(int[] I)
    {
        int[] temp = new int[I.length-1];
        
        for(int i = 0; i < temp.length; i++)
        {
            temp[i] = I[i];
        }
        return temp;
    }
    
    /**
     * This function performs a preorder traverse of a binary tree
     * @param Root is a pointer to the root node of the tree
     */
    private String preOrderTraverse(Node node)
    {
        String result = "";
        
        //handle base case
        if(node == null)
        {
            return "";
        }
        //Get root data
        result += String.format("%-27s | %-3s |%n",node.getData(),
                node.getFreq());
        
        //Get left subtree
        result += preOrderTraverse(node.left);
        
        //Get right subtree
        result += preOrderTraverse(node.right);
        
        return result;
    }
    
    /**
     * @return traverse filled by preorder, in this case
     */
    public String getTraverse()
    {
        return this.traverse;
    }
    
    /**
     * Creates an encoding scheme for each letter and saves it to encoding for
     * display to the user, and codes for use in encoding / decoding
     * @param letters array of letters to be encoded
     * @param node the root node
     * @return the full formatted string which can be printed for the user
     */
    private String fullEncoding(char[] letters, Node node)
    {
        String fullCode = "";
        String letterCode = "";
        //compare each character to the nodes in the tree
        for(int i = letters.length-1; i >= 0; i--)
        {
            letterCode = letterEncoding(letters[i],node);
            fullCode += String.format("%-2s | %-27s |%n",letters[i],
                letterCode);
            this.codes[i] = letterCode;
        }
        
        return fullCode;
    }
    
    /**
     * Find the encoding for a single letter by traversing the tree
     * @param letter the letter to be encoded
     * @param node the root node
     * @return returns the code for the given letter
     */
    private String letterEncoding(char letter, Node node)
    {
        String code = "";
        Node currNode = node;
        //continue descending down the tree while the currnode is not a leaf
        while(currNode.getData().length() != 1)
        {
            if(currNode.left.getData().contains(Character.toString(letter))
                    )
            {
                //System.out.println("Currdata\t" + currNode.left.getData());
                code += '0';
                currNode = currNode.left;
            }
            else
            {
                code += '1';
                currNode = currNode.right;
            }
            //System.out.println("Currdata\t\t" + currNode.getData() + "\t" + code);
            
            
            
        }
        
        return code;
    }
    /**
     * 
     * @return the encoding scheme for the binary tree
     */
    public String getEncoding()
    {
        return this.encoding;
    }
    
    /**
     * Implements quicksort on a string
     * @param Freq is the Frequency table of alphabet characters
     * @param start is the starting index
     * @param end is the ending index
     */
    private char[] quickSort(char[] S, int start, int end)
    {
        //handle base case
        if(start >= end)
            return S;
        
        //select partition
        int part = getPartition(S, start, end);
        
        //recursive case for before partition
        quickSort(S,start,part - 1);
        
        //recursive case for after partition
        quickSort(S,part + 1,end);
        
        return S;
    }
    
    /**
     * This function gets the partition necessary to recursively do quicksort
     * @param Freq is the frequency table for the Strings
     * @param start is the starting array value
     * @param end is the ending array value
     * @return an integer for the next partition
     */
    private int getPartition(char[] chars, int start, int end)
    {
        int pivot = intVal(chars[end]);
        
        //assign pointer to the start
        int low = start-1;
        for(int i = start; i < end; i++)
        {
            //iterate until finding a value greater than the pivot
            if(intVal(chars[i]) < pivot)
            {
                //increment the low pointer
                low++;
                
                //Swap item
                char temp = chars[low];
                chars[low] = chars[i];
                chars[i] = temp;
            }
            
        }
        
        //swap the low element with the pivot
        char temp = chars[low + 1];
        chars[low + 1] = chars[end];
        chars[end] = temp;
        
        return low + 1;
    }
    
    /**
     * Helper function to get the value of a character
     * @param S a character to convert to integer
     * @return the integer value of a character
     */
    private int intVal(char S)
    {
        return Integer.valueOf(S);
    }
}
