import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

public class Main {

	public static void main(String args[]) throws IOException{
		
		long start = System.currentTimeMillis();	
		Train teste = new Train();		
		teste.readTrain("train-data.csv");		
		long end = System.currentTimeMillis();
		
		for(int i = 0; i <teste.number_var; i++)
			System.out.println(teste.network_data.get(i));
		for(int i = 0; i <teste.number_var*2; i++)
			System.out.println(teste.graph_data.get(i));
		
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
	}
	
}
