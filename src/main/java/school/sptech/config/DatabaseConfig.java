package school.sptech.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    // Usamos System.getenv para pegar os dados do Docker/EC2
    private static final String URL = System.getenv().getOrDefault("DB_URL", "jdbc:postgresql://postgres-hyper-container:5432/ConstrucaoBancoDeDados");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "hyper");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "1234");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}