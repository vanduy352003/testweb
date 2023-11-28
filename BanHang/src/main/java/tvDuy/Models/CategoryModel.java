package tvDuy.Models;

import java.io.Serializable;

public class CategoryModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int categoryID;
	private String categoryName;
	private String icon;
	
	public CategoryModel() {
		super();
	}
	
	public CategoryModel(int cateoryID, String categoryName, String icon) {
		super();
		this.categoryID = cateoryID;
		this.categoryName = categoryName;
		this.icon = icon;
	}

	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int cateoryID) {
		this.categoryID = cateoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "CategoryModel [cateoryID=" + categoryID + ", categoryName=" + categoryName + ", icon=" + icon + "]";
	}
	
	
}
