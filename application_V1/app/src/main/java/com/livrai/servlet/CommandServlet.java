package com.livrai.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.livrai.bean.Delivery;
import com.livrai.dao.DeliveryDao;

public class CommandServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/command.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int clientId = (Integer) request.getSession().getAttribute("userId");
        int volume = Integer.valueOf(request.getParameter("volume"));
        int weight = Integer.valueOf(request.getParameter("weight"));

        DeliveryDao deliveryDao = new DeliveryDao();
        deliveryDao.createDelivery(new Delivery(-1, clientId, volume, weight, 0, null));

        doGet(request, response);
    }

}
