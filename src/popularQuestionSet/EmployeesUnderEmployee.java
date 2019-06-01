package popularQuestionSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Find number of Employees Under every Employee.
 * Given a dictionary that contains mapping of employee and his manager.
 * Write a function to get no of employees under each manager in the 
 * hierarchy not just their direct reports. It may be assumed that an employee 
 * directly reports to only one manager. In the above dictionary the root node/ceo 
 * is listed as reporting to himself.
 * Runtime: O(n)
 * Source: Microsoft Interview
 * @author Utkarsh
 *
 */
public class EmployeesUnderEmployee {
	public static void main(String[] args) {
		Map<String, String> dataSet = new HashMap<String, String>(); 
        dataSet.put("A", "C"); 
        dataSet.put("B", "C"); 
        dataSet.put("C", "F"); 
        dataSet.put("D", "E"); 
        dataSet.put("E", "F"); 
        dataSet.put("F", "F"); 
        printManagerEmployeesDynamic(dataSet);
	}
	
	/**
	 * dynamic approach that gives solution in O(n) time
	 * first make a map that stores every manager as key and all their direct reports
	 * as value
	 * then, loop through the given relation map and dynamically find and store employees
	 * count under that manager using memoization
	 * Runtime: O(n)
	 * @param relationMap = given employee: manager relation map
	 */
	public static void printManagerEmployeesDynamic(Map<String, String> relationMap) {
		Map<String, List<String>> directRepsMap = new HashMap<String, List<String>>();
		for(Map.Entry<String, String> entry: relationMap.entrySet()) {
			String emp = entry.getKey();
			String mgr = entry.getValue();
			if(!directRepsMap.containsKey(mgr)) {
				List<String> empList = new ArrayList<String>();
				empList.add(emp);
				directRepsMap.put(mgr, empList);
			} else {
				// for non CEO cases
				if(!emp.equals(mgr))
					directRepsMap.get(mgr).add(emp);
			}
		}
		Map<String, Integer> mgrRep = new HashMap<String, Integer>();
		for(Map.Entry<String, String> entry : relationMap.entrySet()) {
			System.out.println(entry.getKey()+" : "+ getEmpCount(directRepsMap, entry.getKey(), mgrRep));
		}
		
	}
	
	/**
	 * Dynamic recursive method to get reporting employees count for a manager
	 * Runtime: O(1)
	 * @param directRepsMap = manager to direct reportees map
	 * @param mgr = current manager to check employee count on
	 * @param mgrRep = dynamic memory as manager: count of reportees
	 * @return
	 */
	public static int getEmpCount(Map<String, List<String>> directRepsMap, String mgr, Map<String, Integer> mgrRep) {
		if(mgrRep.containsKey(mgr))
			return mgrRep.get(mgr);
		// if manager is not in keys of directRespMap means not a manager
		if(!directRepsMap.containsKey(mgr)) {
			mgrRep.put(mgr,0);
			return 0;
		}
		else { // otherwise we get the list of direct reportees and get count for each
			List<String> empList = directRepsMap.get(mgr);
			int count = 0;
			for(String emp: empList) {
				count+=getEmpCount(directRepsMap, emp, mgrRep)+1;
			}
			mgrRep.put(mgr,count);
			return count;
		}
	}
	
	/**
	 * basic solution using given map only
	 * logic maybe useful for the case of a new employee joinee
	 * climbs and updates manager of manager starting from employee
	 * would be bad for case where a -> b, b->c, c->d, d->e
	 * Runtime: O(n^2) 
	 * @param map
	 */
	public static void printManagerEmployees(Map<String, String> map) {
		Map<String, Integer> managersMap = new HashMap<String, Integer>();
		for(Map.Entry<String, String>  entry : map.entrySet()) {
			String emp = entry.getKey();
			String mgr = entry.getValue();
			if(!managersMap.containsKey(emp))
				managersMap.put(emp, 0);
			if(!managersMap.containsKey(mgr)) {
				managersMap.put(mgr, 0);
			} 
			while(map.containsKey(emp)) {
				mgr = map.get(emp);
				if(emp.equals(mgr)) 
					break;
				else
					managersMap.put(mgr, getEmployeeCount(managersMap,mgr)+1);
				emp = map.get(emp);
			}
		}
		
		for(Map.Entry<String, Integer> entry: managersMap.entrySet()) {
			System.out.println(entry.getKey()+" : "+ entry.getValue());
		}
	}
	
	public static Integer getEmployeeCount(Map<String, Integer> mgrMap, String mgr) {
		if(!mgrMap.containsKey(mgr)) {
			mgrMap.put(mgr, 0);
			return 0;
		} else {
			return mgrMap.get(mgr);
		}
	}
}
