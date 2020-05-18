/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This class implements quicksort 4 ways, insertion sort, and heapsort
 * @author stevl
 */
public class Sorter {
    
    int[]       ArrayInput;
    int         numAlgos = 5;   //This must be changed if additional algorithms are added
    
    /**
     * This function sets the input array for the sorter function
     * @param Q is a queue to be converted to an array
     */
    public void setInputArray(Queue Q)
    {
        this.ArrayInput = new int[Q.getNumElements()];
    }
    
    /**
     * 
     * @return the number of algorithms in the sorter class
     */
    public int getNumAlgos()
    {
        return this.numAlgos;
    }
    
    /**
     * This function implements quicksort down to size 1 as a stopping case
     * @param Arr is the array to be sorted
     * @param start is the starting value of the array
     * @param end is the ending value of the array
     */
    public void quickSort1(int[] Arr, int start, int end)
    {
        
        //handle base case
        if(start >= end)
            return;
        
        //select partition
        int part = getPartition(Arr, start, end);
        
        //recursive case for before partition
        quickSort1(Arr,start,part - 1);
        
        //recursive case for after partition
        quickSort1(Arr,part + 1,end);
    }
    
    
    /**
     * This function implements quicksort down to size 1 as a stopping case, using 
     * the median of three values as a partition
     * @param Arr is the array to be sorted
     * @param start is the starting value of the array
     * @param end is the ending value of the array
     */
    public void quickSortMedian(int[] Arr, int start, int end)
    {
        //handle base case
        if(start >= end)
            return;
        
        //select partition
        int part = getMedianPartition(Arr, start, end);
        
        //recursive case for before partition
        quickSort1(Arr,start,part - 1);
        
        //recursive case for after partition
        quickSort1(Arr,part + 1,end);
    }
    
    /**
     * This function implements quicksort down to size 50 as a stopping case,
     * then implements insertionsort on the array
     * @param Arr is the array to be sorted
     * @param start is the starting value of the array
     * @param end is the ending value of the array
     */
    public void quickSort50(int[] Arr, int start, int end)
    {
        
        //handle base case
        if(Arr.length==50)
        {
            insertionSort(Arr);
            return;
        }
        //select partition
        int part = getPartition(Arr, start, end);
        
        //recursive case for before partition
        quickSort1(Arr,start,part - 1);
        
        //recursive case for after partition
        quickSort1(Arr,part + 1,end);
    }
    
    /**
     * This function implements quicksort down to size 100 as a stopping case,
     * then implements insertionsort on the array
     * @param Arr is the array to be sorted
     * @param start is the starting value of the array
     * @param end is the ending value of the array
     */
    public void quickSort100(int[] Arr, int start, int end)
    {
        
        //handle base case
        if(Arr.length==100)
        {
            insertionSort(Arr);
            return;
        }
        //select partition
        int part = getPartition(Arr, start, end);
        
        //recursive case for before partition
        quickSort1(Arr,start,part - 1);
        
        //recursive case for after partition
        quickSort1(Arr,part + 1,end);
    }
    
    /**
     * This function gets the partition necessary to recursively do quicksort
     * @param Freq is the frequency table for the Strings
     * @param start is the starting array value
     * @param end is the ending array value
     * @return an integer for the next partition
     */
    private int getPartition(int[] Freq, int start, int end)
    {
        int pivot = Freq[start];
        
        //assign pointer to the start
        int low = start-1;
        for(int i = start; i < end; i++)
        {
            //iterate until finding a value greater than the pivot
            if(Freq[i] < pivot)
            {
                //increment the low pointer
                low++;
                
                //Swap item
                int temp = Freq[low];
                Freq[low] = Freq[i];
                Freq[i] = temp;
            }
            
        }
        
        //swap the low element with the pivot
        int temp = Freq[low + 1];
        Freq[low + 1] = Freq[end];
        Freq[end] = temp;
        
        return low + 1;
    }
    
    /**
     * This function gets the partition necessary to recursively do quicksort
     * @param Freq is the frequency table for the Strings
     * @param start is the starting array value
     * @param end is the ending array value
     * @return an integer for the next partition
     */
    private int getMedianPartition(int[] Freq, int start, int end)
    {
        int pivot = getPivot(Freq,start,end);
        
        //assign pointer to the start
        int low = start-1;
        for(int i = start; i < end; i++)
        {
            //iterate until finding a value greater than the pivot
            if(Freq[i] < pivot)
            {
                //increment the low pointer
                low++;
                
                //Swap item
                int temp = Freq[low];
                Freq[low] = Freq[i];
                Freq[i] = temp;
            }
            
        }
        
        //swap the low element with the pivot
        int temp = Freq[low + 1];
        Freq[low + 1] = Freq[end];
        Freq[end] = temp;
        
        return low + 1;
    }
    /**
     * This function finds the median pivot of three values
     * @param Freq the array from which we select a pivot
     * @param start the minimum array value
     * @param end the maximum array value
     * @return the median of three array values
     */
    private int getPivot(int[] Freq, int start, int end)
    {
        int pivot;
        int first = Freq[start];
        int last = Freq[end];
        int mid = Freq[(start + end)/2];
        if(first == last && first == mid)
        {
            return first;
        }
        if (first > last) {
            if (last > mid) {
              return last;
            } else if (first > mid) {
              return mid;
            } else {
              return last;
            }
        } 
        else {
            if (first > mid) {
              return first;
            } else if (last > mid) {
              return mid;
            } else {
              return last;
            }
        }
    }
    /**
     * This function implements insertion sort
     * @param arr is the array to be sorted
     */
    private static void insertionSort(int arr[]) 
    {   
        int key;
        int count;
        for (int i = 1; i < arr.length; i++) {  
            key = arr[i];  
            count = i-1;  
            while ( (count > -1) && ( arr [count] > key ) ) 
            {  
                arr [count+1] = arr [count];  
                count--;  
            }  
            arr[count+1] = key;  
        }  
        return;
    }  
    
    /**
     * This function implements heapsort on an int array
     * @param arr the array to be heaped, then sorted
     */
    public  void heapSort(int arr[]) 
    { 
        int n = arr.length; 
  
        // Build heap (rearrange array) 
        for (int i = arr.length / 2 - 1; i >= 0; i--) 
            ArrtoHeap(arr, arr.length, i); 
  
        // One by one extract an element from heap 
        for (int i=arr.length-1; i>0; i--) 
        { 
            // Move current root to end 
            int temp = arr[0]; 
            arr[0] = arr[i]; 
            arr[i] = temp; 
  
            // call max heapify on the reduced heap 
            ArrtoHeap(arr, i, 0); 
        } 
    } 
    
    /**
     * This function implements the heapify part of the heapsort algorithm
     * @param arr the array to be converted to a heap
     * @param n the length of the array to be heapified
     * @param i the halfway point of the array
     */
    private void ArrtoHeap(int arr[], int n, int i) 
    { 
        int largest = i; // Initialize largest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && arr[l] > arr[largest]) 
            largest = l; 
  
        // If right child is larger than largest so far 
        if (r < n && arr[r] > arr[largest]) 
            largest = r; 
  
        // If largest is not root 
        if (largest != i) 
        { 
            int swap = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = swap; 
  
            // Recursively heapify the affected sub-tree 
            ArrtoHeap(arr, n, largest); 
        } 
    } 
}
