package Tabu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class tabu {

	public HashMap<Integer,List<int[]>> tabu_list;
	private int retries;
	private int retries_intra = 2;
	
	public tabu (int x){
		tabu_list =  new HashMap<Integer,List<int[]>>();
		tabu_list.put(1, null);
		retries = x;
	}
	
	public boolean addTabu(int op, int i, int j){
		List<int[]> op_list = tabu_list.get(op);
		int[] v = {i,j,retries};
		int k = -1;
		int added = 0;
		
		if(op_list == null && op != 3){
			List<int[]> list = new ArrayList<int[]>();
			list.add(v);
			tabu_list.put(op, list);
			return true; // fez add
		}		
		for(Iterator<int[]> it = op_list.iterator();it.hasNext();){
			int[] values = it.next();
			k++;
			if(Arrays.equals(v, values) && op !=3 ){
				values[2] = retries;
				op_list.set(k, v);
				tabu_list.put(op, op_list);
				added = 1;
				break;
			}
		}
		if(added == 0){
			op_list.add(v);
			tabu_list.put(op, op_list);
			return true;
		}
		return false; // nao fez add
		
	}
	
	@Override
	public String toString() {
		return "tabu [tabu_list=" + tabu_list + ", retries=" + retries + "]";
	}

	public boolean checkTabu(int op,int i,int j){
		List<int[]> op_list = tabu_list.get(op);
		int[] vector =  new int[3];
		int k = -1;
		
		vector[0] = i;
		vector[1] = j;


		if(op_list == null)
			return false; // pode tentar a opera��o
		
		for(Iterator<int[]>it = op_list.iterator(); it.hasNext();){
			int[] values = it.next();
			k++;
			vector[2] = values[2];
			if(Arrays.equals(values, vector)){
				if(values[2]!=0){
					values[2]--;
					op_list.set(k, values);
					//System.out.println("(i,j) = "+i+","+j+" ainda com "+values[2] +" tentativas");
					return true; // nao tenta a opera��o
				}else{
					it.remove();
					return false; // pode tentar a opera��o
				}
			}else{
				continue;
			}			
		}
		
		return false; // pode tentar a opera��o
	}
	
	
}
