

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {
	
	public BufferedReader openFile(String file) throws IOException{
		File dir = new File(".");
		File fin = new File(dir.getCanonicalPath() + File.separator + file);
		BufferedReader br = new BufferedReader(new FileReader(fin));
		return br;
	}
	
	public String readLine(BufferedReader br) throws IOException{
		String line = null;		
		line = br.readLine();
		return line;
	}
	
}
