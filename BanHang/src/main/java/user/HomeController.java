package user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tvDuy.Models.CategoryModel;
import tvDuy.Models.ProductModel;
import tvDuy.Services.CategoryServiceImpl;
import tvDuy.Services.ICategoryService;
import tvDuy.Services.IProductService;
import tvDuy.Services.ProductServiceImpl;

@WebServlet(urlPatterns = {"/home", "/listprocate", "/detail"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ICategoryService cateService = new CategoryServiceImpl();
	IProductService proService = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		
		if (url.contains("home")) {
			List<ProductModel> list1 = proService.find10Top();
			req.setAttribute("listpro", list1);
		}
		else if (url.contains("listprocate")) {
			int id = Integer.parseInt(req.getParameter("categoryID"));
			List<ProductModel> list2 = proService.findBaseOnCateID(id);
			req.setAttribute("listpro", list2);
			req.setAttribute("setactive", id);
		}
		else if (url.contains("detail")) {
			int id = Integer.parseInt(req.getParameter("id"));
			ProductModel product = proService.findOne(id);
			req.setAttribute("product", product);
			req.getRequestDispatcher("views/users/detail.jsp").forward(req, resp);
		}
		
		List<CategoryModel> list = cateService.findAll();
		req.setAttribute("listcate", list);
		List<ProductModel> countCID = proService.count();
		req.setAttribute("countCID", countCID);
		
		req.getRequestDispatcher("views/users/home.jsp").forward(req, resp);
	}
}
