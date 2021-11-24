/**
 * This class is the entire view of the GUI. it defines all the components and displays them on different panels
 * @author Vaughn Carroll 18027345 Carroll 18027345
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class guiView extends JFrame{
	
	private ActionListener menuListener;
	private ActionListener productListener;
	private ActionListener backListener;
	private ActionListener yes_Listener;
	private ActionListener recommendedBack;
	private ArrayList<Product> cart;
	private ProductDatabase productDB;
	private JPanel mainPanel;
	private JPanel cartPanel;
	private JPanel viewEmptyCartPanel;
	private JPanel viewCartPanel;
	private JPanel finalizePaymentPanel;
	private JPanel purchaseSuccessfulPanel;
	private DefaultListModel<String> list1;
	private DefaultListModel<String> list2;
	private JList<String> list;
	private JList<String> final_List;
	private JList<String> recommended_List;
	private JLabel totalCostLabel;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JButton button;
	private JButton yes_Button;
	private JButton no_Button;
	private JButton[] button1 = new JButton[4];
	/**
	 * Constructor which sets the properties of the JFrame
	 * @param productDB this is to allows the product Database to be seen in the GUI view
	 * @author Vaughn Carroll 18027345
	 */
	guiView(ProductDatabase productDB) {
		super("Online Shop");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(350, 500);
		this.setVisible(true);
		this.setResizable(false);
		
		this.productDB = productDB;
	}
	/**
	 * this is the main layout. It sets up everything that will be displayed in all the panels and for the entire GUI
	 * @author Vaughn Carroll 18027345
	 */
	void mainLayout() {
		mainPanel = new JPanel();
		mainPanel.setBackground(new java.awt.Color(200, 220, 130));
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		getContentPane().add(mainPanel);
		
		label1 = new JLabel("Online Shop");
		label1.setFont(new Font("Serif", Font.BOLD, 40));
		mainPanel.add(label1);
		
		String[] titles = {"Add Product to Cart", "View Your Shopping Cart", "Finalize Purchases", "Quit"};
		for (int i = 0; i < 4; i++) {
			button1[i] = new JButton(titles[i]);
			button1[i].addActionListener(menuListener);
			button1[i].setPreferredSize(new Dimension(230, 40));
			button1[i].setFont(new Font("Arial", Font.BOLD, 15));
			button1[i].setBackground(new java.awt.Color(220, 190, 130));
			mainPanel.add(button1[i]);
		}
		
		//Cart Panel
		cartPanel = new JPanel();
		cartPanel.setBackground(new java.awt.Color(200, 220, 130));
		
		cartPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 22));
		
		label1 = new JLabel("Select a Product to be Added to the Cart");
		label1.setFont(new Font("Serif", Font.BOLD, 18));
		cartPanel.add(label1);
		
		for (int i = 0; i < productDB.getProducts().size(); i++) {
			Product temp = productDB.getProducts().get(i);
			
			button = new JButton(temp.toString());
			button.addActionListener(productListener);
			button.setPreferredSize(new Dimension(230, 40));
			button.setFont(new Font("Arial", Font.BOLD, 15));
			button.setBackground(new java.awt.Color(220, 190, 130));
			cartPanel.add(button);
		}
		
		button = new JButton("Back");
		button.addActionListener(backListener);
		button.setPreferredSize(new Dimension(230, 40));
		button.setFont(new Font("Arial", Font.BOLD, 15));
		button.setBackground(new java.awt.Color(220, 190, 130));
		cartPanel.add(button);
		
		//View Empty Shopping Cart
		viewEmptyCartPanel = new JPanel();
		viewEmptyCartPanel.setBackground(new java.awt.Color(200, 220, 130));
		
		label1 = new JLabel("Your Cart is Empty");
		label1.setFont(new Font("Serif", Font.BOLD, 30));
		viewEmptyCartPanel.add(label1);
		
		viewEmptyCartPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
		
		button = new JButton("Back");
		button.addActionListener(backListener);
		button.setPreferredSize(new Dimension(230, 40));
		button.setFont(new Font("Arial", Font.BOLD, 15));
		button.setBackground(new java.awt.Color(220, 190, 130));
		viewEmptyCartPanel.add(button);
		
		//Cart with at least 1 Item
		viewCartPanel = new JPanel();
		viewCartPanel.setBackground(new java.awt.Color(200, 220, 130));
		viewCartPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 18));
		
		label1 = new JLabel("Your Cart");
		label1.setFont(new Font("Arial", Font.BOLD, 30));
		
		list1 = new DefaultListModel<>();
		
		list = new JList<>(list1);
		list.setPreferredSize(new Dimension(250, 300));
		DefaultListCellRenderer renderer = (DefaultListCellRenderer)list.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
	
		button = new JButton("Back");
		button.addActionListener(backListener);
		button.setPreferredSize(new Dimension(230, 40));
		button.setFont(new Font("Arial", Font.BOLD, 15));
		button.setBackground(new java.awt.Color(220, 190, 130));
		
		//Finalize payment
		finalizePaymentPanel = new JPanel();
		finalizePaymentPanel.setBackground(new java.awt.Color(200, 220, 130));
		finalizePaymentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 18));
		
		label1 = new JLabel("Your Cart");
		label1.setFont(new Font("Arial", Font.BOLD, 30));
		
		final_List = new JList<>(list1);
		final_List.setFixedCellWidth(250);
		renderer = (DefaultListCellRenderer)final_List.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
		final_List.setFont(new Font("Arial", Font.PLAIN, 18));
		
		totalCostLabel = new JLabel();
		totalCostLabel.setFont(new Font("Arial", Font.BOLD, 25));
		
		label2 = new JLabel("Would you like to pay?");
		label2.setFont(new Font("Arial", Font.BOLD, 20));
		
		no_Button = new JButton("No");
		no_Button.addActionListener(backListener);
		no_Button.setPreferredSize(new Dimension(120, 40));
		no_Button.setFont(new Font("Arial", Font.BOLD, 15));
		no_Button.setBackground(new java.awt.Color(220, 190, 130));
		
		yes_Button = new JButton("Yes");
		yes_Button.addActionListener(yes_Listener);
		yes_Button.setPreferredSize(new Dimension(120, 40));
		yes_Button.setFont(new Font("Arial", Font.BOLD, 15));
		yes_Button.setBackground(new java.awt.Color(220, 190, 130));
		
		button = new JButton("Back");
		button.addActionListener(backListener);
		button.setPreferredSize(new Dimension(230, 40));
		button.setFont(new Font("Arial", Font.BOLD, 15));
		button.setBackground(new java.awt.Color(220, 190, 130));
		
		//Successful Purchase Panel
		purchaseSuccessfulPanel = new JPanel();
		
		purchaseSuccessfulPanel.setBackground(new java.awt.Color(200, 220, 130));
		purchaseSuccessfulPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 18));
		
		label3 = new JLabel("Purchase Successful");
		label3.setFont(new Font("Arial", Font.BOLD, 30));
		
		label4 = new JLabel("<html>You may also be interested<br/>in purchasing these products:</html>");
		label4.setFont(new Font("Arial", Font.BOLD, 18));
		
		list2 = new DefaultListModel<>();
		
		recommended_List = new JList<String>(list2);
		recommended_List.setPreferredSize(new Dimension(250, 250));
		renderer = (DefaultListCellRenderer)recommended_List.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
		
		button = new JButton("Back");
		button.addActionListener(recommendedBack);
		button.setPreferredSize(new Dimension(230, 40));
		button.setFont(new Font("Arial", Font.BOLD, 15));
		button.setBackground(new java.awt.Color(220, 190, 130));
	}
	/**
	 * this is what is called to show the cart panel. it also revalidates to update what is displayed on the GUI
	 * @author Vaughn Carroll 18027345
	 */
	void cartPanel() {
		this.setContentPane(cartPanel);
		this.invalidate();
		this.validate();
	}
	/**
	 * this is what is called when the main menu is to be displayed on the GUI
	 * @author Vaughn Carroll 18027345
	 */
	void mainMenu() {
		this.setContentPane(mainPanel);
		this.invalidate();
		this.validate();
	}
	/**
	 * this is what is called to be displayed when there are no items in the cart
	 * @author Vaughn Carroll 18027345
	 */
	void viewEmptyCart() {
		this.setContentPane(viewEmptyCartPanel);
		this.invalidate();
		this.validate();
	}
	/**
	 * this method is called when the user wants to view the cart
	 * @param totalCost //totalCost is passed in through the controller as the view is not able to see it
	 * @author Vaughn Carroll 18027345
	 */
	void viewCartPanel(double totalCost) {
		Collections.sort(cart);
		list1.clear();
		
		for (int i = 0; i < cart.size(); i++) {
			if (list1.contains(cart.get(i).toString())) {
			}				
			else {
				list1.addElement(cart.get(i).toString());
			}
		}
		
		list.setFont(new Font("Arial", Font.PLAIN, 18));
		
		viewCartPanel.add(label1);
		viewCartPanel.add(list);
		viewCartPanel.add(button);
		
		this.setContentPane(viewCartPanel);
		this.invalidate();
		this.validate();
	}
	/*
	 * this is the method which is used to display the finalize payment panel
	 */
	void viewFinalizePaymentPanel(double totalCost) {
		Collections.sort(cart);
		list1.clear();
		
		for (int i = 0; i < cart.size(); i++) {
			if (list1.contains(cart.get(i).toString())) {
			}				
			else {
				list1.addElement(cart.get(i).toString());
			}
		}
		
		totalCostLabel.setText("Total Cost: $" + totalCost);
		
		finalizePaymentPanel.add(label1);
		finalizePaymentPanel.add(final_List);
		finalizePaymentPanel.add(totalCostLabel);
		finalizePaymentPanel.add(label2);
		finalizePaymentPanel.add(no_Button);
		finalizePaymentPanel.add(Box.createHorizontalStrut(10));
		finalizePaymentPanel.add(yes_Button);
		finalizePaymentPanel.add(button);
		
		this.setContentPane(finalizePaymentPanel);
		this.invalidate();
		this.validate();
	}
	/**
	 * this is the method which is called after the user pays for their products
	 * @param recommendedProducts //this is passed in using the controller in order for the GUI view to be able to see the recommended products 
	 * @author Vaughn Carroll 18027345
	 */
	void purchaseSuccessfulPanel(ArrayList<Product> recommendedProducts) {
		cart.clear();
		list1.removeAllElements();
		
		for (int i = 0; i < recommendedProducts.size(); i++) {
			list2.addElement(recommendedProducts.get(i).toString());
		}
		
		recommended_List.setFont(new Font("Arial", Font.PLAIN, 18));
		
		purchaseSuccessfulPanel.add(label3);
		purchaseSuccessfulPanel.add(label4);
		purchaseSuccessfulPanel.add(recommended_List);
		purchaseSuccessfulPanel.add(button);
		
		this.setContentPane(purchaseSuccessfulPanel);
		this.invalidate();
		this.validate();
	}
	/**
	 * this is used to display any error messages that the program might run into. eg multiple of the same items
	 * @param errorMessage //displays the error message that is passed into it
	 * @author Vaughn Carroll 18027345
	 */
	void errorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	/**
	 * adds the ActionListener for the add products to cart button
	 * @param listenerForAddButton // passes in the action listener from controller in order for the view to see it and be able to call it
	 * @author Vaughn Carroll 18027345
	 */
	void addProductToCartListener(ActionListener listenerForAddButton) {
		this.menuListener = listenerForAddButton;
	}
	/**
	 * adds the action listener for when adding a product to the cart
	 * @param listenerForLoopButton //action listener is used in a for loop to create the buttons which displays the catalogue of products
	 * @author Vaughn Carroll 18027345
	 */
	void addListener(ActionListener listenerForLoopButton) {
		this.productListener = listenerForLoopButton;
	}
	/**
	 * the back button for each panel
	 * @param listenerForBackButton //action listener for when the back button is clicked
	 */
	void addBackButtonListener(ActionListener listenerForBackButton) {
		this.backListener = listenerForBackButton;
	}
	/**
	 * adds the action listener for when the user clicks yes to purchase their products
	 * @param Yes //action listener for when the yes button is clicked
	 * @author Vaughn Carroll 18027345
	 */
	void addYesButton(ActionListener Yes) {
		this.yes_Listener = Yes;
	}
	/**
	 * adds the recommended panels back action listener. required a separate back button to clear the cart
	 * @param recommendedBack //action listener for the back button in recommended panel
	 * @author Vaughn Carroll 18027345
	 */
	void addRecommendedBackListener(ActionListener recommendedBack) {
		this.recommendedBack = recommendedBack;
	}
	/**
	 * removes all the elements from the default model list for the recommended products
	 * @author Vaughn Carroll 18027345
	 */
	void clearRecommended() {
		list2.removeAllElements();
	}
	/**
	 * returns the cart and allows the controller to view the current cart in the view
	 * @return returns the cart
	 * @author Vaughn Carroll 18027345
	 */
	ArrayList<Product> getCart() {
		return cart;
	}
	/**
	 * sets the cart to whatever the controllers cart is
	 * @param cart //allows for controller to pass in the cart
	 */
	void setCart(ArrayList<Product> cart) {
		this.cart = cart;
		Collections.sort(cart);
	}
	/**
	 * allows for the controller to be able to see the database of the view
	 * @return the GUI views database
	 * @author Vaughn Carroll 18027345
	 */
	ProductDatabase getDB() {
		return this.productDB;
	}
	/**
	 * allows for the controller to see when the button1 has been pressed
	 * @return returns the button 1
	 * @author Vaughn Carroll 18027345
	 */
	JButton[] getButton() {
		return button1;
	}
}