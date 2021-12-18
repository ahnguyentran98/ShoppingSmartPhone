package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Product;

public class ListProductDAO {

	// search product by name
	public List<Product> search(String productName) throws Exception {
		List<Product> list = new ArrayList<>();
		try {
			// connect to database
			DBContext dbcontext = new DBContext();
			Connection conn = dbcontext.getConnection();
			Statement stmt = conn.createStatement();
			// query
			ResultSet rs = stmt.executeQuery("select * from dbo.Products");
			while (rs.next()) {
				// if name = null return all product
				if (!productName.isEmpty()) {
					String name = rs.getString(2);
					if (productName.equalsIgnoreCase(name)) {
						Product productFound = new Product(rs.getInt(1), rs.getString(2), rs.getString(3),
								rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getString(7));
						list.add(productFound);
					}
				} else {
					
					Product products = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
							rs.getString(5), rs.getString(6), rs.getString(7));
					list.add(products);
				}
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// get product by id
	public Product getProduct(int productID) throws Exception {
		Product productGet = new Product();
		try {
			// connect to database
			DBContext dbcontext = new DBContext();
			Connection conn = dbcontext.getConnection();
			Statement stmt = conn.createStatement();
			// query
			ResultSet rs = stmt.executeQuery("select * from dbo.Products");
			while (rs.next()) {
				int find = rs.getInt(1);
				// check
				if (productID != 0) {
					if (find == productID) {
						productGet = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
								rs.getString(5), rs.getString(6), rs.getString(7));
					}
				} else {
					productGet = null;
				}
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productGet;
	}
	
	
	//list pagination
    public List<Product> getRecords(int start,int total){  
        List<Product> list=new ArrayList<Product>();  
        try{  
			// connect to database
			DBContext dbcontext = new DBContext();
			Connection conn = dbcontext.getConnection();
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM dbo.Products Limit ?,?");  
            ps.setInt(1, start);
            ps.setInt(2, total);
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                Product e = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
                list.add(e);  
            }  
            conn.close();  
        }catch(Exception e){System.out.println(e);}  
        return list;  
    }  
    
    public List<Product> getShop(int a, int b) throws Exception {
		DBContext dbcontext = new DBContext();
		Connection conn = dbcontext.getConnection();
		List<Product> list = new ArrayList<Product>();
		List<Product> listFind = new ArrayList<Product>();
        String sql = "SELECT * FROM dbo.Products";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	  Product s = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
  						rs.getString(5), rs.getString(6), rs.getString(7));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        for(int i = a; i <b;i++ ) {
        	listFind.add(list.get(i));
        }
        return listFind;
    }
    
  //Total product number
    public int getCount() throws Exception {
        int count = 0;
        try {
			// connect to database
			DBContext dbcontext = new DBContext();
			Connection conn = dbcontext.getConnection();
			Statement stmt = conn.createStatement();
			// query
			ResultSet rs = stmt.executeQuery("select * from dbo.Products");
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(count);
        return count;
    }

}
