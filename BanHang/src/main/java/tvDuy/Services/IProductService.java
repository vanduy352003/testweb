package tvDuy.Services;

import java.util.List;

import tvDuy.Models.ProductModel;

public interface IProductService {
	List<ProductModel> findAll();

	List<ProductModel> findBaseOnCateID(int cateID);

	void insert(ProductModel model);

	void update(ProductModel model);
	ProductModel findOne(int id);

	int countByCateID(int id);

	List<ProductModel> count();
	List<ProductModel> find10Top();
}
