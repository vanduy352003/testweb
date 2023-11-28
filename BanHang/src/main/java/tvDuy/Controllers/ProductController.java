package tvDuy.Controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import tvDuy.Models.CategoryModel;
import tvDuy.Models.ProductModel;
import tvDuy.Services.IProductService;
import tvDuy.Services.ProductServiceImpl;
import tvDuy.utils.Constant;
import tvDuy.utils.UploadUtils;
import tvDuy.Services.CategoryServiceImpl;
import tvDuy.Services.ICategoryService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
		* 50)

@WebServlet(urlPatterns = { "/listproduct", "/product/findproductbycate", "/admin-insertproduct" })
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IProductService proServ = new ProductServiceImpl();
	ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if (url.contains("listproduct"))
			findAll(req, resp);
		else if (url.contains("findproductbycate"))
			findBaseOnCateID(req, resp);
		else if (url.contains("admin-insertproduct"))
			gotoinsert(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if (url.contains("admin-insertproduct")) {
			insert(req, resp);
		}
	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		ProductModel model = new ProductModel();
		try {
			// Lấy dữ liệu từ jsp bằng beanUtils
			BeanUtils.populate(model, req.getParameterMap());
			// Xử lý trường image 
			if (req.getPart("imageLink").getSize() != 0) {
				String fileName = "" + System.currentTimeMillis();
				model.setImageLink(
						UploadUtils.processUpload("imageLink", req, Constant.DIR + "\\products\\", fileName));
			}
			model.setAmount(1);
			model.setCategory(cateService.findOne(model.getCategoryID()));
			proServ.insert(model);

			// thong bao
			req.setAttribute("product", model);
			req.setAttribute("message", "Add success");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Add fails");
		}
		
		resp.sendRedirect(req.getContextPath() + "/listproduct");
	}

	private void gotoinsert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<CategoryModel> listcate = cateService.findAll();
		req.setAttribute("listcate", listcate);
		RequestDispatcher rd = req.getRequestDispatcher("/views/product/addproduct.jsp");
		rd.forward(req, resp);
	}

	private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ProductModel> list = proServ.findAll();
		req.setAttribute("listproduct", list);

		RequestDispatcher rd = req.getRequestDispatcher("/views/product/listproduct.jsp");
		rd.forward(req, resp);
	}

	private void findBaseOnCateID(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int cateID = Integer.parseInt(req.getParameter("categoryID"));
		List<ProductModel> list = proServ.findBaseOnCateID(cateID);
		req.setAttribute("listproduct", list);

		RequestDispatcher rd = req.getRequestDispatcher("/views/product/listproduct.jsp");
		rd.forward(req, resp);
	}
}
