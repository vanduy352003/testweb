package tvDuy.Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tvDuy.Models.CategoryModel;
import tvDuy.Services.CategoryServiceImpl;
import tvDuy.Services.ICategoryService;

@WebServlet(urlPatterns = {"/category/listcate", "/category/insert", "/category/update", "/category/delete"})
public class CategoryController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ICategoryService cateService = new CategoryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		
		if (url.contains("insert")) {
			RequestDispatcher rd = req.getRequestDispatcher("/views/category/addcate.jsp");
			rd.forward(req, resp);
		}
		else if (url.contains("listcate"))
			findAll(req, resp);
		else if (url.contains("update"))
			findOne(req, resp);
		else if (url.contains("delete"))
			delete(req, resp);
	}


	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("categoryID"));
		
		try {
			cateService.delete(id);
			req.setAttribute("message", "Xóa thành công");		
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Xóa thất bại");
		}
		RequestDispatcher rd = req.getRequestDispatcher("listcate");
		rd.forward(req, resp);
	}


	private void findOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("categoryID"));
		CategoryModel model = cateService.findOne(id);

		req.setAttribute("cate", model);
 
		RequestDispatcher rd = req.getRequestDispatcher("/views/category/updatecate.jsp");
		rd.forward(req, resp);
		//Trả về ko tham so thì redirect, có tham số thì request
	}


	private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<CategoryModel> list = cateService.findAll();
		
		req.setAttribute("listcate", list);
		
		RequestDispatcher rd = req.getRequestDispatcher("/views/category/listcate.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		
		if (url.contains("insert"))
			insert(req, resp);
		else if (url.contains("update"))
			update(req, resp);
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		Mã hóa UTF-8
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

//		Nhận dữ liệu từ form
		Integer categoryID = Integer.parseInt(req.getParameter("categoryID"));
		String categoryName = req.getParameter("categoryName");
		String icon = req.getParameter("icon");

//		Khoi tao model
		CategoryModel model = new CategoryModel();
		model.setCategoryID(categoryID);
		model.setCategoryName(categoryName);
		model.setIcon(icon);

//		Gọi phương thức insert

		cateService.update(model);

//		Chuyen trang
		resp.sendRedirect(req.getContextPath()+"/category/listcate");
	}


	private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//		Mã hóa UTF-8
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

//		Nhận dữ liệu từ form
		String categoryName = req.getParameter("categoryName");
		String icon = req.getParameter("icon");

//		Khoi tao model
		CategoryModel model = new CategoryModel();
		model.setCategoryName(categoryName);
		model.setIcon(icon);

//		Gọi phương thức insert

		cateService.insert(model);

//		Chuyen trang
		findAll(req, resp);
		//resp.sendRedirect("listcate");

	}
}
