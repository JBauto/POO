package stockmarket;

public class Corporation extends StockOwner {
	private float value;
	
	public Corporation (String name, float money, float value){
		super(name, money);
		this.value = value;
	}
	public Share createShare (int quantity) {
		
	
	}
	
	public float getValue (){
		return value;
	}


}
