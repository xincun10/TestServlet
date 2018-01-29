package com.session;

import javax.servlet.ServletException;

public class KidsBooksPage extends CatalogPage {

	public void init() {
		String[] ids = {"1001", "1002", "1003"};
		this.setItems(ids);
		setTitle("id in 1001~1003 books");
	}

}
