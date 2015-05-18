package Tabu;

import java.util.HashMap;

public class tabu_mat implements tabu{

	private HashMap<Integer,Integer> tabu_list;
	/** Receives a HashCode. Verifies if the hashMap exist, and if doesn't inserts the received hashCode
	 * into the hashMap as a key.
	 * 
	 * @param i hashCode to add to the hashMap
	 * @param j unused parameter
	 * @param m unused parameter
	 */
	
	public tabu_mat (){
		tabu_list =  new HashMap<Integer,Integer>();
		tabu_list.put(null, null);
	}
	
	public boolean addTabu(int i, int j, int m) {
		if(tabu_list ==  null){
			tabu_list = new HashMap<Integer,Integer>();
			tabu_list.put(i, 1);
			return true;
		}
		if(tabu_list.containsKey(i))
			return false;
		else{
			tabu_list.put(i, 1);
			return true;
		}
	}

	/** hashCode to compare with the keys in the hashMap
	 * 
	 * @param i hashCode to compare to the key in hashMap
	 * @param j unused parameter
	 * @param m unused parameter
	 * @return true if the structure wasn't previously visited otherwise performs the operation
	 */
	public boolean checkTabu(int i, int j, int m) {
		if(tabu_list ==  null){
			tabu_list = new HashMap<Integer,Integer>();
			return true;
		}
		if(tabu_list.containsKey(i))
			return false;
		else
			return true;
	}

}
