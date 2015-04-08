package Store;

import java.util.HashMap;

public class Inventory {

	private HashMap<Integer,ItemsPrice> store;
	public Integer inventory_size;
	
	public Inventory(){
		store =  new HashMap<Integer,ItemsPrice>();
	}
	
	public void insert_Inventory(int key, ItemsPrice x){
		store.put(key, x);
	}
	
	public ItemsPrice getPrices(int key){
		return store.get(key);
	}
	
	public void size_Inventory(int size){
		inventory_size = size;
	}

	@Override
	public String toString() {
		return "Inventory [store=" + store + ", inventory_size="
				+ inventory_size + "]";
	}

	public HashMap<Integer, ItemsPrice> getStore() {
		return store;
	}

	public Integer getInventory_size() {
		return inventory_size;
	}
}
