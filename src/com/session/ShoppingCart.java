package com.session;

import java.util.ArrayList;
import java.util.List;

/*
 * 购物车
 */
public class ShoppingCart {

	private ArrayList<ItemOrder> itemsOrdered;

	public ShoppingCart() {
		itemsOrdered = new ArrayList();
	}
	
	public List getItemsOrdered()
	{
		return itemsOrdered;
	}
	
	//添加到购物车
	public synchronized void addItem(String itemID)
	{
		ItemOrder order;
		for(int i=0; i<itemsOrdered.size(); i++)
		{
			order = itemsOrdered.get(i);
			if(order.getItemID().equals(itemID))
			{
				order.incrementNumItems();
				return;
			}
		}
		ItemOrder newOrder = new ItemOrder(Catalog.getItem(itemID), 1);
		itemsOrdered.add(newOrder);
	}
	
	//设置购买数量
	public synchronized void setNumOrdered(String itemID, int numOrdered)
	{
		ItemOrder order;
		for(int i=0; i<itemsOrdered.size(); i++)
		{
			order = itemsOrdered.get(i);
			if(order.getItemID().equals(itemID))
			{
				if(numOrdered<=0)
				{
					//从订单中移除
					itemsOrdered.remove(i);
				}
				else
				{
					//更改订单数量
					order.setNumItems(numOrdered);
				}
				return;
			}
			//没有相关产品
			ItemOrder newOrder = new ItemOrder(Catalog.getItem(itemID), numOrdered);
			itemsOrdered.add(newOrder);
		}
	}
}
