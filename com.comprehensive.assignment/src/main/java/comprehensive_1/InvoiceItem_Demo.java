package comprehensive_1;

public class InvoiceItem_Demo {

	// Create Variables InvoiceId, InvoiceDesc, InvoiceQty and InvoiceItemPrice
	private String invoiceId;
	private String invoiceDesc;
	private int invoiceQty;
	private double invoiceItemPrice;

	// Constructor to pass required values
	public InvoiceItem_Demo(String invoiceId, String invoiceDesc, int invoiceQty, double invoiceItemPrice) {
		this.invoiceId = invoiceId;
		this.invoiceDesc = invoiceDesc;
		this.invoiceQty = invoiceQty;
		this.invoiceItemPrice = invoiceItemPrice;
	}

	// Getter method to get invoice Id
	public String getInvoiceId() {
		return invoiceId;
	}

	// Setter method to set invoice Id
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	// Getter method to get invoice description
	public String getInvoiceDesc() {
		return invoiceDesc;
	}

	// Setter method to set invoice description
	public void setInvoiceDesc(String invoiceDesc) {
		this.invoiceDesc = invoiceDesc;
	}

	// Getter method to get invoice quantity
	public int getInvoiceQty() {
		return invoiceQty;
	}

	// Setter method to set invoice quantity
	public void setInvoiceQty(int invoiceQty) {
		this.invoiceQty = invoiceQty;
	}

	// Getter method to get invoice item price
	public double getInvoiceItemPrice() {
		return invoiceItemPrice;
	}

	// Setter method to set invoice item price
	public void setInvoiceItemPrice(double invoiceItemPrice) {
		this.invoiceItemPrice = invoiceItemPrice;
	}

	// Method to display total price based on quantity
	public void printInvoiceTotal() {

		System.out.println("Invoice ID: " + invoiceId);
		System.out.println("Description: " + invoiceDesc);
		System.out.println("Quantity: " + invoiceQty);
		System.out.println("Unit Price: " + invoiceItemPrice);

		// Print price based on quantity
		if (invoiceQty == 1) {
			System.out.println("Total Price: " + invoiceItemPrice);
		} else if (invoiceQty > 1) {
			double totalPrice = invoiceQty * invoiceItemPrice;
			System.out.println("Total Price: " + totalPrice);
		} else {
			System.out.println("Invalid quantity! [Please try again with a valid quantity to proceed further......]");
		}

	}

	public static void main(String[] args) {

		// Example with quantity 1
		InvoiceItem_Demo invoice1 = new InvoiceItem_Demo("INV_001", "Keyboard", 1, 800.55);
		invoice1.printInvoiceTotal();

		System.out.println();

		// Example with quantity more than 1
		InvoiceItem_Demo invoice2 = new InvoiceItem_Demo("INV_002", "Hard Disk", 3, 3000.88);
		invoice2.printInvoiceTotal();

	}

}