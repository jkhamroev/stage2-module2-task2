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
    public void init(ServletConfig config) {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String url = session.getAttribute("user") == null ? "/login.jsp" : "/user/hello.jsp";
        try {
            req.getServletContext().getRequestDispatcher(url).forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String url = "/login.jsp";
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Users users = Users.getInstance();
        if (users.getUsers().contains(login) && !password.trim().isEmpty()) {
            url = "/user/hello.jsp";
            req.getSession().setAttribute("user", 1);
        }

        try {
            req.getServletContext().getRequestDispatcher(url).forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
