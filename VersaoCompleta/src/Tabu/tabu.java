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
		tabu_list.put(2, null);
		tabu_list.put(3, null);
		retries = x;
	}
	
	public boolean addTabu(int op, int i, int j){
		List<int[]> op_list = tabu_list.get(op);
		int[] v = {i,j,retries};
		int[] v_r = {j,i,retries};
		int k = -1;
		int added = 0;
		
		if(op_list == null && op != 3){
			List<int[]> list = new ArrayList<int[]>();
			list.add(v);
			tabu_list.put(op, list);
			return true; // fez add
		}
		if(op_list == null && op == 3){
			List<int[]> list = new ArrayList<int[]>();
			list.add(v_r);
			tabu_list.put(op, list);
			return true;
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
			if(Arrays.equals(v_r, values) && op ==3 ){
				values[2] = retries;
				op_list.set(k, v_r);
				tabu_list.put(op, op_list);
				added = 1;
				break;
			}
		}
		if(added == 0){
			if(op == 3){
				op_list.add(v_r);
				tabu_list.put(op, op_list);
			}else{
				op_list.add(v);
				tabu_list.put(op, op_list);
			}
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
		int[] vector_r = new int[3]; 
		int k = -1;
		
		vector[0] = i;
		vector[1] = j;
		
		vector_r[0] = j;
		vector_r[1] = i;

		if(op_list == null)
			return false; // pode tentar a operação
		
		for(Iterator<int[]>it = op_list.iterator(); it.hasNext();){
			int[] values = it.next();
			k++;
			if(op == 1 || op == 2){
				vector[2] = values[2];
				if(Arrays.equals(values, vector)){
					if(values[2]!=0){
						values[2]--;
						op_list.set(k, values);
						return true; // nao tenta a operação
					}else{
						values[2] = retries_intra;
						op_list.set(k,values);
						return false; // pode tentar a operação
					}
				}else{
					continue;
				}
			}else{
				vector_r[2] = values[2];
				if(Arrays.equals(values, vector_r)){
					if(values[2]!=0){
						values[2]--;
						op_list.set(k, values);
						return true; // nao tenta a operação
					}else{
						values[2] = retries_intra;
						op_list.set(k,values);
						return false; // pode tentar a operação
					}
				}else{
					continue;
				}
			}
			
		}
		
		return false; // pode tentar a operação
	}
	
	
}
