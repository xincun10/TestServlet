package com.session;

import javax.servlet.ServletException;

public class TechBooksPage extends CatalogPage {

	public void init() {
		String[] ids = {"1004", "1005"};
		this.setItems(ids);
		setTitle("id in 1004~1005 books");
	}

}
