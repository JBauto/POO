import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;



public class Train extends NetInfo{

	private int time_samples;
	
	public void setParameters(String line){
		int i = 0;
		String[] parts;
		String[] var_name;
		String tmp = null;
		
		parts = line.split(",");
		time_samples = parts.length;
		for(i=0;i<parts.length;i++){
			var_name = parts[i].split("_");
			if(i == 0){
				tmp = var_name[0];
			}else{
				if(tmp.equals(var_name[0])){
					number_var = i;
					break;
				}else{
					
				}
			}
		}
		time_samples = time_samples / number_var;
		System.out.println("Numero de variaveis = "+ number_var);
		System.out.println("Numero de amostras temporais = "+ time_samples);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void readTrain(String s) throws IOException{
		BufferedReader br;
		String line;
		
		br = io.openFile(s);		
		line = io.readLine(br);		
		this.setParameters(line);
		
		while ((line = io.readLine(br)) != null) {
			String[] parts = line.split(",");
			for(int i = 0;i<number_var;i++)	{					
				int j,k=0;

				for(j=i; j<parts.length ; j+=number_var){
					parts = line.split(",");
					for(i=0;i<number_var;i++){
						HashMap<Integer,VarInfo> variable_map;
						if(network_data.get(i) == null){
							variable_map = new HashMap<Integer,VarInfo>();
						}else{ 
							variable_map = (HashMap<Integer, VarInfo>) network_data.get(i);
						}				
						
						for(j=i, k=0 ; j<parts.length ; j+=number_var,k++){
							VarInfo variable_data;
							if(!((HashMap<Integer, VarInfo>) variable_map).containsKey(k)){
								variable_data = new VarInfo();  
							}else{
								variable_data = ((HashMap<Integer, VarInfo>) variable_map).get(k);
							}
							variable_data.addElement(Integer.parseInt(parts[j]));
							variable_data.VarSize(Integer.parseInt(parts[j]));
							variable_map.put(k, variable_data);
						}
						network_data.put(i, variable_map);				
					}
			
				}
				
			}
		}
		br.close();
	}
}
