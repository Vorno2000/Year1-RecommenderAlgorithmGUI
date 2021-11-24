/**
 * this class allows for the database to be retrieved and created. it also allows for a product to be returned according to a code
 * @author Vorno
 */
import java.util.*;
public class ProductDatabase {
	private ArrayList<Product> database;
	/**
	 * creates a new instance of database
	 * @author Vorno
	 */
	public ProductDatabase() {
		database = new ArrayList<Product>();
	}
	/**
	 * 
	 * @param n_Product
	 */
	public void put(Product n_Product) {
		database.add(n_Product);
	}
	
	public Product get(int n_Code) {
		Product r_Database = null;
		
		for(int i = 0; i < database.size(); i++) {
			if (database.get(i).getCode() == n_Code) {
				r_Database = database.get(i);
			}
		}
		return r_Database;
	}
	
	public ArrayList<Product> getProducts() {
		return database;
	}
}