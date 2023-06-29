package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/user/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // ignore
        throw new UnsupportedOperationException();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user") == null) {
            try {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                filterChain.doFilter(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}