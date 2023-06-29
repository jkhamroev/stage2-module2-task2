package com.example.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) {
        // ignore
        throw new UnsupportedOperationException();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        try {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
