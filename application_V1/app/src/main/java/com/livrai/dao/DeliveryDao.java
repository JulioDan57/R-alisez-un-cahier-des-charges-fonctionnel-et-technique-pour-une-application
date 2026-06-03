package com.livrai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.livrai.bean.Delivery;

public class DeliveryDao extends AbstractDao {

    public Void createDelivery(final Delivery delivery) {
        return execute(new SqlQuery<Void>() {
            @Override
            public Void execute(Connection connection) throws SQLException {
                String sql = "INSERT INTO delivery (userId, volume, weight, status) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, delivery.getUserId());
                statement.setInt(2, delivery.getVolume());
                statement.setInt(3, delivery.getWeight());
                statement.setString(4, Delivery.PENDING);
                statement.executeUpdate();
                statement.close();
                return null;
            }
        });
    }

    public Delivery getDeliveryById(final int id) {
        return execute(new SqlQuery<Delivery>() {
            @Override
            public Delivery execute(Connection connection) throws SQLException {
                Delivery delivery = null;
                String sql = "SELECT userId, volume, weight, price, status FROM delivery WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    delivery = new Delivery(id, result.getInt("userId"), result.getInt("volume"),
                            result.getInt("weight"), result.getDouble("price"), result.getString("status"));
                }
                result.close();
                statement.close();
                return delivery;
            }
        });
    }

    public List<Delivery> getAllDeliveries() {
        return execute(new SqlQuery<List<Delivery>>() {
            @Override
            public List<Delivery> execute(Connection connection) throws SQLException {
                List<Delivery> deliveries = new ArrayList<Delivery>();
                String sql = "SELECT id, userId, volume, weight, price, status FROM delivery";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    Delivery delivery = new Delivery(result.getInt("id"), result.getInt("userId"),
                            result.getInt("volume"), result.getInt("weight"), result.getDouble("price"),
                            result.getString("status"));
                    deliveries.add(delivery);
                }
                result.close();
                statement.close();
                return deliveries;
            }
        });
    }

    public List<Delivery> getAllDeliveriesByUserId(final int userId) {
        return execute(new SqlQuery<List<Delivery>>() {
            @Override
            public List<Delivery> execute(Connection connection) throws SQLException {
                List<Delivery> deliveries = new ArrayList<Delivery>();
                String sql = "SELECT id, userId, volume, weight, price, status FROM delivery WHERE userId = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, userId);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    Delivery delivery = new Delivery(result.getInt("id"), result.getInt("userId"),
                            result.getInt("volume"), result.getInt("weight"), result.getDouble("price"),
                            result.getString("status"));
                    deliveries.add(delivery);
                }
                result.close();
                statement.close();
                return deliveries;
            }
        });
    }

    public Void acceptDeliveryById(final int id) {
        return execute(new SqlQuery<Void>() {
            @Override
            public Void execute(Connection connection) throws SQLException {
                String sql = "UPDATE delivery SET status = ? WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, Delivery.ACCEPTED);
                statement.setInt(2, id);
                statement.executeUpdate();
                statement.close();
                return null;
            }
        });
    }

    public Void rejectDeliveryById(final int id) {
        return execute(new SqlQuery<Void>() {
            @Override
            public Void execute(Connection connection) throws SQLException {
                String sql = "UPDATE delivery SET status = ? WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, Delivery.REJECTED);
                statement.setInt(2, id);
                statement.executeUpdate();
                statement.close();
                return null;
            }
        });
    }

    public Void billDeliveryById(final int id, final double price) {
        return execute(new SqlQuery<Void>() {
            @Override
            public Void execute(Connection connection) throws SQLException {
                String sql = "UPDATE delivery SET status = ?, price = ? WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, Delivery.FINISHED);
                statement.setDouble(2, price);
                statement.setInt(3, id);
                statement.executeUpdate();
                statement.close();
                return null;
            }
        });
    }

    public Void deleteDelivery(final int id) {
        return execute(new SqlQuery<Void>() {
            @Override
            public Void execute(Connection connection) throws SQLException {
                String sql = "DELETE FROM delivery WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                statement.executeUpdate();
                statement.close();
                return null;
            }
        });
    }
}
