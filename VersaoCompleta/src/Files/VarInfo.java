package Files;

import java.util.ArrayList;
import java.util.List;

public class VarInfo {

	List<Integer> var_data;
	int size;
	public  int time;
	private int r;
	
	public VarInfo(){
		var_data = new ArrayList<Integer>();
		size = 0;
		time = -1;
		r = -1;
	}
	
	public void addElement(Integer x) /*throws NoMapCreatedException*/{
		if(var_data == null){
			//throw new NoMapCreatedException("Create a map before adding any value");
		}else{
			var_data.add(x);
			size++; 
		}
	}
}
