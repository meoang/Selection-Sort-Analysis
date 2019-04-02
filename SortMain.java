/*Austin Johnson
 * CMSC 451 6380
 * SortMain
 */
public class SortMain {
		
	public static void main(String args[]){
		
		int[] sizes = {5, 10, 100, 200, 500, 1000, 2000, 3000, 4000, 5000};
		
		BenchmarkSorts sorts = new BenchmarkSorts(sizes);
		
		sorts.runSorts();
		sorts.displayReports();
		
	}

}
