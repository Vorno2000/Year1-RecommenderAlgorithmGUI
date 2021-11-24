/**
 * this is the main class which calls the GUI controller and view and integrates all the classes into 1 to be used in the GUI. it also creates the database and reads the purchase history text file
 * @author Vaughn Carroll 18027345
 */
import java.util.*;
import java.io.*;

public class OnlineShopApp{
	/**
	 * this creates the product database
	 * @return the product database
	 * @author Vaughn Carroll 18027345
	 */
	static ProductDatabase generateSampleDatabase() {
		ProductDatabase Products = new ProductDatabase();
		
		Products.put(new Laptop());
		Products.put(new ueBoom());
		Products.put(new Fridge());
		Products.put(new iPhoneX());
		Products.put(new Monitor());
		
		return Products;
	}
	/**
	 * this reads the purchase history file and brings the information into the program
	 * @param pb this is the database that is used when reading the file
	 * @param filename this is the name of file to be read
	 * @return returns a purchase history of all the products that were read from the file
	 * @author Vaughn Carroll 18027345
	 */
	public static ArrayList<PurchaseHistory> readPurchaseHistoryData(ProductDatabase pb,String filename) throws ProductNotFoundException,IOException,NumberFormatException {
		try {
			Scanner s = new Scanner(new File(filename));
			
			int temp = 0;
			
			ArrayList<PurchaseHistory> n_History = new ArrayList<PurchaseHistory>();
			
			while (s.hasNext()) {
				if(s.hasNextInt()) {
					 
					temp = s.nextInt();
					 
					 ArrayList<Product> a_Product = new ArrayList<Product>();
					 
					 for (int i = 0; i < temp; i++) {
					 	 
						 int temp2 = s.nextInt();
						 Product r_Product = pb.get(temp2);
						 
						 if (r_Product == null)
							 throw new ProductNotFoundException(temp2+" is not a product code!");
						 else
							 a_Product.add(r_Product);					 
					 }
					 
					 PurchaseHistory r_History = new PurchaseHistory(a_Product);
					 n_History.add(r_History);			 
				}
				else
					s.next();
			}
			
			s.close();
			return n_History;
		} catch (ProductNotFoundException e) { 
			System.err.println("Product Not Found");
		} catch (IOException e) {
			System.err.println("File could not be found or read");
		} catch (NumberFormatException e) {
			System.err.println("Did not return type Integer");
		}
		return null;
	}
	/**
	 * this is the main method which calls all the classes and sends all the information to the GUI
	 * @author Vaughn Carroll 18027345
	 */
	public static void main(String[] args) throws NumberFormatException, ProductNotFoundException, IOException {
		ProductDatabase productDB = generateSampleDatabase();
		
		String filename = "Purchase-History.txt";
		OnlineShop shop = new OnlineShop(productDB, readPurchaseHistoryData(productDB, filename));
		
		guiView theView = new guiView(productDB);
		guiController theController = new guiController(theView, shop);
		
	}
}
