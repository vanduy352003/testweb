package tvDuy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tvDuy.Models.CategoryModel;
import tvDuy.Models.ProductModel;

public class ProductDAOImpl implements IProductDAO{

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	ICategoryDAO cateDAO = new CategoryDAOImpl();
	
	@Override
	public List<ProductModel> findAll() {
		String sql = "SELECT * FROM Product";
		List<ProductModel> list = new ArrayList<ProductModel>();
		
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductModel model = new ProductModel();
				model.setProductID(rs.getInt("ProductID"));
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setPrice(rs.getInt("Price"));
				model.setImageLink(rs.getString("ImageLink"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setSellerID(rs.getInt("SellerID"));
				model.setAmount(rs.getInt("Amount"));
				model.setStoke(rs.getInt("Stoke"));
				list.add(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductModel> findBaseOnCateID(int cateID) {
		String sql = "SELECT * FROM Product WHERE CategoryID = ?";
		List<ProductModel> list = new ArrayList<ProductModel>();
		
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cateID);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				CategoryModel model1 = cateDAO.findOne(rs.getInt("CategoryID"));
				ProductModel model = new ProductModel();
				model.setProductID(rs.getInt("ProductID"));
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setPrice(rs.getInt("Price"));
				model.setImageLink(rs.getString("ImageLink"));
				model.setCategoryID(model1.getCategoryID());
				model.setSellerID(rs.getInt("SellerID"));
				model.setAmount(rs.getInt("Amount"));
				model.setStoke(rs.getInt("Stoke"));
				list.add(model);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insert(ProductModel model) {
		String sql = "INSERT INTO Product(ProductName, Description, Price, ImageLink, CategoryID, SellerID, Stoke) VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, model.getProductName());
			ps.setString(2, model.getDescription());
			ps.setInt(3, model.getPrice());
			ps.setString(4, model.getImageLink());
			ps.setInt(5, model.getCategory().getCategoryID());
			ps.setInt(6, model.getSellerID());
			ps.setInt(7, model.getStoke());
			
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(ProductModel model) {
		String sql = "UPDATE Product SET ProductName=?, Description=?, Price=?, ImageLink=?, CategoryID=?, SellerID=?, Stoke=? WHERE ProductID=?";
		
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, model.getProductName());
			ps.setString(2, model.getDescription());
			ps.setInt(3, model.getPrice());
			ps.setString(4, model.getImageLink());
			ps.setInt(5, model.getCategory().getCategoryID());
			ps.setInt(6, model.getStoke());
			ps.setInt(7, model.getProductID());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ProductModel findOne(int id) {
		String sql = "SELECT * FROM Product WHERE ProductID = ?";
		ProductModel model = new ProductModel();
		
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				model.setProductID(rs.getInt("ProductID"));
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setPrice(rs.getInt("Price"));
				model.setImageLink(rs.getString("ImageLink"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setSellerID(rs.getInt("SellerID"));
				model.setAmount(rs.getInt("Amount"));
				model.setStoke(rs.getInt("Stoke"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public int countByCateID(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductModel> count() {
		String sql = "SELECT categoryID, COUNT(*) as count FROM Product GROUP BY CategoryID ORDER BY count DESC";
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new ProductModel(rs.getInt("CategoryID"), rs.getInt("count")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductModel> find10Top() {
		String sql = "SELECT TOP 10 * FROM Product Order by productID DESC";
		List<ProductModel> list = new ArrayList<ProductModel>();
		
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductModel model = new ProductModel();
				model.setProductID(rs.getInt("ProductID"));
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setPrice(rs.getInt("Price"));
				model.setImageLink(rs.getString("ImageLink"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setSellerID(rs.getInt("SellerID"));
				model.setAmount(rs.getInt("Amount"));
				model.setStoke(rs.getInt("Stoke"));
				list.add(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductModel> findAllPage(int indexp) {
		String sql = "SELECT * FROM Product ORDER BY ProductID DESC OFFSET ? rows fetch next 3 rows only";
List<ProductModel> list = new ArrayList<ProductModel>();
		
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, indexp);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductModel model = new ProductModel();
				model.setProductID(rs.getInt("ProductID"));
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setPrice(rs.getInt("Price"));
				model.setImageLink(rs.getString("ImageLink"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setSellerID(rs.getInt("SellerID"));
				model.setAmount(rs.getInt("Amount"));
				model.setStoke(rs.getInt("Stoke"));
				list.add(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
