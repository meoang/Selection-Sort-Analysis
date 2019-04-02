/*Austin Johnson
 * CMSC 451 6380
 * BenchmarkSorts
 */
public class BenchmarkSorts {
	
	//recursive sort instance variables
	int[] recurCounts; //Average counts for each size of recursive sorts
	long[] recurTimes; //Average times for each size of recursive sorts
	int[] recurCountDeviations; //Standard deviations of count for each size of recursive sorts 
	int[] recurTimeDeviations; //Standard deviations of time for each size of recursive sorts 
	
	//iterative sort instance variables
	int[] iterCounts; //Average counts for each size of iterative sorts
	long[] iterTimes; //Average tiems for each size of iterative sorts
	int[] iterCountDeviations; //Standard deviations of count for each size of iterative sorts
	int[] iterTimeDeviations; //Standard deviations of time for each size of iterative sorts
	
	//other instance variable
	int[] sizes; //Array of the sizes of data sets to be created and sorted
	
	//constructor
	public BenchmarkSorts(int[] sizes){
		
		recurCounts = new int[sizes.length];
		recurTimes = new long[sizes.length];
		recurCountDeviations = new int[sizes.length];
		recurTimeDeviations = new int[sizes.length];
		
		iterCounts = new int[sizes.length];
		iterTimes = new long[sizes.length];
		iterCountDeviations = new int[sizes.length];
		iterTimeDeviations = new int[sizes.length];		
		
		this.sizes = sizes;
		
	}
	
	//Runs both sorts with a random set of integers 50 times based on sizes passed in constructor
	//calculates average counts, times, and standard deviations
	public void runSorts(){
		
		//loop through each size, create a random set of integers for that size,
		//and then run each sort 50 times.
		for (int i = 0; i < sizes.length; i++){
			
			//arrays to hold the counts and times for each of the 50 current runs
			int[] curRecurCounts = new int[50];
			long[] curRecurTimes = new long[50];
			
			int[] curIterCounts = new int[50];
			long[] curIterTimes = new long[50];
			
			//run each sort for the current size 50 times
			for (int t = 0; t < 50; t++){
				
				//integer sets to be used with this run
				int[] iterData = new int[sizes[i]];
				int[] recurData = new int[sizes[i]];
				
				//generate random integers between 0 and 1000
				//and fill the arrays to be identical
				for (int j = 0; j < sizes[i]; j++){
					
					int curRandom = (int)(Math.random() * 1000);
					
					iterData[j] = curRandom;
					recurData[j] = curRandom;
					
				}
								
				//run each sort for this run of 50
				MySelectionSort sorter = new MySelectionSort();
				
				//recursive sort
				//Collect the count and time for this run of 50
				sorter.recursiveSort(recurData);
				curRecurCounts[t] = sorter.getCount();
				curRecurTimes[t] = sorter.getTime();	
				
				//iterative sort
				//Collect the count and time for this run of 50
				sorter.iterativeSort(iterData);
				curIterCounts[t] = sorter.getCount();
				curIterTimes[t] = sorter.getTime();				
				
			}//end 50 sorts
			
			//sum up times and counts
			int sumRecurCounts = 0;
			long sumRecurTimes = 0;
			
			int sumIterCounts = 0;
			long sumIterTimes = 0;
			
			for (int a = 0; a < 50; a++){
				
				sumRecurCounts += curRecurCounts[a];
				sumRecurTimes += curRecurTimes[a];
				
				sumIterCounts += curIterCounts[a];
				sumIterTimes += curIterTimes[a];
				
			}
			
			//calculate averages
			recurCounts[i] = (sumRecurCounts / 50);
			recurTimes[i] = (sumRecurTimes / 50);
			
			iterCounts[i] = (sumIterCounts / 50);
			iterTimes[i] = (sumIterTimes / 50);
			
			//calculate Standard Deviations			
			double curDeviation = 0;
			
			//Recursive Count Deviation
			for (int d = 0; d < curRecurCounts.length; d++)				
				curDeviation += Math.pow(curRecurCounts[d] - recurCounts[i], 2);
				
			curDeviation = (curDeviation / 2);
			curDeviation = Math.sqrt(curDeviation);
			recurCountDeviations[i] = (int)curDeviation;
			
			//Recursive Time Deviation
			curDeviation = 0;
			
			for (int d = 0; d < curRecurTimes.length; d++)				
				curDeviation += Math.pow(curRecurTimes[d] - recurTimes[i], 2);
				
			curDeviation = (curDeviation / 2);
			curDeviation = Math.sqrt(curDeviation);
			recurTimeDeviations[i] = (int)curDeviation;
			
			//Iterative Count Deviation
			curDeviation = 0;
			
			for (int d = 0; d < curIterCounts.length; d++)				
				curDeviation += Math.pow(curIterCounts[d] - iterCounts[i], 2);
				
			curDeviation = (curDeviation / 2);
			curDeviation = Math.sqrt(curDeviation);
			iterCountDeviations[i] = (int)curDeviation;
			
			//Iterative Time Deviation
			curDeviation = 0;
			
			for (int d = 0; d < curIterTimes.length; d++)				
				curDeviation += Math.pow(curIterTimes[d] - iterTimes[i], 2);
				
			curDeviation = (curDeviation / 2);
			curDeviation = Math.sqrt(curDeviation);
			iterTimeDeviations[i] = (int)curDeviation;
			
		}//end loop through sizes
		
	}//end runSorts
	
	//displays collected data as a table to the console
	public void displayReports(){
			
		//headers
		System.out.format("%12s%12s%12s%12s%12s%12s%12s%12s%12s", "Data Size","Iterative","","","","Recursive","","","");
		System.out.println("");
		System.out.format("%12s%12s%12s%12s%12s%12s%12s%12s%12s", "","Average","Standard","Average","Standard","Average","Standard","Average","Standard");
		System.out.println("");
		System.out.format("%12s%12s%12s%12s%12s%12s%12s%12s%12s", "","Operation","Deviation","Execution","Deviation","Operation","Deviation","Execution","Deviation");
		System.out.println("");
		System.out.format("%12s%12s%12s%12s%12s%12s%12s%12s%12s", "","Count","of Count","Time","of Time","Count","of Count","Time","of Time");

		//data
		for (int i = 0; i < sizes.length; i++){
			
			System.out.println("");
			System.out.format("%12d%12d%12d%12d%12d%12d%12d%12d%12d", sizes[i],iterCounts[i],iterCountDeviations[i],iterTimes[i],iterTimeDeviations[i],recurCounts[i],recurCountDeviations[i],recurTimes[i],recurTimeDeviations[i]);
			
		}
		
	}//end displayReports

}
