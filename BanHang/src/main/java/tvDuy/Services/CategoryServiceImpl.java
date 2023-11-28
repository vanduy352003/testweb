package tvDuy.Services;

import java.util.List;

import tvDuy.DAO.CategoryDAOImpl;
import tvDuy.DAO.ICategoryDAO;
import tvDuy.Models.CategoryModel;

public class CategoryServiceImpl implements ICategoryService{

	ICategoryDAO cateDAO = new CategoryDAOImpl();
	@Override
	public List<CategoryModel> findAll() {
		
		return cateDAO.findAll();
	}
	@Override
	public void insert(CategoryModel model) {
		cateDAO.insert(model);
		
	}
	@Override
	public void update(CategoryModel model) {
		CategoryModel newModel = cateDAO.findOne(model.getCategoryID());
		newModel.setCategoryID(model.getCategoryID());
		newModel.setCategoryName(model.getCategoryName());;
		newModel.setIcon(model.getIcon());
		cateDAO.update(newModel);
		
	}
	@Override
	public CategoryModel findOne(int id) {
		return cateDAO.findOne(id);
	}
	@Override
	public void delete(int id) {
		cateDAO.delete(id);
		
	}


}
