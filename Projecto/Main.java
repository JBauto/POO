import java.io.IOException;
import java.util.HashMap;

public class Main {

	public static void main(String args[]) throws IOException{
			
		Train teste = new Train();
		
		teste.readTrain("train-data.csv");
		for(int i = 0; i <teste.number_var; i++)
			System.out.println(teste.network_data.get(i));
	}
	
}
