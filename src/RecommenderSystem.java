/*
 * this class is meant for to recommend products according to the products in the cart
 * @author Vaughn Carroll 18027345
 */
import java.util.*;

public class RecommenderSystem {
	private ArrayList<PurchaseHistory> purchaseHistory;
	private ProductDatabase productDB;
	private ArrayList<RecommendedItem> recommend;
	/**
	 * this is the constructor which sets the purchase history of the class and the data base
	 * @param history this is the history from the purchase history text file
	 * @param productDB this is the product database of all the products
	 * @author Vaughn Carroll 18027345
	 */
	public RecommenderSystem(ArrayList<PurchaseHistory> history, ProductDatabase productDB) {
		purchaseHistory = history;
		this.productDB = productDB;
	}
	
	public int getProductFreq(Product n_Product) {
		int freq = 0;
		for (int i = 0; i < purchaseHistory.size(); i++) {
			for (int r = 0; r < purchaseHistory.get(i).getPurchases().size(); r++) {
				if (purchaseHistory.get(i).getPurchases().get(r).getCode() == n_Product.getCode()) {
					freq++;
				}
			}
		}
		return freq;
	}
	
	public void updateProductFreq(Product n_Product) {
		for (int i = 0; i < recommend.size(); i++) {
			if (recommend.get(i).getProduct() == n_Product) {
				recommend.get(i).setFreq(recommend.get(i).getFreq() + 1);
			}
		}
	}
	
	public ArrayList<Product> productRecommenderAlgorithm(ArrayList<Product> n_Product, int freq) {
		ArrayList<Product> recommendedProducts = new ArrayList<Product>();
		
		recommend = new ArrayList<RecommendedItem>();
		for (int i = 0; i < productDB.getProducts().size(); i++) {
			recommend.add(new RecommendedItem(productDB.getProducts().get(i), 0));	
		}
		
		for (int i = 0; i < purchaseHistory.size(); i++) {
			for (int r = 0; r < n_Product.size(); r++) {
				if (purchaseHistory.get(i).getPurchases().contains(n_Product.get(r))) {					
					for (int a = 0; a < purchaseHistory.get(i).getPurchases().size(); a++) {
						updateProductFreq(productDB.get(purchaseHistory.get(i).getPurchases().get(a).getCode()));	
					}
				}
			}
		}
		
		for (int i = 0; i < recommend.size(); i++) {
			if (recommend.get(i).getFreq() >= freq) {
				recommendedProducts.add(recommend.get(i).getProduct());
			}
		}
		
		return recommendedProducts;
		
	}
}