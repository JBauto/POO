package stockmarket;

import java.util.LinkedList;

public class StockMarket {
	
	LinkedList<Share> main_shares;
	
	public StockMarket (){
		LinkedList<Share> main_shares = new LinkedList<Share>();
	}
	
	public void putInMarket (Share x){
		main_shares.add(x);
	}
	public void putInMarket (Share x, int quantity){
		x.setQuantity(quantity);
		main_shares.add(x);
	}
	
	public void buy (StockOwner x, Corporation y, int quantity){
		float buyer_money = x.getMoney();
		float share_cost = y.getValue();
		int total_cost;
		int tmp;
		Share b;
		LinkedList<Share> buyer_shares;
		if(buyer_money / share_cost > quantity){
			buyer_shares = x.getShares();
			b = new Share(x,y,0);
			tmp = buyer_shares.indexOf(y);
			if(tmp == -1){
				b.setQuantity(quantity);
				x.addShare(b);				
			}else{
				b = buyer_shares.get(tmp);
				b.get
			}
			main_shares.
		}
	}

	public static void main(String[] args) {
		StockMarket market = new StockMarket();
		market.buy(x, y, quantity);
	}
}
