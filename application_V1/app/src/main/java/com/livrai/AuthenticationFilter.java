package com.livrai;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.livrai.bean.User;
import com.livrai.dao.UserDao;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

    private static final String[] PUBLIC_ROUTES = { "/login", "/logout" };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        Integer userId = session != null ? (Integer) session.getAttribute("userId") : null;
        User user = userId != null ? new UserDao().getUserById(userId) : null;

        if (user != null && user.isAdmin()) {
            request.setAttribute("admin", true);
        }

        boolean isPublicRoute = false;
        for (String publicRoute : PUBLIC_ROUTES) {
            if (publicRoute.equals(httpRequest.getServletPath())) {
                isPublicRoute = true;
                break;
            }
        }

        if (user != null || isPublicRoute) {
            chain.doFilter(request, response);
        } else {
            request.getServletContext().getRequestDispatcher("/login").forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
