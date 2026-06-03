package com.livrai.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractDao {

    private Connection connection;

    private Connection getConnection() {
        if (connection == null) {
            loadDatabase();
        }
        return connection;
    }

    protected <T> T execute(SqlQuery<T> query) {
        try {
            return query.execute(getConnection());
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'exécution de la requête SQL", e);
        }
    }

    protected void loadDatabase() {
        try {
            // Charger le driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL introuvable !");
            e.printStackTrace();
        }

        try {
            // Connexion à la base livrai sur localhost
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/livrai?useSSL=false&serverTimezone=UTC",
                    "livrai_user",
                    "motdepasse123"
            );
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données !");
            e.printStackTrace();
        }
    }

    protected interface SqlQuery<T> {
        T execute(Connection connection) throws SQLException;
    }

}
