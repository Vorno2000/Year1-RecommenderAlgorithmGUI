/**
 * this allows for the exception ProductNotFoundException to function as intended
 * @author Vorno
 */
public class ProductNotFoundException extends Exception{
	public ProductNotFoundException(String message) {
		System.out.println(message);
	}
}