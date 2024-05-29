package application;



public class AidatPayer {
	private long payerId;
	private String payerName;
	private long payerNo;
	private String payerPhone;
	private String payerAddress;
	
	public long getPayerId() {
		return payerId;
	}
	public void setPayerId(long payerId) {
		this.payerId = payerId;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public long getPayerNo() {
		return payerNo;
	}
	public void setPayerNo(long payerNo) {
		this.payerNo = payerNo;
	}
	public String getPayerPhone() {
		return payerPhone;
	}
	public void setPayerPhone(String payerPhone) {
		this.payerPhone = payerPhone;
	}
	public String getPayerAddress() {
		return payerAddress;
	}
	public void setPayerAddress(String payerAddress) {
		this.payerAddress = payerAddress;
	}
	@Override
	public String toString() {
		return "AidatPayer [payerId=" + payerId + ", payerName=" + payerName + ", payerNo=" + payerNo + ", payerPhone="
				+ payerPhone + ", payerAddress=" + payerAddress + "]";
	}
	
	
	

}
