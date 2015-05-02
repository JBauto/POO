

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NetInfo {

	protected HashMap<Integer,Object> network_data;
	public List<List<Integer>> graph_data;
	protected FileIO io;
    int number_var;
	
	protected NetInfo(){
		network_data = new HashMap<Integer,Object>(); //nome-tempo-valor
		graph_data = new ArrayList<List<Integer>>();
		io = new FileIO();
		number_var = -1;
	}
	
}
