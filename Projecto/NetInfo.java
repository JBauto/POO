

import java.util.HashMap;

public class NetInfo {

	protected HashMap<Integer,Object> network_data;
	protected FileIO io;
    int number_var;
	
	protected NetInfo(){
		network_data = new HashMap<Integer,Object>();
		io = new FileIO();
		number_var = -1;
	}
	
}
