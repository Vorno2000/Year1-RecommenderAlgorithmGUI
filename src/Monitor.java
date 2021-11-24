public class Monitor extends Product{
	public Monitor() {
		super.setPrice(600);
		super.setCode(199);
	}
	
	public String toString() {
		return "Monitor ("+super.getCode()+") "+"$"+super.getPrice();
	}
}
