package loga.dev11.hibernate.utils;

import org.flywaydb.core.Flyway;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MigrationUtil {
    public void migration() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream("hibernate.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            String url = properties.getProperty("hibernate.connection.url");
            String username = properties.getProperty("hibernate.connection.username");
            String password = properties.getProperty("password");
            Flyway flyway = Flyway.configure().dataSource(url, username, password)
                    .locations("migration").load();
            flyway.migrate();
            System.out.println("Database migration successful!");
        } catch (IOException e) {
            System.err.println("Error reading db.properties file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error during database migration: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
