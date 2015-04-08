package lab5;

public class MainClass {
	
	public static void main(String [] args)
	{
		Stockmarket market = new Stockmarket();
		Person p1 = new Person("Joao");
		Person p2 = new Person("Antonio");
		Corporation c1 = new Corporation("Intel");
		Corporation c2 = new Corporation("AMD");
		
		c1.updateCorpShares(1000, market);
		market.addCorporation(1000, c1.getName());
		c2.updateCorpShares(2000, market);
		market.addCorporation(2000, c2.getName());
		
		System.out.println("Inicial State\n"+market);	
		
		p1.buyShare(100, "Intel", market);
		p1.buyShare(200, "AMD", market);
		p2.buyShare(100, "Intel", market);
		
		System.out.println("1st Buy");		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(market);	
				
		System.out.println("1st Sell");
		c1.buyShare(200, "Intel", market);
		c1.sellShare(100, c1.getName(), market);
		System.out.println(c1);
		System.out.println(market);	
	}
}
