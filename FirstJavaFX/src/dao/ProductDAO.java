package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import util.MySQLHelper;

public class ProductDAO {
	public String getProductId()  {
		String id = "";
		String sql = "select id from product order by id desc";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id = rs.getString(1);
				id = id.substring(5);
				int n = Integer.parseInt(id);
				n++;
				if(n<10)
					id = "PROD-0000" + n;
				else if(n<100)
					id = "PROD-000" + n;
				else if(n<1000)
					id = "PROD-00" + n;
				else if(n<10000) 
					id = "PROD-0" + n;
				else
					id = "PROD-" + n;
			}else {
				id = "PROD-00001";
			}
			
			rs.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public boolean insert(Product product) {
		boolean result = false;
		String sql = "insert into product(id, name, price, amount) values(?, ?, ?, ?)";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, product.getId());
			ps.setString(2, product.getName());
			ps.setInt(3, product.getPrice());
			ps.setInt(4, product.getAmount());
			
			int row = ps.executeUpdate();
			if(row>0)
				result = true;
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean update(Product product) {
		boolean result = false;
		String sql = "update product set name = ?, price = ?, amount = ? where id = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(4, product.getId());
			ps.setString(1, product.getName());
			ps.setInt(2, product.getPrice());
			ps.setInt(3, product.getAmount());
			
			int row = ps.executeUpdate();
			if(row>0)
				result = true;
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean delete(String id) {
		boolean result = false;
		String sql = "delete from product where id = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, id);
						
			int row = ps.executeUpdate();
			if(row>0)
				result = true;
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean updateAmount(String id, int amount, String flag) {
		boolean result = false;
		String sql = "";
		if(flag.equals("inc")) {
			sql = "update product set amount = amount + ? where id = ?";
		}else if(flag.equals("dec")) {
			sql = "update product set amount = amount - ? where id = ?";
		}
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(2, id);			
			ps.setInt(1, amount);	
			int row = ps.executeUpdate();
			if(row>0)
				result = true;
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public ObservableList<Product> select() {
		ObservableList<Product> products = FXCollections.observableArrayList();
		String sql = "select * from product";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				int amount = rs.getInt(4);
				
				Product product = new Product(id, name, price, amount);
				
				products.add(product);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	public ObservableList<Product> selectByName(String sname) {
		ObservableList<Product> products = FXCollections.observableArrayList();
		String sql = "select * from product where name like ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, "%" + sname + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				int amount = rs.getInt(4);
				
				Product product = new Product(id, name, price, amount);
				
				products.add(product);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	public ObservableList<Product> selectById(String sid) {
		ObservableList<Product> products = FXCollections.observableArrayList();
		String sql = "select * from product where id = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, sid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				int amount = rs.getInt(4);
				
				Product product = new Product(id, name, price, amount);
				
				products.add(product);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
}
