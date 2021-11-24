/**
 * this is the class which allows for all the products to be accessed and created. it also gives all the products their code and price
 * @author Vaughn Carroll 18027345
 */
abstract public class Product implements Comparable<Product>{
	
	private double price;
	private int code;
	
	public Product() {
		setPrice(0);
		setCode(0);
	}
	/**
	 * constructor which sets the price and code
	 * @param code passes in the code to be set as the code
	 * @param price passes in the price to be set as the price
	 * @author Vaughn Carroll 18027345
	 */
	public Product(int code, double price) {
		setPrice(price);
		setCode(code);
	}
	/**
	 * returns the price
	 * @return returns the price
	 * @author Vaughn Carroll 18027345
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * sets the price
	 * @param n_price sets the price to what is passed in
	 * @author Vaughn Carroll 18027345
	 */
	public void setPrice(double n_price) {
		this.price = n_price;
	}
	/**
	 * returns the code
	 * @return returns the code
	 * @author Vaughn Carroll 18027345
	 */
	public int getCode() {
		return code;
	}
	/**
	 * sets the code to what is passed in
	 * @param n_code sets the code to what is passed in
	 * @author Vaughn Carroll 18027345
	 */
	public void setCode(int n_code) {
		this.code = n_code;
	}
	/**
	 * compares prices between products
	 * @param n_product this is the product which is passed in to be compared with
	 * @return returns the price difference
	 * @author Vaughn Carroll 18027345
	 */
	public int compareTo(Product n_product) {
		return (int)(price - n_product.getPrice());
	}
	/**
	 * to string for the product class
	 * @return returns the string
	 * @author Vaughn Carroll 18027345
	 */
	public String toString() {
		return "toString of Product";
	}
}
