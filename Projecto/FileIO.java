

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileIO {
	
	public static <T> void main(String [] args) throws IOException
	{
		int i;
		HashMap<Integer,HashMap<Integer,List<Integer>>> Train = new HashMap<Integer,HashMap<Integer,List<Integer>>>();
		File dir = new File(".");
		File fin = new File(dir.getCanonicalPath() + File.separator + "train-data.csv");
		readFileTrain(fin,Train);

	}
	
	@SuppressWarnings("unchecked")
	public static <T> HashMap<Integer, HashMap<Integer, List<Integer>>> readFileTrain(File fin,HashMap<Integer,HashMap<Integer,List<Integer>>> Train) throws IOException {
		// Construct BufferedReader from FileReader
		int i,j,k;
		Object obj = null;
		int number_var = 0;
		int number_time = 0;
		String[] parts = null;
		String tmp = null;
		String[] var_name = null;
		String line = null;
		HashMap<Integer,HashMap<Integer,List<Integer>>> map = new HashMap<Integer,HashMap<Integer,List<Integer>>>();
		List<Integer> variable_data = null;
		HashMap<Integer, List<Integer>> variable_map = null;
		
		BufferedReader br = new BufferedReader(new FileReader(fin));
	 
		line = br.readLine();
		parts = line.split(",");
		number_time = parts.length;
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
			Train.put(i, (HashMap<Integer, List<Integer>>) obj);
		}
		number_time = number_time / 3;
		System.out.println("Numero de variaveis = "+ number_var);
		System.out.println("Numero de amostras temporais = "+ number_time);
		
		while ((line = br.readLine()) != null) {
			//System.out.println("K = " + k );
			parts = line.split(",");
			for(i=0;i<number_var;i++){
				if(Train.get(i) == null){
					variable_map = new HashMap<Integer,List<Integer>>();
				}else{ 
					variable_map = Train.get(i);
				}
				
				
			
				
				for(j=i, k=0 ; j<parts.length ; j+=number_var,k++){
					if(!variable_map.containsKey(k)){
						variable_data = new ArrayList<Integer>();  
					}else{
						variable_data = variable_map.get(k);
					}
					variable_data.add(Integer.parseInt(parts[j]));
					variable_map.put(k, variable_data);
				}
				Train.put(i, variable_map);				
			}
			
		}
		/*for(Object b : Train.keySet()){
	
			System.out.println(Train.toString());
			
		}*/
		for(i=0;i<number_var;i++){
			System.out.println(Train.get(i).toString());
		}
		br.close();
		return map;
	}
}
