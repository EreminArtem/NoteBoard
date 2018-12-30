package ru.eremin.noteboard;

import org.jetbrains.annotations.Nullable;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @autor Artem Eremin on 26.12.2018.
 */

@WebFilter(urlPatterns = {"/enter/*"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (isAuth(request)) filterChain.doFilter(servletRequest, servletResponse);
        else response.sendRedirect("/index");
    }

    private boolean isAuth(@Nullable final HttpServletRequest request) {
        if (request == null) return false;
        final HttpSession session = request.getSession();
        if (session == null) return false;
        final Object auth = session.getAttribute("auth");
        if (auth == null) return false;
        if (!(auth instanceof Boolean)) return false;
        return (Boolean) auth;
    }

    @Override
    public void destroy() {

    }
}
