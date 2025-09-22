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

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10,  // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet(urlPatterns = { "/admin/category/add" })
public class CategoryAddController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/views/admin/add-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            String categoryName = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));

            Category category = new Category();
            category.setCatename(categoryName);
            category.setStatus(status);

            // Xử lý file upload
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            Part part = req.getPart("images");
            if (part != null && part.getSize() > 0) {
                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                int index = filename.lastIndexOf(".");
                String ext = (index > 0) ? filename.substring(index + 1) : "";
                String newFileName = System.currentTimeMillis() + "." + ext;

                part.write(uploadPath + File.separator + newFileName);
                category.setIcon(UPLOAD_DIRECTORY + "/" + newFileName);
            }

            cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Lỗi upload file: " + e.getMessage());
        }
    }

}
