import java.util.*;

public class Main {

	public static void main(String[] args) {
		int parameter = 0;
		
		if(args.length == 0 || (parameter = Integer.parseInt(args[0]))<1){
			System.out.println("Input number must be greater than 1");
			System.exit(1);
		}else{
			parameter = Integer.parseInt(args[0]);	
		}
		
		Lab2[] labs2 = new Lab2[parameter];
		int k = 0;
		while(true){
			int i = 0,j = 0, m = 0;
			boolean tmp = true;
			int cond = 0;
			Random numero = new Random();
			Lab1[] labs1 = new Lab1[parameter*10];
			for(i=0; i<parameter; i++){
				labs2[i] = new Lab2();
				//System.out.println(labs2);
			}
			for(i=0; i<parameter*10; i++){
				labs1[i] = new Lab1(numero.nextInt(10), 0);
				//System.out.println(labs1[i]);
			}
			for(i=0; i<parameter; i++){
				for(j=0; j<10 ; j++ ){
					//System.out.println("(I,J) = ("+i+","+j+")" +"  "+ "[x="+labs1[m].x+", y="+labs1[m].y+"]");
					tmp = labs2[i].associateLab1(labs1[m]);
					if(tmp == true ){
						cond++;
					}
					if(cond == 10){
						System.out.println("Found it: after " + k + " interation(s) with condition = " + cond + "\n"+ labs2[i]);
						System.exit(3);
					}
				if(m == parameter*10 - 1) break;
				else m++;
				}
				cond = 0;
				m = 0;
			}
			k++;
			//System.out.println("Iteração: "+k);
		}
	}
}
