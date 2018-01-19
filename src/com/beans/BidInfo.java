package com.beans;

public class BidInfo {

	private String itemID = "";
	private String itemName = "";
	private String bidderName = "";
	private String emailAddress = "";
	private double bidPrice = 0;
	private boolean autoIncrement = false;
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getBidderName() {
		return bidderName;
	}
	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public double getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}
	public boolean isAutoIncrement() {
		return autoIncrement;
	}
	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}
	//判断是否全部完成填写
	public boolean isComplete()
	{
		return (hasValue(this.getItemID()) &&
				hasValue(this.getItemName()) &&
				hasValue(this.getBidderName()) &&
				hasValue(this.getEmailAddress()) &&
				(this.getBidPrice()>0));
	}
	//判断是否完成部分
	public boolean isPartlyComplete()
	{
		boolean flag = (hasValue(this.getItemID()) ||
						hasValue(this.getItemName()) ||
						hasValue(this.getBidderName()) ||
						hasValue(this.getEmailAddress()) ||
						(this.getBidPrice()>0) ||
						this.isAutoIncrement());
		return flag;
	}
	//判断是否有值
	public boolean hasValue(String val)
	{
		return ((val!=null) && (!val.equals("")));
	}
	
}
