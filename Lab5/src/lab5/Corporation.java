package lab5;

import java.util.HashMap;

public class Corporation extends Stockowner{
	private String name;
	private HashMap<String,Shares> shares;
	
	public Corporation(String name){
		shares = new HashMap<String, Shares>();
		this.name = name;
	}
	
	public HashMap<String,Shares> getShares(){
		return shares;
	}
	
	public String getName(){
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void updateCorpShares(int amount, Stockmarket x){
		HashMap<String,Shares> stock;
		stock = x.getStock();
		Shares tmp = new Shares();
		tmp.setAmount(amount);
		stock.put(this.name, tmp);		
	}

	
	@Override
	public String toString() {
		return "Corporation [name=" + name + ", shares=" + shares + "]";
	}
	
	public void buyShare(int amount, String corp, Stockmarket x){
		int rem_shares;
		Shares owner_shares;
		Shares tmp = new Shares();
		Shares tmp2 = new Shares();
		HashMap<String,Shares> stock;
		stock = x.getStock();
		Shares totalshares = stock.get(corp);
		rem_shares = totalshares.getAmount() - amount;
		if(rem_shares <0){
			stock.put(corp, tmp);
			if(shares.containsKey(corp)){
				owner_shares = shares.get(corp);
				tmp2.setAmount(owner_shares.getAmount() + rem_shares);
				shares.put(corp, tmp2);	
			}else{
				tmp2.setAmount(rem_shares);
				shares.put(corp, tmp2);
			}
		}else{
			tmp.setAmount(rem_shares);
			stock.put(corp, tmp);
			if(shares.containsKey(corp)){
				owner_shares = shares.get(corp);
				tmp2.setAmount(owner_shares.getAmount() + amount);
				shares.put(corp, tmp2);
			}else{
				tmp2.setAmount(amount);
				shares.put(corp, tmp2);
			}
		}
	}
	public void sellShare(int amount, String corp, Stockmarket x){	
		Shares owner_shares;
		HashMap<String,Shares> stock;
		stock = x.getStock();
		Shares tmp = new Shares();
		Shares tmp2 = new Shares();
		Shares totalshares = stock.get(corp);
		if(shares.containsKey(corp)){
			owner_shares = shares.get(corp);
			if(amount > owner_shares.getAmount()){
				tmp.setAmount(totalshares.getAmount() + owner_shares.getAmount());
				stock.put(corp, tmp);
				tmp2.setAmount(0);
				shares.put(corp, tmp2);
			}else{
				tmp.setAmount(totalshares.getAmount() + amount);
				stock.put(corp, tmp);
				tmp2.setAmount(owner_shares.getAmount() - amount);
				shares.put(corp, tmp2);
			}	
		}else{
			System.out.println("You dont have shares of " + corp);
		}		
	}
}
