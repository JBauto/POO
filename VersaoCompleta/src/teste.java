import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class teste {

	protected int matrixHashcode(int[][] mat){
		
		return 0;
	}
	
	public static void main(String[] args) {
		
		List<int[]> lista = new ArrayList<int[]>();
		Random rnd = new Random();
		int b = 0;
		int m = 0;

		long start = System.currentTimeMillis();		
		while(true){
			int[][] teste1 = new int[6][4];
			int[] hash = new int[4];
			int k = 0;
			for(int i = 0; i<teste1.length; i++){
				for(int j = 0;j<teste1[0].length;j++){
					teste1[i][j] = rnd.nextInt(3);
				}
			}
			for(int i = 0; i <hash.length; i++){
				hash[i] = Arrays.hashCode(teste1[i]);
				//System.out.println(Arrays.toString(teste1[i]));
			}

			System.out.println("("+m+")A testar");
			for(Iterator<int[]> it = lista.iterator(); it.hasNext(); ){
				int[] tmp = it.next();
				if(Arrays.equals(tmp, hash)){
					System.out.println("Igual,"+k);
					b = 1;				
				}
				k++;
			}
			if(b==1) break;
			lista.add(hash);
			m++;
		}
		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds\n");
		
	}
}
