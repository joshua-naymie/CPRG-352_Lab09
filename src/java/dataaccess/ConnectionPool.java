package dataaccess;

import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

public class ConnectionPool {

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            // lookup is matched to context name in context.xml in META-INF
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/userdb");
        } catch (NamingException e) {
            System.err.println("Context name does not match, cannot connect");
        }
    }

    public static synchronized ConnectionPool getInstance() {
        // follow singleton pattern
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (NullPointerException | SQLException e) {
            System.out.println(e);
        }
    }
}
