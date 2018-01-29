package com.session;

import java.util.ArrayList;
import java.util.List;

/*
 * ���ﳵ
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
	
	//��ӵ����ﳵ
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
	
	//���ù�������
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
					//�Ӷ������Ƴ�
					itemsOrdered.remove(i);
				}
				else
				{
					//���Ķ�������
					order.setNumItems(numOrdered);
				}
				return;
			}
			//û����ز�Ʒ
			ItemOrder newOrder = new ItemOrder(Catalog.getItem(itemID), numOrdered);
			itemsOrdered.add(newOrder);
		}
	}
}
