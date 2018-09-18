import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RawLinked {
	private String[] myWords = {"apple", "banana", "guava"};
	private Random myRandom = new Random();
	
	public static class ListNode {
		String info;
		ListNode next;
		ListNode(String x){
			info = x;
		}
		ListNode(String x, ListNode node){
			info = x;
			next = node;
		}
	}
	
	public static ListNode reverse2(ListNode list) {
		if (list == null || list.next == null) {
			return list;
		}
		ListNode after = reverse2(list.next);
		ListNode temp = after;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = list;
		list.next = null;
		return after;
	}
	
	public static ListNode reverse(ListNode list) {
		if (list == null || list.next == null) {
			return list;
		}
		ListNode after = reverse(list.next);
		list.next.next = list;
		list.next = null;
		return after;
	}
	
	public  ListNode fill(int size) {
		ListNode dummy = new ListNode("",null);
		ListNode last = dummy;
		for(int k=0; k < size; k++) {
			int index = myRandom.nextInt(myWords.length);
			last	.next = new ListNode(myWords[index],null);
			last = last.next;
		}
		return dummy.next;
	}
	
	public ListNode removeAll(String target, ListNode list) {
		ListNode dummy = new ListNode("",list);
		ListNode first = dummy;
		while (dummy.next != null) {
			if (dummy.next.info.equals(target)) {
				dummy.next = dummy.next.next;
			}
			else {
				dummy = dummy.next;
			}
		}
		return first.next;
	}

	public static int frequency(String target,ListNode list) {
		int count = 0;
		while (list != null) {
			if (list.info.equals(target)) {
				count += 1;
			}
			list = list.next;
		}
		return count;
	}
	public static int size(ListNode list) {
		int count = 0;
		while (list != null) {
			count += 1;
			list = list.next;
		}
		return count;
	}
	public static void print(ListNode list) {
		while (list != null) {
			System.out.print(list.info+",");
			list = list.next;
		}
		System.out.println();
	}
	public double timeRemove(int repSize) {
		myRandom.setSeed(123456);
		ListNode llist = fill(repSize);
		double start = System.nanoTime();
		String target = llist.info;
		int presize = size(llist);
		int tsize = frequency(target,llist);
		llist = removeAll(target,llist);
		double end = System.nanoTime();
		int rsize = size(llist);
		if (rsize != presize-tsize) {
			System.out.printf("error on remove %s %d times %d %d\n", target,tsize,presize,rsize);
			print(llist);
		}
		return (end-start)/1e9;
	}
	
	public ListNode arrayToList(String[] list) {
		ListNode front = null;
		for(int k=list.length-1; k >= 0; k--) {
			front = new ListNode(list[k],front);
		}
		return front;
	}
	public static void main(String[] args) {
		//RawLinked remove = new RawLinked();
		//double ltime = remove.timeRemove(100000);
	
		//System.out.printf("raw time = %1.3f\n", ltime);
		
		RawLinked runner = new RawLinked();
		ListNode list = runner.arrayToList(new String[]{"a","b","c","d","e"});
		runner.print(list);
		list = runner.reverse(list);
		runner.print(list);
		list = runner.reverse2(list);
		runner.print(list);
	}
}
