package com.session;
/*
 * 订单处理的相关操作
 */
public class ItemOrder {

	private CatalogItem item;
	private int numItems;
		
	public ItemOrder(CatalogItem item, int numItems) {
		this.item = item;
		this.numItems = numItems;
	}
	
	public CatalogItem getItem() {
		return item;
	}
	public void setItem(CatalogItem item) {
		this.item = item;
	}
	public int getNumItems() {
		return numItems;
	}
	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}
	
	public String getItemID()
	{
		return this.getItem().getItemID();
	}
	public String getShortDescription()
	{
		return this.getItem().getShortDescription();
	}
	public String getLongDescription()
	{
		return this.getItem().getLongDescription();
	}
	public double getUnitCost()
	{
		return this.getItem().getCost();
	}
	
	public void incrementNumItems()
	{
		this.setNumItems(this.getNumItems()+1);
	}
	public void cancelOrder()
	{
		this.setNumItems(0);
	}
	public double getTotalCost()
	{
		return (this.getUnitCost()*this.getNumItems());
	}
}
