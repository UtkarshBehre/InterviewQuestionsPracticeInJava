package popularQuestionSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Given a list of tickets, find itinerary in order using the given list.
 * It may be assumed that the input list of tickets is not cyclic and 
 * there is one ticket from every city except final destination.
 * Runtime: O(n)
 * @author Utkarsh
 *
 */
public class ItineraryListTickets {
	public static void main(String[] args) {
		Map<String, String>  map = new HashMap<String, String>();
		map.put("Chennai","Banglore");
		map.put("Bombay","Delhi");
		map.put("Goa","Chennai");
		map.put("Delhi","Goa");
		printItinerary(map);
	}
	
	/**
	 * prints the itinerary using hash map and making a reverse map 
	 * to find the start point
	 * Runtime: O(n)
	 */
	public static void printItinerary(Map<String, String> map) {
		String start = "";
		Map<String,String> reverseMap = new HashMap<String, String>();
		for(Entry<String, String> entry : map.entrySet()) 
			reverseMap.put(entry.getValue(),entry.getKey());
		for(String s : map.keySet()) {
			if(!reverseMap.containsKey(s))
				start = s;
		}
//      below approach of finding start looks like O(n) but its O(n^2)
//		for(String s : map.keySet() ) { 
//			if(!map.containsValue(s)) // containsValue() takes O(n)
//				start = s;
//		}
		while(map.get(start)!=null) {
			System.out.print(start + " -> " + map.get(start)+", ");
			start = map.get(start);
		}
	}
}
