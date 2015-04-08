package Store;

import java.util.ArrayList;

public class ItemsPrice {
	
	private ArrayList<Integer> items;
	private Integer credit;
	private Integer size;

	public Integer getCredit() {
		return credit;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public ItemsPrice(){
		items = new ArrayList<Integer>();
		credit = 0;
	}
	public Integer getSize() {
		return size;
	}
	public ArrayList<Integer> getItems() {
		return items;
	}
	public int array_Length(){
		return items.size();
	}
	public void insert_Price(int price){
		items.add(price);
	}
	@Override
	public String toString() {
		return "\nItemsPrice [items=" + items + ", credit=" + credit + ", size="
				+ size + "]";
	}
	public void insert_credit (int credit){
		this.credit = credit;
	}
	public int getPrice(int index){
		return items.get(index);
	}
}
