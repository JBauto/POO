package Files;

import java.io.BufferedReader;
import java.io.IOException;

public class Test extends NetInfo {	
	
	public void readTest(String s) throws IOException{
		BufferedReader br;
		String line;
		String[] vars;
		
		br = io.openFile(s);
		line = io.readLine(br);
		int number_lines = io.numberLine(s);
		
		line = line.replaceAll("\\s+","");
		vars = line.split(","); 
		
		this.nodeNames(line);

		this.createTest(number_lines-1, vars.length);
		int i = 0;
		while ((line = io.readLine(br)) != null){
			line = line.replaceAll("\\s+","");
			String[] var = line.split(",");
			for(int j = 0; j<var.length;j++){
				matrix_test[i][j] = Integer.parseInt(var[j]);
			}
//			System.out.println(Arrays.toString(var));
			i++;
		}
	}
}
