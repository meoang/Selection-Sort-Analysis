/*Austin Johnson
 * CMSC 451 6380
 * MySelectionSort
 */
public class MySelectionSort implements SortInterface{
	
	//instance variables
	private int count; //count of critical operations after a sort
	private long time; //amount of time it took after a sort
	
	//constructor
	public MySelectionSort(){
		
		count = 0;
		time = 0;
		
	}
	
	//getters
	public int getCount(){
		
		return count;
		
	}
	
	public long getTime(){
		
		return time;
		
	}
	
	//Recursively sorts the passed array with Selection Sort
	//Generates the Critical Operation count and run time
	//Checks that passed array was sorted
	public void recursiveSort(int[] array){
		
		//reset count
		count = 0;
		
		//start time
		long startTime = System.nanoTime();
		
		//recursive selection sort
	    selectionSortRecursive(array, 0);
	    
	    //end time
	    long endTime = System.nanoTime();
	    
	    //calculate time passed
	    time = (endTime - startTime);
	    
	    //check that data is sorted, throw exception if it is not
	    try {
			checkSort(array);
		} catch (UnsortedException e) {
			e.printStackTrace();
		}
		
	}
	
	//private method, runs the actual recursive selectionSort
	//Critical operations are: each minimum comparison and each swap
	//original sort retrieved from http://www.cs.kzoo.edu/cs210/Labs/Recursion/recursiveSelSort.html
	private void selectionSortRecursive(int[]array, int startIndex){
		
		//base case
		if (startIndex >= array.length - 1)
	        return;
		
		//initialize minimum
	    int minIndex = startIndex;
	    
	    //find minimum
	    for (int index = startIndex + 1; index < array.length; index++){
	    	
	    	count++;
	    	
	        if (array[index] < array[minIndex])
	            minIndex = index;
	        
	    }
	    
	    //swap minimum
	    count++;
	    
	    int temp = array[startIndex];
	    array[startIndex] = array[minIndex];
	    array[minIndex] = temp;
	    
	    //recursive call
	    selectionSortRecursive(array, startIndex + 1);	
		
	}
	
	//Iteratively sorts the passed array with Selection Sort
	//Generates the Critical Operation count and run time
	//Checks that passed array was sorted
	public void iterativeSort(int[] array){
		
		//reset count
		count = 0;
		
		//start time
		long startTime = System.nanoTime();
		
		//iterative selection sort
	    selectionSortIterative(array);
	    
	    //end time
	    long endTime = System.nanoTime();
	    
	    //calculate time passed
	    time = (endTime - startTime);
	    
	    //check that data is sorted, throw exception if it is not
	    try {
			checkSort(array);
		} catch (UnsortedException e) {
			e.printStackTrace();
		}
		
	}
	
	//private method, runs the actual iterative selectionSort
	//Critical operations are: each minimum comparison and each swap
	//original sort retrieved from http://www.codingeek.com/algorithms/selection-sort-algorithm-and-its-implementation/
	private void selectionSortIterative(int[] array){
		
		for (int i = 0; i < array.length - 1; i++) {
        	
            int index_min = i;
            
            //Search for the minimum in unsorted array
            for (int j = i + 1; j < array.length; j++) {
            	
            	count++;
            	
                if (array[j] < array[index_min])
                    index_min = j;         
                
            }
            
            //Swap minimum element with element at index i
            count++;
            
            int smallerNumber = array[index_min];
            array[index_min] = array[i];
            array[i] = smallerNumber;
            
        }        
		
	}
	
	//helper method to check if passed data is sorted
	//throws an exception if it is not, does nothing if it is sorted
	private void checkSort(int[] array) throws UnsortedException{
		
		int previous = 0;
		
		for (int i = 0; i < array.length; i++){
						
			if (array[i] < previous)
				throw new UnsortedException("The array was not sorted.");
			
			previous = array[i];
			
		}
		
	}

}
