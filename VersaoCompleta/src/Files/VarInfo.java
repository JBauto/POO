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
	
	public Integer getElement(Integer x){
		return var_data.get(x);
	}
		
	@Override
	public String toString() {
		return "VarInfo [var_data=" + var_data + ", size=" + size + ", time="
				+ time + ", r=" + r + "]";
	}

	public void setVarTime(Integer x){
		if(time == -1){
			time = x;
		}else{
			System.out.println("Can't change variable time once it is set.");
			System.exit(-1);			
		}		
	}
	
	public void VarSize(Integer x){
		if(r < x)
			r = x + 1;
	}
	
	public void setR(Integer x){
		r = x; 
	}
}
