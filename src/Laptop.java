public class Laptop extends Product {
	public Laptop() {
		super.setPrice(900);
		super.setCode(200);
	}
	
	public String toString() {
		return "Laptop ("+super.getCode()+") "+"$"+super.getPrice();
	}
}
