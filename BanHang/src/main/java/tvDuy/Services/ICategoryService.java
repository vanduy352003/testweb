package tvDuy.Services;

import java.util.List;

import tvDuy.Models.CategoryModel;

public interface ICategoryService {
	List<CategoryModel> findAll();
	void insert(CategoryModel model);
	void update(CategoryModel model);
	void delete(int id);
	CategoryModel findOne(int id);
	
}
