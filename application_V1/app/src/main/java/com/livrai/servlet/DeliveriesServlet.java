package com.livrai.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.livrai.bean.Delivery;
import com.livrai.bean.User;
import com.livrai.dao.DeliveryDao;
import com.livrai.dao.UserDao;

/**
 * Servlet implementation class DeliveriesServlet
 */
public class DeliveriesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        User user = new UserDao().getUserById(userId);
        DeliveryDao deliveryDao = new DeliveryDao();
        List<Delivery> deliveries = user.isAdmin() ? deliveryDao.getAllDeliveries()
                : deliveryDao.getAllDeliveriesByUserId(user.getId());
        List<Delivery> upcomingDeliveries = new ArrayList<Delivery>();
        List<Delivery> pastDeliveries = new ArrayList<Delivery>();

        Map<Integer, String> clientNames = new HashMap<Integer, String>();
        if (user.isAdmin()) {
            List<User> clients = new UserDao().getAllNonAdminUsers();
            for (User client : clients) {
                clientNames.put(client.getId(), client.getName());
            }
        }

        for (Delivery delivery : deliveries) {
            if (Delivery.ACCEPTED.equals(delivery.getStatus()) || Delivery.PENDING.equals(delivery.getStatus())) {
                upcomingDeliveries.add(delivery);
            } else {
                pastDeliveries.add(delivery);
            }

            if (user.isAdmin()) {
                delivery.setClientName(clientNames.get(delivery.getUserId()));
            }
        }
        request.setAttribute("upcomingDeliveries", upcomingDeliveries);
        request.setAttribute("pastDeliveries", pastDeliveries);

        request.getServletContext().getRequestDispatcher("/WEB-INF/deliveries.jsp").forward(request, response);
    }

}
