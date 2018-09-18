import java.util.*;

/**
 * 
 * @author ola
 * @version 1.0, September 2011
 * @version 2.0, September 2016
 * @version 3.0, September 2018
 */

public class ListSplicer {

	private static final String GOODBYE = "goodbye";
	private static final int SEED = 123456;

	/**
	 * Add n occurrences of a string to list
	 * @param list willbe modified as a result of adding strings
	 * @param n the number of strings to add
	 * @return the time for the adds to take place
	 */
	public double addFront(List<String> list, int n) {
		double start = System.nanoTime();
		for (int k = 0; k < n; k++) {
			list.add(0, GOODBYE);
		}
		double end = System.nanoTime();
		return (end - start) / 1e9;
	}
	
	public double randomAccess(List<String> list) {
		ArrayList<Integer> nums = new ArrayList<>();
		for(int k=0; k < list.size(); k += 1) {
			nums.add(k);
		}
		Random rand = new Random(SEED);
		Collections.shuffle(nums,rand);
		double start = System.nanoTime();
		for(int index : nums) {
			String dummy = list.get(index);
			String shadow = dummy;
			if (shadow == dummy) continue;
		}
		double end = System.nanoTime();
		return (end-start) / 1e9;
	}

	/**
	 * Remove the first element until the list is empty
	 * @param list being modified
	 * @return time for removal to take place
	 */
	public double removeFirst(List<String> list) {
		double start = System.nanoTime();
		while (list.size() != 1) {
			list.remove(0);
		}
		double end = System.nanoTime();
		return (end - start) / 1e9;
	}
	/**
	 * Move first to last n times, so list isn't changed after n moves
	 * for an n-element list
	 * @param list being modified
	 * @return time for removal to take place
	 */
	public double moveFirstToLast(List<String> list) {
		double start = System.nanoTime();
		for(int k=0; k < list.size(); k += 1) {
			String first = list.remove(0);
			list.add(first);
		}
		double end = System.nanoTime();
		return (end - start) / 1e9;
	}

	/**
	 * Remove middle element until list is empty
	 * @param list being modified
	 * @return time for removal to take place
	 */
	public double removeMiddleIndex(List<String> list) {
		double start = System.nanoTime();
		while (list.size() != 1) {
			list.remove(list.size() / 2);
		}
		double end = System.nanoTime();
		return (end - start) / 1e9;
	}

	/**
	 * Use iterator to find string GOODBYE and remove the string after
	 * GOODBYE until only GOODBYE left, then remove that string
	 * @param list being modified
	 * @return time for removal to take place
	 */
	public double removeMiddleValueIterate(List<String> list) {
		double start = System.nanoTime();
		while (list.size() != 1) {
			Iterator<String> iter = list.iterator();
			while (iter.hasNext()) {
				String s = iter.next();
				if (s.equals(GOODBYE)) {
					if (iter.hasNext()) {
						iter.next();
						iter.remove();
					}
					break;
				}
			}
			if (list.size() > 1) {
				list.remove(0);
			}
		}
		double end = System.nanoTime();
		return (end - start) / 1e9;
	}

	/**
	 * Return a list of n elements, middle is GOODBYE, all other strings are hello.
	 * list should be empty when method starts
	 * @param list being modified and returned
	 * @param size is number of elements being added
	 * @return the list passed in, with size entries
	 */
	public List<String> create(List<String> list, int size) {

		for (int k = 0; k < size; k++) {
			if (k == size / 2) {
				list.add(new String(GOODBYE));
			} else {
				list.add(new String("hello"));
			}
		}
		return list;
	}

	public double duplicate(List<String> list) {
		double start = System.nanoTime();
		ListIterator<String> iter = list.listIterator();
		while (iter.hasNext()){
			String s = iter.next();
			iter.add(s);
		}
		double end = System.nanoTime();
		return (end-start)/1e9;
	}

	public double duplicate2(List<String> list) {
		double start = System.nanoTime();
		int originalSize = list.size();
		list.addAll(list);
		for(int k=originalSize-1; k >= 0; k -= 1){
			String current = list.get(k);
			list.set(2*k, current);
			list.set(2*k+1,current);
		}
		double end = System.nanoTime();
		return (end-start)/1e9;
	}

	public double reverse(List<String> list) {
		double start = System.nanoTime();
		for(int k=0; k < list.size(); k++) {
			String last = list.remove(list.size()-1);
			list.add(0,last);
		}
		double end = System.nanoTime();
		return (end-start)/1e9;
	}
	
	public double iterate(List<String> list) {
		double start = System.nanoTime();
		int total = 0;
		for(int k=0; k < list.size(); k++) {
			total += list.get(k).length();
		}
		double end = System.nanoTime();
		return (end-start)/1e9;
	}
	
	public double iterateEach(List<String> list) {
		double start = System.nanoTime();
		int total = 0;
		for(String s : list) {
			total += s.length();
		}
		double end = System.nanoTime();
		return (end-start)/1e9;
	}

	public static void main(String[] args) {
		ListSplicer splicer = new ListSplicer();
		int first = 10000;
		int last = 150000;
		int incr = 10000;
		
		first = 1000000;
		last = 20000000;
		incr = 1000000;
		for (int k = first; k <= last; k += incr) {
			List<String> linked = new LinkedList<String>();
			List<String> array = new ArrayList<String>();
		
			linked = splicer.create(linked, k);
			array  = splicer.create(array, k);
			List<String> lcopy = new LinkedList(linked);
			List<String> acopy = new ArrayList(array);
			
			//double ltime = splicer.removeFirst(linked);
			///double atime = splicer.removeFirst(array);
			
			//double ltime = splicer.randomAccess(linked);
			//double atime = splicer.randomAccess(array);
			
			//double ltime = splicer.moveFirstToLast(linked);
			//double atime = splicer.moveFirstToLast(array);
			//if (! linked.equals(lcopy)) System.out.print("link fail ");
			//if (! array.equals(acopy)) System.out.print("array fail ");
			

			//double ltime = splicer.removeMiddleIndex(splicer.create(linked,k));
			//double atime = splicer.removeMiddleIndex(splicer.create(array,k));

			//double ltime = splicer.duplicate2(splicer.create(linked, k));
			//double atime = splicer.duplicate2(splicer.create(array, k));
//			double ltime = splicer.reverse(linked);
//			double atime = splicer.reverse(array);
			double ltime = splicer.iterateEach(linked);
			double atime = splicer.iterateEach(array);
			System.out.printf("%d\t%2.4f\t%2.4f\n", k, ltime, atime);
		}

	}
}
