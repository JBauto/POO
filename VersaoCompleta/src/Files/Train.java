package Files;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class Train extends NetInfo{

	private int time_samples;
	int number_lines;
		
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
	
	public void printPairedData(){
		int i,j;
		for(i = 0; i<matrix_data.length;i++){
			for(j = 0; j<matrix_data[0].length; j++){
				System.out.print(matrix_data[i][j]);
			}	
			System.out.print("\n");
		}
	}
	
	public void printvectorR(){
		System.out.println(Arrays.toString(vectorR));
	}
	
	public void readTrain(String s) throws IOException{
		BufferedReader br;
		String line;
		
		br = io.openFile(s);
		number_lines = io.numberLine(s);
		line = io.readLine(br);
		
		this.setParameters(line);
		int n = 0;
		
		while ((line = io.readLine(br)) != null){
			String[] parts = line.split(",");
			ArrayList<Integer> lista = new ArrayList<Integer>();
			file_data.put(n,lista);
			for(int i = 0;i<parts.length;i++){
				lista.add(Integer.parseInt(parts[i]));
			}
			file_data.put(n, lista);
			n++;
		}
		System.out.println("Numero de linhas = " + number_lines);
		br.close();
		
		List<Integer> v = new ArrayList<Integer>();
		
		int i = 0;
		v = file_data.get(i);
		
		for(i = 0; i<time_samples*number_var;i+=number_var){		
			v = file_data.get(0);
			for(int j = 0; j < number_lines-1;j++){
				List<Integer> l = new ArrayList<Integer>();
				int tmp = 0;
				for(int k = 0; k<number_var*2;k++){
					if(i+number_var < v.size()){
						if(v.get(i+number_var)==null){
							tmp = 0;
							break;							
						}else{
							l.add(v.get(k+i));
							tmp = 1;
						}
					}
				
				}
				if(file_data.get(j+1)!=null){
					v = file_data.get(j+1);
				}
				if(tmp == 1){
					paired_data.add(l);
					graph_size++;
				}
			}
		}
		i = 0;
		this.createRvector(number_var);
		this.createMatrix(graph_size, number_var*2);
		for(Iterator<List<Integer>> it_lin = paired_data.iterator(); it_lin.hasNext(); ) {
		    List<Integer> item = it_lin.next();
			int j = 0;
		    for(Iterator<Integer> it_col = item.iterator(); it_col.hasNext();){
			    Integer t = it_col.next();
			    matrix_data[i][j] = t;
			    if(j < number_var) this.calculateR(t,j);
			    j++;
		    }
		    i++;
		}
	}
}
