package application;



public class Aidat {
	
	private long aidatId;
	private long aidatMonth;
	private long aidatYear;
	private long aidatAmount;
	private long aidatStatus;
	private long payerNo;
	
	public long getAidatId() {
		return aidatId;
	}
	public void setAidatId(long aidatId) {
		this.aidatId = aidatId;
	}
	public long getAidatMonth() {
		return aidatMonth;
	}
	public void setAidatMonth(long aidatMonth) {
		this.aidatMonth = aidatMonth;
	}
	public long getAidatYear() {
		return aidatYear;
	}
	public void setAidatYear(long aidatYear) {
		this.aidatYear = aidatYear;
	}
	public long getAidatAmount() {
		return aidatAmount;
	}
	public void setAidatAmount(long aidatAmount) {
		this.aidatAmount = aidatAmount;
	}
	public long getAidatStatus() {
		return aidatStatus;
	}
	public void setAidatStatus(long aidatStatus) {
		this.aidatStatus = aidatStatus;
	}
	public long getPayerNo() {
		return payerNo;
	}
	public void setPayerNo(long payerNo) {
		this.payerNo = payerNo;
	}
	@Override
	public String toString() {
		return "Aidat [aidatId=" + aidatId + ", aidatMonth=" + aidatMonth + ", aidatYear=" + aidatYear
				+ ", aidatAmount=" + aidatAmount + ", aidatStatus=" + aidatStatus + ", payerNo=" + payerNo + "]";
	}

	
	

}
