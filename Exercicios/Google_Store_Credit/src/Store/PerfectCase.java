package Store;

public class PerfectCase {
	
	public void iteratePrices (ItemsPrice x){
		int i,j,n=0;
		//System.out.println(x.getCredit());
		for(i = 0; i<x.array_Length(); i++){
			//System.out.println("Fixed Value = "+x.getPrice(i));
			for(j = 0; j<(x.array_Length()); j++){
				//System.out.println("Test Value = "+x.getPrice(j));
				if (x.getCredit() == x.getPrice(i) + x.getPrice(j)){
					System.out.println("Case:" + x.getPrice(i) + "," + x.getPrice(j));
					n=1;
				}
			}
			if(n==1){
				n=0;
				break;
			}
		}		
	}	
}
