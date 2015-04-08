package Store;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class MainClass {

	public static void main(String [] args) throws IOException
	{
		int i;
		Inventory store = new Inventory();
		ItemsPrice x = new  ItemsPrice();
		File dir = new File(".");
		File fin = new File(dir.getCanonicalPath() + File.separator + "Small.in");
		readFile(fin,store);
		//System.out.println(store);
		PrintWriter writer = new PrintWriter("Small.out", "UTF-8");

		for(i=1; i<=store.getInventory_size(); i++){
			int k,j,n=0;
			x = store.getPrices(i);
			//System.out.println(x.getCredit());
				for(k = 0; k<=x.array_Length(); k++){
					//System.out.println("Break");
					//System.out.println("K = " + k);
					//System.out.println("Fixed Value = "+x.getPrice(i));
					for(j = 0; j<x.array_Length(); j++){
						//System.out.println("Test Value = "+x.getPrice(j));
						if (x.getCredit() == x.getPrice(k) + x.getPrice(j) && j!=k){
							writer.println("Case #" + i +": " + (k+1) + " " + (j+1));
							n=1;
							break;
						}
					}
					if(n==1){
						n=0;
						break;
					}
				}
		}
		writer.close();
	}
	
	private static void readFile(File fin, Inventory s) throws IOException {
		// Construct BufferedReader from FileReader
		int i,j,k;
		String[] parts = null;
		String line = null;
		HashMap<Integer,ItemsPrice> map = new HashMap<Integer,ItemsPrice>();
		map = s.getStore();

		BufferedReader br = new BufferedReader(new FileReader(fin));
	 
		line = br.readLine();
		s.size_Inventory(Integer.parseInt(line));
		j = 0;
		k = 1;
		ItemsPrice p = new ItemsPrice();
		while ((line = br.readLine()) != null) {
			//System.out.println("K = " + k );
			parts = line.split(" ");
			for(i=0;i<parts.length;i++){				 
				 //System.out.println("K = "+(k%3)+" I = " + i + " Value = "+parts[i]);
				 if(k == 1) p.insert_credit(Integer.parseInt(parts[i]));
				 else if(k == 2) p.setSize(Integer.parseInt(parts[i]));
				 else if(k == 3) p.insert_Price(Integer.parseInt(parts[i]));
			}
			if(k == 3){
				j++;
				map.put(j, p);
				p = new ItemsPrice();
				//System.out.println("New Store = " + j);
				k = 0;
			}
			k++;
		}
		br.close();
		
	}
}
