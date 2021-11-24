public class ueBoom extends Product{
	public ueBoom() {
		super.setPrice(200);
		super.setCode(123);
	}
	
	public String toString() {
		return "ueBoom ("+super.getCode()+") "+"$"+super.getPrice();
	}
}

