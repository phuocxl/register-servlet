package org.example.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.entity.Category;
import org.example.service.CategoryService;
import org.example.service.impl.CategoryServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/admin/category/list" })
public class CategoryeListController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        List<Category> cateList = cateService.getAll();
        req.setAttribute("cateList", cateList);
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/views/admin/list-category.jsp");
        dispatcher.forward(req, resp);
    }

    @WebServlet(urlPatterns = { "/admin/category/edit" })
    public class CategoryeEditController extends HttpServlet {
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
    }
}
