package com.livrai.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.livrai.bean.User;
import com.livrai.dao.DeliveryDao;
import com.livrai.dao.UserDao;

/**
 * Servlet implementation class DeliveryServlet
 */
public class DeliveryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        User user = new UserDao().getUserById(userId);

        if (user.isAdmin()) {
            int id = Integer.valueOf(request.getParameter("id"));
            String action = request.getParameter("action");

            DeliveryDao deliveryDao = new DeliveryDao();
            if ("ACCEPT".equals(action)) {
                deliveryDao.acceptDeliveryById(id);
            } else if ("REJECT".equals(action)) {
                deliveryDao.rejectDeliveryById(id);
            } else if ("BILL".equals(action)) {
                Double price = Double.valueOf(request.getParameter("price"));
                deliveryDao.billDeliveryById(id, price);
            }
        }

        response.sendRedirect(request.getContextPath() + "/livraisons");
    }

}
