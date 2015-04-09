package stockmarket;

import java.util.LinkedList;

public class StockOwner {
	protected String owner;
	protected float money;
	private LinkedList<Share> shares;
	
	public StockOwner (String name, float money){
		LinkedList<Share> shares = new LinkedList<Share>();
		owner=name;
		this.money=money;
	}
	public void credit (float amount){
		money=money+amount;
	}
	
	public void debit (float amount){
		money=money-amount;
	}
	
	public float getMoney() {
		return money;
	}
	public void removeShare (Share x){
		
	}
	
	public void addShare (Share x){
		
	}
	public LinkedList<Share> getShares() {
		return shares;
	}

	

}
