public class Fridge extends Product{
	public Fridge() {
		super.setPrice(5000);
		super.setCode(187);
	}
	
	public String toString() {
		return "Fridge ("+super.getCode()+") "+"$"+super.getPrice();
	}
}