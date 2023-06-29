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
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) {
        this.config = config;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String url = session.getAttribute("user") != null ? "/login.jsp" : "/user/hello.jsp";
        try {
            response.sendRedirect(request.getContextPath() + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String url = "/login.jsp";
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Users users = Users.getInstance();
        if (users.getUsers().contains(login) && password.trim().isEmpty()) {
            request.getSession().setAttribute("user", 1);
            try {
                response.sendRedirect(request.getContextPath() + "/user/hello.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                config.getServletContext().getRequestDispatcher(url).forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
