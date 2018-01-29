package com.session;
/*
 * 商品列表
 */
public class Catalog {
	private static CatalogItem[] items = 
		{new CatalogItem("1001", "servlet", "servlet", 39.95),
		 new CatalogItem("1002", "jsp", "jsp", 49.99),
		 new CatalogItem("1003", "servlet2", "servlet2", 29.95),
		 new CatalogItem("1004", "servlet3", "servlet3", 39.95),
		 new CatalogItem("1005", "servlet4", "servlet4", 49.95)
		};
	public static CatalogItem getItem(String itemID)
	{
		CatalogItem item;
		if(itemID == null)
		{
			return null;
		}
		for(int i=0; i<items.length; i++)
		{
			item = items[i];
			if(itemID.equals(item.getItemID()))
			{
				return item;
			}
		}
		return null;
	}
}
