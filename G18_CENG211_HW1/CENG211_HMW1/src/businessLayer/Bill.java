package businessLayer;
import java.util.Calendar;
public class Bill {
	
	private int billId;
	private int flatId;
	private int Amount; 
	private String Type;
	private String paymentInfo;
	private Calendar paymentDeadlineDate;
	private Calendar lastUpdateDate;
	
	public Bill(int billId, int flatId, int Amount, String Type, String paymentInfo, Calendar paymentDeadlineDate, Calendar lastUpdateDate) {
		this.billId = billId;
		this.flatId = flatId;
		this.Amount = Amount;
		this.Type = Type;
		this.paymentInfo = paymentInfo;
		this.paymentDeadlineDate = paymentDeadlineDate;
		this.lastUpdateDate = lastUpdateDate;
	}
	
	@SuppressWarnings("deprecation")
	public String toString(){
		return billId + "," + flatId + "," + Amount + "," + Type + "," + paymentInfo + "," + paymentDeadlineDate.get(Calendar.YEAR) +  "-" + 
		(paymentDeadlineDate.getTime().getMonth()+1) + "-" +paymentDeadlineDate.getTime().getDate() + "," + lastUpdateDate.get(Calendar.YEAR) + "-" + 
		(lastUpdateDate.getTime().getMonth()+1) + "-" +lastUpdateDate.getTime().getDate();
	}
	
	public int getBillId() {
		return billId;
	}
	
	@SuppressWarnings("unused")
	private void setBillId(int billId) {
		if (billId > 0) {
			this.billId = billId;
			System.out.println("Bill Id has been updated. New billId is " + billId);
		}
		else {
			System.out.println("Bill Id has to be an positive integer.");
		}
		return;
	}
	
	public int getFlatId() {
		return flatId;
	}
	
	@SuppressWarnings("unused")
	private void setFlatId(int flatId) {
		if (flatId > 0) {
			this.flatId = flatId;
			System.out.println("Flat Id has been updated. New Flat Id is " + flatId);
		}
		else {
			System.out.println("Flat Id has to be an positive integer.");
		}
		return;
	}
	
	public String getType() {
		return Type;
	}
	
	@SuppressWarnings("unused")
	private void setType(String Type) {
		this.Type = Type;
	}
	
	public int getAmount() {
		return Amount;
	}
	
	@SuppressWarnings("unused")
	private void setAmount(int Amount) {
		if (Amount > 0) {
			this.Amount = Amount;
			System.out.println("Amount has been updated. New Amount is " + Amount);
		}
		else {
			System.out.println("Amount has to be an positive integer.");
		}
		return;
	}
	
	public String getPaymentInfo() {
		return paymentInfo;
	}
	
	public void setPaymentInfo(String paymentInfo) {
		if (paymentInfo.toLowerCase().equals("false") || paymentInfo.toLowerCase().equals("true")) {
			this.paymentInfo = paymentInfo;
			System.out.println("Payment Info has been updated. New Payment Info is " + paymentInfo);
		}
		else {
			System.out.println("Payment Info has to be either true or false.");
		}
		return;
	}
	
	public Calendar getPaymentDeadlineDate() {
		return paymentDeadlineDate;
	}
	
	@SuppressWarnings("unused")
	private void setPaymentDeadlineDate(Calendar paymentDeadlineDate) {
			this.paymentDeadlineDate = paymentDeadlineDate;
		return;
	}

	public Calendar getLastUpdateDate() {
		return lastUpdateDate;
	}
	
	public void setLastUpdateDate(Calendar lastUpdateDate) {
			this.lastUpdateDate = lastUpdateDate;
		return;
	}
}
