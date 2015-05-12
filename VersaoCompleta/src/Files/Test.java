package Files;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Test extends NetInfo {	
	
	public void readTest(String s) throws IOException{
		BufferedReader br;
		String line;
		String[] vars;
		
		br = io.openFile(s);
		line = io.readLine(br);
		int number_lines = io.numberLine(s);
		vars = line.split(","); 

		this.createTest(number_lines, vars.length);
		int i = 0;
		while ((line = io.readLine(br)) != null){
			String[] var = line.split(",");
			for(int j = 0; j<var.length;j++){
				matrix_test[i][j] = Integer.parseInt(var[j]);
			}
			i++;
		}
	}
}
