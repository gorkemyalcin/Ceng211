package businessLayer;

public class Customer {
	
	private String typeOfCustomer;
	private int discount;
	private int customerNumber;
	private Invoice invoice;
	
	public Customer(String typeOfCustomer, int customerNumber) {
		this.setTypeOfCustomer(typeOfCustomer);
		this.setCustomerNumber(customerNumber);
		initializeDiscounts(typeOfCustomer);
	}
	
	private void initializeDiscounts(String typeOfCustomer) {
		if (typeOfCustomer.equals("silver")) {
			this.setDiscount(10);
		}
		else if (typeOfCustomer.equals("gold")) {
			this.setDiscount(15);
		}
		else if (typeOfCustomer.equals("premium")) {
			this.setDiscount(20);
		}
		else {
			this.setDiscount(0);
		}
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getTypeOfCustomer() {
		return typeOfCustomer;
	}

	public void setTypeOfCustomer(String typeOfCustomer) {
		this.typeOfCustomer = typeOfCustomer;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
}
