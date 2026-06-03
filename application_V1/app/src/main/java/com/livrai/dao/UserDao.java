package com.livrai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.livrai.bean.User;

public class UserDao extends AbstractDao {

    public Void addUser(final User user) {
        return execute(new SqlQuery<Void>() {
            @Override
            public Void execute(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection
                        .prepareStatement("INSERT INTO user (email, name, password) VALUES (?, ?, ?)");
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.executeUpdate();
                return null;
            }
        });
    }

    public User getUserById(final int id) {
        return execute(new SqlQuery<User>() {
            @Override
            public User execute(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return new User(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("name"),
                            resultSet.getString("password"), resultSet.getBoolean("admin"));
                }
                return null;
            }
        });
    }

    public User getUserByEmail(final String email) {
        return execute(new SqlQuery<User>() {
            @Override
            public User execute(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return new User(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("name"),
                            resultSet.getString("password"), resultSet.getBoolean("admin"));
                }
                return null;
            }
        });
    }

    public List<User> getAllNonAdminUsers() {
        return execute(new SqlQuery<List<User>>() {
            @Override
            public List<User> execute(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection
                        .prepareStatement("SELECT * FROM user WHERE admin IS FALSE");
                ResultSet resultSet = preparedStatement.executeQuery();
                List<User> users = new ArrayList<User>();
                while (resultSet.next()) {
                    users.add(
                            new User(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("name"),
                                    resultSet.getString("password"), resultSet.getBoolean("admin")));
                }
                return users;
            }
        });
    }

    public Void updateUser(final User user) throws SQLException {
        return execute(new SqlQuery<Void>() {
            @Override
            public Void execute(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection
                        .prepareStatement("UPDATE user SET email = ?, name = ?, password = ? WHERE id = ?");
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setInt(4, user.getId());
                preparedStatement.executeUpdate();
                return null;
            }
        });
    }

}
