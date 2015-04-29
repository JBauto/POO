

import java.util.ArrayList;
import java.util.List;

public class Variable_Information {

	List<Integer> var_data;
	private int size;
	private int time;
	
	public Variable_Information(){
		var_data = new ArrayList<Integer>();
		size = 0;
		time = -1;
	}
	
	public void addElement(Integer x) throws NoMapCreatedException{
		if(var_data == null){
			throw new NoMapCreatedException("Create a map before adding any value");
		}else{
			var_data.add(x);
			size++; 
		}
	}
	
	public void setVarTime(Integer x){
		if(time == -1){
			time = x;
		}else{
			System.out.println("Can't change variable time once it is set.");
			System.exit(-1);			
		}		
	}
}
