import java.util.*;

public class PurchaseHistory {
	private ArrayList<Product> purchases;
	
	public PurchaseHistory(ArrayList<Product> n_Product) {
		purchases = n_Product;
	}
	
	public ArrayList<Product> getPurchases() {
		return purchases;
	}
	public String toString() {
		return "";
	}
}
