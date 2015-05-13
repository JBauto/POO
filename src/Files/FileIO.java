package Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {
	
	public int numberLine(String s) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(s));
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
		return lines;
	}
	public BufferedReader openFile(String file) throws IOException{
//		File dir = new File(".");
//		File fin = new File(dir.getCanonicalPath() + File.separator + file);
		BufferedReader br = new BufferedReader(new FileReader(file));
		return br;
	}
	
	public String readLine(BufferedReader br) throws IOException{
		String line = null;		
		line = br.readLine();
		return line;
	}
	
}
