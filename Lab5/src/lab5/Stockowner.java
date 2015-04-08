package lab5;

import java.util.HashMap;

public class Stockowner {
	private String name;
	private HashMap<String,Integer> shares;
	
	public void Init_HashMap(){
		shares = new HashMap<String, Integer>();
	}
	public HashMap<String,Integer> getShares(){
		return shares;
	}
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void buyShare(int amount, String corp, Stockmarket x){
		int rem_shares;
		int owner_shares;
		HashMap<String,Integer> stock;
		stock = x.getStock();
		int totalshares = stock.get(corp);
		rem_shares = totalshares - amount;
		if(rem_shares <0){
			stock.put(corp, 0);
			if(shares.containsKey(corp)){
				owner_shares = shares.get(corp);
				shares.put(corp, owner_shares + rem_shares);	
			}else{
				shares.put(corp, rem_shares);
			}
		}else{
			stock.put(corp, rem_shares);
			if(shares.containsKey(corp)){
				owner_shares = shares.get(corp);
				shares.put(corp, owner_shares + amount);
			}else{
				shares.put(corp, amount);
			}
		}
	}
	public void sellShare(int amount, String corp, Stockowner x){	
		int owner_shares;
		HashMap<String,Integer> stock;
		stock = x.getShares();
		int totalshares = stock.get(corp);
		if(shares.containsKey(corp)){
			owner_shares = shares.get(corp);
			if(amount > owner_shares){
				stock.put(corp, totalshares + owner_shares);
				shares.put(corp, 0);
			}else{
				stock.put(corp, totalshares + amount);
				shares.put(corp, owner_shares - amount);
			}	
		}else{
			System.out.println("You dont have shares of " + corp);
		}		
	}
}
