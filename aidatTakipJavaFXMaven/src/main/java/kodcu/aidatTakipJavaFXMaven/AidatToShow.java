package kodcu.aidatTakipJavaFXMaven;



public class AidatToShow {
	
	private long aidatId;
	private long aidatMonth;
	private long aidatYear;
	private long aidatAmount;
	private String aidatStatus;
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
	public String getAidatStatus() {
		return aidatStatus;
	}
	public void setAidatStatus(String aidatStatus) {
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
		return "AidatToShow [aidatId=" + aidatId + ", aidatMonth=" + aidatMonth + ", aidatYear=" + aidatYear
				+ ", aidatAmount=" + aidatAmount + ", aidatStatus=" + aidatStatus + ", payerNo=" + payerNo + "]";
	}
	
	

	
	

}
