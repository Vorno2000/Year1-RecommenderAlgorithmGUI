/**
 * this is the online shop class which retrieves the recommended Products and resets the cart after a purchase has been made
 * @author Vaughn Carroll 18027345
 */
import java.util.*;

public class OnlineShop implements PaymentSystem{
	private ProductDatabase productDB;
	private ArrayList<Product> cart = new ArrayList<Product>();
	private RecommenderSystem recommender;
	
	/**
	 * constructor for online shop which gets the recommended products
	 * @param n_productDB allows for the product database to be used in the online shop
	 * @param n_cart allows for the online shop to use the cart which would have the users products
	 * @author Vaughn Carroll 18027345
	 */
	public OnlineShop(ProductDatabase n_productDB, ArrayList<PurchaseHistory> n_cart) {
		recommender = new RecommenderSystem(n_cart, n_productDB);
	}
	/**
	 * this method allows for the retrieval of the recommendedProducts
	 * @return recommendedProducts returns all the recommended products
	 * @author Vaughn Carroll 18027345
	 */
	public ArrayList<Product> getRecommendedProducts() {
		ArrayList<Product> recommendedProducts = new ArrayList<Product>();
		
		for (int i = 0; i < cart.size(); i++) {
			ArrayList<Product> temp = new ArrayList<Product>();
			temp = recommender.productRecommenderAlgorithm(cart, recommender.getProductFreq(cart.get(i)));
			
			for (int r = 0; r < temp.size(); r++) {
				for (int a = 0; a < cart.size(); a++) {
					if (recommendedProducts.contains(temp.get(r)) || cart.contains(temp.get(r))) {
						
					}
					else
						recommendedProducts.add(temp.get(r));
				}
				
			}
		}
		
		return recommendedProducts;		
	}
	/**
	 * this gets in a product and adds it to the cart
	 * @param n_Product gets in a product to be added
	 * @return returns a boolean for whether the products was added or not
	 * @author Vaughn Carroll 18027345
	 */
	public boolean addToCart(Product n_Product) {
		Product temp = null;
		boolean match = false;
		boolean added = false;
		for (int i = 0; i < cart.size(); i++) {
			temp = cart.get(i);
			if (temp == n_Product)
				match = true;
		}
		if (match == false) {
			cart.add(n_Product);
			added = true;
		}
		
		return added;
		
	}
	/**
	 * this returns the products in the database
	 * @return the database products
	 */
	public ArrayList<Product> getProductListing() {
		return productDB.getProducts();
	}
	/**
	 * this returns the cart of the user
	 * @return returns and array list of the users cart
	 * @author Vaughn Carroll 18027345
	 */
	public ArrayList<Product> getShoppingCart() {
		return cart;
	}
	/**
	 * calculates the total amount that is to be charged to the customer
	 * @return a double of the total amount owing
	 * @author Vaughn Carroll 18027345
	 */
	public double amountOwing() {
		double cost = 0;
		for (int i = 0; i < cart.size(); i++) {
			cost = cost + cart.get(i).getPrice();
		}
		return cost;
	}
	/**
	 * completes the transaction and clears the cart
	 * @author Vaughn Carroll 18027345
	 */
	public void completeTransaction() {
		cart.clear();
	}
}
