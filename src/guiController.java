/**
 * this class is the complete controller for the GUI. it controls the view and what to show when different events occur
 * @author Vaughn Carroll 18027345
 */
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;

public class guiController {
	private guiView theView;
	private OnlineShop shop;
	/**
	 * this is the constructor which initializes the view, passes all the action listeners, sets the cart and then calls the mainLayout
	 * @param theView this is the view of the GUI and is passed in for the controller to be able to see it and use it
	 * @param shop the shop is the entire programs core and needs to be used in the controller
	 * @author Vaughn Carroll 18027345
	 */
	public guiController(guiView theView, OnlineShop shop) {
		this.shop = shop;
		
		theView.addListener(new listenerForCartButtons());
		theView.addProductToCartListener(new listenerForAddButton());
		theView.addBackButtonListener(new listenerForBackButton());
		theView.addYesButton(new listenerForYesButton());
		theView.addRecommendedBackListener(new ListenerForRecommendedBackButton());
		
		this.theView = theView;
		
		this.theView.setCart(shop.getShoppingCart());
		
		this.theView.mainLayout();
		
	}
	/**
	 * this is the Action listener for the buttons in the main menu
	 * @author Vaughn Carroll 18027345
	 */
	class listenerForAddButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource();
			JButton[] btn = theView.getButton();
			if (src == btn[0]) {
				theView.cartPanel();
			}
			else if (src == btn[1]) {
				if (shop.getShoppingCart().size() == 0) {
					theView.viewEmptyCart();
				}
				else {
					theView.viewCartPanel(shop.amountOwing());
				}
			}
			else if (src == btn[2]) {
				if (shop.getShoppingCart().size() == 0) {
					theView.viewEmptyCart();
				}
				else {
					theView.viewFinalizePaymentPanel(shop.amountOwing());
				}
			}
			else if (src == btn[3]) {
				System.exit(0);
			}
		}
	}
	/**
	 * this is the action listener for when a product is added to the cart
	 * @author Vaughn Carroll 18027345
	 */
	class listenerForCartButtons implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.mainMenu();
			String buttonText = ((JButton) e.getSource()).getText();
			if (buttonText.contains("Laptop")) {
				if (theView.getCart().size() == 0) {
					shop.addToCart(theView.getDB().get(200));
				}
				else {
					boolean temp = false;
					for(int i = 0; i < theView.getCart().size(); i++) {
						if (shop.getShoppingCart().get(i).getCode() == 200) {
							theView.errorMessage("A Laptop has already been added");
						}
						else {
							temp = true;
						}
					}
					if (temp == true) {
						shop.addToCart(theView.getDB().get(200));
					}
				}
			}
			else if (buttonText.contains("iPhoneX")) {
				if (theView.getCart().size() == 0) {
					shop.addToCart(theView.getDB().get(865));
				}
				else {
					boolean temp = false;
					for(int i = 0; i < theView.getCart().size(); i++) {
						if (shop.getShoppingCart().get(i).getCode() == 865) {
							theView.errorMessage("An iPhoneX has already been added");
						}
						else {
							temp = true;
						}
					}
					if (temp == true) {
						shop.addToCart(theView.getDB().get(865));
					}
				}
			}
			else if (buttonText.contains("Monitor")) {
				if (theView.getCart().size() == 0) {
					shop.addToCart(theView.getDB().get(199));
				}
				else {
					boolean temp = false;
					for(int i = 0; i < theView.getCart().size(); i++) {
						if (shop.getShoppingCart().get(i).getCode() == 199) {
							theView.errorMessage("A Monitor has already been added");
						}
						else {
							temp = true;
						}
					}
					if (temp == true) {
						shop.addToCart(theView.getDB().get(199));
					}
				}
			}
			else if (buttonText.contains("ueBoom")) {
				if (theView.getCart().size() == 0) {
					shop.addToCart(theView.getDB().get(123));
				}
				else {
					boolean temp = false;
					for(int i = 0; i < theView.getCart().size(); i++) {
						if (shop.getShoppingCart().get(i).getCode() == 123) {
							theView.errorMessage("A ueBoom has already been added");
						}
						else {
							temp = true;
						}
					}
					if (temp == true) {
						shop.addToCart(theView.getDB().get(123));
					}
				}
			}
			else {
				if (theView.getCart().size() == 0) {
					shop.addToCart(theView.getDB().get(187));
				}
				else {
					boolean temp = false;
					for(int i = 0; i < theView.getCart().size(); i++) {
						if (shop.getShoppingCart().get(i).getCode() == 187) {
							theView.errorMessage("A Fridge has already been added");
						}
						else {
							temp = true;
						}
					}
					if (temp == true) {
						shop.addToCart(theView.getDB().get(187));
					}
				}
			}
		}
	}
	/**
	 * this is the action listener for the back button in each class
	 * @author Vaughn Carroll 18027345
	 */
	class listenerForBackButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.setCart(shop.getShoppingCart());
			theView.mainMenu();
		}
	}
	/**
	 * this is the action listener for the yes button when the user clicks to purchase their selected items
	 * @author Vaughn Carroll 18027345
	 *
	 */
	class listenerForYesButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.setCart(shop.getShoppingCart());
			writePurchaseHistoryData();
			theView.purchaseSuccessfulPanel(shop.getRecommendedProducts());
		}
	}
	/**
	 * this is the action listener for the recommendedPanel's back button which clears the cart
	 * @author Vaughn Carroll 18027345
	 *
	 */
	class ListenerForRecommendedBackButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.setCart(shop.getShoppingCart());
			theView.clearRecommended();
			theView.mainMenu();
		}
	}
	/**
	 * this is the method which writes to the Purchase history text file whenever the user purchases products
	 * @author Vaughn Carroll 18027345
	 */
	public void writePurchaseHistoryData() {
		try {
			String filename = "Purchase-History.txt";
			FileWriter fw = new FileWriter(filename, true);
			fw.write("\n"+shop.getShoppingCart().size());
			
			for (int i = 0; i < shop.getShoppingCart().size(); i++) {
				fw.write("\n"+shop.getShoppingCart().get(i).getCode());
			}
			
			fw.close();
		} catch(IOException e) {
			System.err.println("File could not be found or written to" + e.getMessage());
		}
	}
	
}
