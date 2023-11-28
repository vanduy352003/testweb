package api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import tvDuy.Models.CategoryModel;
import tvDuy.Services.CategoryServiceImpl;
import tvDuy.Services.ICategoryService;
import tvDuy.utils.HttpUtil;

@WebServlet(urlPatterns = { "/api-admin-category" })
public class CategoryApiController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ICategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		List<CategoryModel> cateModel = HttpUtil.of(req.getReader()).listModel(CategoryModel.class);
		cateModel = categoryService.findAll();
		mapper.writeValue(resp.getOutputStream(), cateModel);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CategoryModel cateModel = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
		categoryService.insert(cateModel);
		mapper.writeValue(resp.getOutputStream(), "{Đã thêm thành công}");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CategoryModel cateModel = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
		categoryService.update(cateModel);
		mapper.writeValue(resp.getOutputStream(), "{Đã cập nhật thành công}");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CategoryModel cateModel = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
		categoryService.delete(cateModel.getCategoryID());
		mapper.writeValue(resp.getOutputStream(), "{Đã xóa}");
	}

}
