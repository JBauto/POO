package lab5;

import java.util.HashMap;

public class Stockmarket {
	
	public HashMap<String,Integer> stock;
	
	public Stockmarket(){
		stock = new HashMap<String, Integer>();
	}
	
	public void addCorporation(int amount,String corp){
		stock.put(corp, amount);
	}

	public void removeCorporation(String corp){
		stock.remove(corp);
	}

	private void updateCorporation(int amount, String corp){
		stock.put(corp, amount);
	}
	
	/*public void buyShare(int amount, String corp, Stockowner x){
		int totalshares = stock.get(corp);
		int rem_shares;
		int owner_shares;
		HashMap<String,Integer> owner;
		rem_shares = totalshares - amount;
		owner = x.getShares();
		if(rem_shares <0){
			stock.put(corp, 0);
			if(owner.containsKey(corp)){
				owner_shares = owner.get(corp);
				owner.put(corp, owner_shares + rem_shares);	
			}else{
				owner.put(corp, rem_shares);
			}
		}else{
			stock.put(corp, rem_shares);
			if(owner.containsKey(corp)){
				owner_shares = owner.get(corp);
				owner.put(corp, owner_shares + amount);
			}else{
				owner.put(corp, amount);
			}
		}
	}*/
	
	/*public void sellShare(int amount, String corp, Stockowner x){	
		int totalshares = stock.get(corp);
		int owner_shares;
		HashMap<String,Integer> owner;
		owner = x.getShares();
		if(owner.containsKey(corp)){
			owner_shares = owner.get(corp);
			if(amount > owner_shares){
				stock.put(corp, totalshares + owner_shares);
				owner.put(corp, 0);
			}else{
				stock.put(corp, totalshares + amount);
				owner.put(corp, owner_shares - amount);
			}	
		}else{
			System.out.println("You dont have shares of " + corp);
		}		
	}*/
	
	public HashMap<String,Integer> getStock(){
		return stock;
	}

	
	@Override
	public String toString() {
		return "Stockmarket [stock=" + stock + "]";
	}

}
