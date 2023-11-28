package tvDuy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tvDuy.Models.CategoryModel;

public class CategoryDAOImpl implements ICategoryDAO{

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	@Override
	public List<CategoryModel> findAll() {
		List<CategoryModel> list = new ArrayList<CategoryModel>();
		String sql = "SELECT * FROM Category";
		try {
			conn = new DBConnection().getConnection();
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CategoryModel cate = new CategoryModel();
				cate.setCategoryID(rs.getInt("CategoryID"));
				cate.setCategoryName(rs.getString("CategoryName"));
				cate.setIcon(rs.getString("Icon"));
				list.add(cate);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public void insert(CategoryModel model) {
		String sql = "INSERT INTO Category(CategoryName, Icon) VALUES(?, ?)";
		try {
//			Kết nối dữ liệu
			conn = new DBConnection().getConnection();
		
//			Phát biểu câu truy vấn
			ps = conn.prepareStatement(sql);
			
//			Truyền tham số vào query
			ps.setString(1, model.getCategoryName());
			ps.setString(2, model.getIcon());
			
//			Thực thi câu truy vấn
			ps.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void update(CategoryModel model) {
		String sql = "UPDATE Category SET CategoryName = ?, Icon = ? WHERE CategoryID = ?";
		try {
//			Kết nối dữ liệu
			conn = new DBConnection().getConnection();
		
//			Phát biểu câu truy vấn
			ps = conn.prepareStatement(sql);
			
//			Truyền tham số vào query
			ps.setString(1, model.getCategoryName());
			ps.setString(2, model.getIcon());
			ps.setInt(3, model.getCategoryID());
//			Thực thi câu truy vấn
			ps.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public CategoryModel findOne(int id) {
		CategoryModel cate = new CategoryModel();
		String sql = "SELECT * FROM Category WHERE CategoryID = ?";
		try {
			conn = new DBConnection().getConnection();
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				cate.setCategoryID(rs.getInt("CategoryID"));
				cate.setCategoryName(rs.getString("CategoryName"));
				cate.setIcon(rs.getString("Icon"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cate;
	}
	@Override
	public void delete(int id) {
		String sql = "DELETE Category WHERE CategoryID = ?";
		try {
//			Kết nối dữ liệu
			conn = new DBConnection().getConnection();
		
//			Phát biểu câu truy vấn
			ps = conn.prepareStatement(sql);
			
//			Truyền tham số vào query
			ps.setInt(1, id);
//			Thực thi câu truy vấn
			ps.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
