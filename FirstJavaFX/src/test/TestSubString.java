package test;

import dao.ProductDAO;

public class TestSubString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductDAO dao = new ProductDAO();
		String str = dao.getProductId();
		//str = str.substring(5);
		System.out.println("str = " + str);
	}

}
