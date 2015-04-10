import java.util.Arrays;


public class Lab2 {

	private Lab1 labs1[] = new Lab1[10];
	private int length = 0;

	boolean associateLab1(Lab1 obj){
		int i = 0, j = 0,m = 0;
		while(i<10) {
			if(labs1[i] == null){
				break;
			}
			i++;
		}
		j = i;
		for(i=0; i<labs1.length; i++){			
			if(labs1[i] != null){
				if(labs1[i].equals(obj)){
					System.out.println("Equals");
					return false;						
				}else{
					continue;
				}
			}else{
				m = 1;
				labs1[i] = obj;
				length = i;
				break;
			}			
		}
		if(j < 10 && (i == labs1.length) && m == 0){
			labs1[j] = obj;
			length = j;
		}
		System.out.println("Length = " + length + " @ Position = "+j);
		if(length > 10){
			System.out.println("Trying to associate more than 10 objects!");
			System.exit(2);
		}
		return true;
	}

	@Override
	public String toString() {
		int i;
		String test = "Lab2 [labs1=";
		for(i=0; i<labs1.length; i++){
			test = test + " " + labs1[i];
		}
		return test;
		//return "Lab2 [labs1=" + Arrays.toString(labs1) + ", length=" + length+ "]";
	}
	
}
