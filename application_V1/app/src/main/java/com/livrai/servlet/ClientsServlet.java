package com.livrai.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.livrai.bean.User;
import com.livrai.dao.UserDao;

public class ClientsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDao userDao = new UserDao();
        List<User> clients = userDao.getAllNonAdminUsers();

        request.setAttribute("clients", clients);
        request.getServletContext().getRequestDispatcher("/WEB-INF/clients.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        userDao.addUser(new User(0, email, name, password, false));

        doGet(request, response);
    }

}
