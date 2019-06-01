package generalPracticeQuestionSet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Given two Linked Lists, create union and intersection lists that contain 
 * union and intersection of the elements present in the given lists. Order 
 * of elments in output lists doesn’t matter.
 * Runtime: O(n1+n2)
 * @author Utkarsh
 *
 */
public class UnionIntersectionLinkedLists {
	public static void main(String[] args) {
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		list1.add(10);
		list1.add(15);
		list1.add(4);
		list1.add(20);
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		list2.add(8);
		list2.add(4);
		list2.add(2);
		list2.add(10);
		
		LinkedList<Integer> union = new LinkedList<Integer>();
		LinkedList<Integer> intersection = new LinkedList<Integer>();
		fillUnionIntersectionLists(list1, list2, union, intersection);
		System.out.println("Union List: " +union);
		System.out.println("Intersection list: " + intersection);

	}
	
	/**
	 * fills union and intersection lists for the 2 given lists
	 * first, fills elements from list1 to a hashset and union list, 
	 * and then uses the set to find if any element from list2 is present 
	 * and fill appropriate list with each element
	 * Runtime: O(n1+n2)
	 */
	public static void fillUnionIntersectionLists(LinkedList<Integer> list1, 
	LinkedList<Integer> list2, LinkedList<Integer> union, LinkedList<Integer> intersection) {
		Iterator<Integer> itr1 = list1.listIterator();
		Iterator<Integer> itr2 = list2.iterator();
		HashSet<Integer> set = new HashSet<Integer>();
		// add everything from first list to hashset and union list
		while(itr1.hasNext()) {
			int num = itr1.next();
			union.addFirst(num);
			set.add(num);
		}
		// add everything from second list to intersection if already present
		// or to union if not present
		while(itr2.hasNext()) {
			int num = itr2.next();
			if(set.contains(num))
				intersection.addFirst(num);
			else
				union.addFirst(num);
		}
	}
}
