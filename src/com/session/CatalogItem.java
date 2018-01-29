package com.session;
/*
 * …Ã∆∑œÍ«È
 */
public class CatalogItem {

	private String itemID;
	private String shortDescription;
	private String longDescription;
	private double cost;
	
	public CatalogItem(String itemID, String shortDescription, String longDescription, double cost) {
		this.itemID = itemID;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.cost = cost;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
}
