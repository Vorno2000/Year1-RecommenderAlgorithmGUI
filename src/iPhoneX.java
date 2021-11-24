public class iPhoneX extends Product{
	public iPhoneX() {
		super.setPrice(1300);
		super.setCode(865);
	}
	
	public String toString() {
		return "iPhoneX ("+super.getCode()+") "+"$"+super.getPrice();
	}
}
