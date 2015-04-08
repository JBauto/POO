package lab5;

public class Shares {
	
	private Integer amount;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "Shares [amount=" + amount + "]";
	}

	public Shares(){
		amount = 0;
	}
	
}
