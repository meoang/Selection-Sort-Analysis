/*Austin Johnson
 * CMSC 451 6380
 * SortInterface
 */
public interface SortInterface {
	
	public int getCount();
	public long getTime();
		
	public void recursiveSort(int[] list);
	public void iterativeSort(int[] list);

}
