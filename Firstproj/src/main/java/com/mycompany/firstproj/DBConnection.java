
package com.mycompany.firstproj;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Asus
 */
public class DBConnection {
    private static interface Singleton {

        final DBConnection INSTANCE = new DBConnection();
    }
    private final BasicDataSource dataSource;

    private  DBConnection() {
        String dbName = ("studentdb3");
        String user = ("root");
        String password = ("root");
        String url = ("jdbc:mysql://localhost:3306/");
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url+dbName);
        ds.setUsername(user);
        ds.setPassword(password);

        this.dataSource = ds;
}
    
 public static synchronized Connection getConnection() throws SQLException {
        return Singleton.INSTANCE.dataSource.getConnection();
    }
}


