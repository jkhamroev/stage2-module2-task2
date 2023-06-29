package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String url = session.getAttribute("user") == null ? "/login.jsp" : "/user/hello.jsp";
        try {
            response.sendRedirect(request.getContextPath() + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Users users = Users.getInstance();
        if (users.getUsers().contains(login) && (password == null || password.trim().isEmpty())) {
            request.getSession().setAttribute("user", 1);
            try {
                request.getServletContext().getRequestDispatcher("/user/hello.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
