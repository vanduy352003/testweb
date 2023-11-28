package tvDuy.Services;

import java.util.List;

import tvDuy.DAO.IProductDAO;
import tvDuy.DAO.ProductDAOImpl;
import tvDuy.Models.ProductModel;

public class ProductServiceImpl implements IProductService{

	IProductDAO proDAO = new ProductDAOImpl();
	@Override
	public List<ProductModel> findBaseOnCateID(int cateID) {
		
		return proDAO.findBaseOnCateID(cateID);
	}
	@Override
	public List<ProductModel> findAll() {
		return proDAO.findAll();
	}
	@Override
	public void insert(ProductModel model) {
		proDAO.insert(model);
		
	}
	@Override
	public void update(ProductModel model) {
		
		ProductModel newPro = proDAO.findOne(model.getProductID());
		newPro.setProductName(model.getProductName());
		newPro.setDescription(model.getDescription());
		newPro.setPrice(model.getPrice());
		newPro.setImageLink(model.getImageLink());
		newPro.setCategory(model.getCategory());
		newPro.setStoke(model.getStoke());
		
		proDAO.update(newPro);
	}
	@Override
	public ProductModel findOne(int id) {
		return proDAO.findOne(id);
	}
	@Override
	public int countByCateID(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<ProductModel> count() {
		// TODO Auto-generated method stub
		return proDAO.count();
	}
	@Override
	public List<ProductModel> find10Top() {
		// TODO Auto-generated method stub
		return proDAO.find10Top();
	}

}
