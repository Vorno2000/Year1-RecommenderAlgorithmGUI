public class RecommendedItem {
	private Product product;
	private int freq;
	
	public RecommendedItem() {
		setProduct(null);
		setFreq(0);
	}
	
	public RecommendedItem(Product n_Product, int n_freq) {
		setProduct(n_Product);
		setFreq(n_freq);
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product n_Product) {
		this.product = n_Product;
	}
	
	public int getFreq() {
		return freq;
	}
	
	public void setFreq(int n_freq) {
		this.freq = n_freq;
	}
}