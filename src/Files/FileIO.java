package Files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {
	/** Method that finds the number of lines of a given file
	 * 
	 * @param s	string that contains the full path of the file
	 * @return returns the number of lines of the file
	 */
	public int numberLine(String s) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(s));
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
		return lines;
	}
	/** Method that opens the file for the given path
	 *  
	 * @param file string that contains the full path of the file
	 * @return returns a buffer br with the data read from the file
	 */
	public BufferedReader openFile(String file) throws IOException{
//		File dir = new File(".");
//		File fin = new File(dir.getCanonicalPath() + File.separator + file);
		BufferedReader br = new BufferedReader(new FileReader(file));
		return br;
	}
	/** Method that reads a line from a data buffer
	 * 
	 * @param br buffer with the data to read
	 * @return return a string with the line that was read
	 */
	public String readLine(BufferedReader br) throws IOException{
		String line = null;		
		line = br.readLine();
		return line;
	}
	
}
