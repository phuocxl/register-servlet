package org.example.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.model.entity.Category;
import org.example.service.CategoryService;
import org.example.service.impl.CategoryServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.example.constant.Constant.UPLOAD_DIRECTORY;

@WebServlet(urlPatterns = { "/admin/category/edit" })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10,  // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class CategoryEditController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Category category = cateService.get(Integer.parseInt(id));
        req.setAttribute("category", category);
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/views/admin/edit-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Category category = new Category();

        try {
            // Lấy id và name
            String id = req.getParameter("id");
            if (id != null && !id.isEmpty()) {
                category.setCateid(Integer.parseInt(id));
            }
            category.setCatename(req.getParameter("name"));

            // Xử lý file upload (field="icon")
            Part part = req.getPart("icon");
            if (part != null && part.getSize() > 0) {
                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                int index = filename.lastIndexOf(".");
                String ext = (index > 0) ? filename.substring(index + 1) : "";
                String newFileName = System.currentTimeMillis() + "." + ext;

                // Tạo thư mục nếu chưa có
                String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                // Lưu file
                part.write(uploadPath + File.separator + newFileName);

                category.setIcon(UPLOAD_DIRECTORY + "/" + newFileName);
            } else {
                category.setIcon(null);
            }

            // Gọi service update
            cateService.edit(category);

            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Lỗi khi cập nhật category: " + e.getMessage());
        }
    }

}
