package pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

// DriverManager / DataSource
public class C3P0DataSource {
    private static ComboPooledDataSource cpds =
            new ComboPooledDataSource();

//    static {
//        try {
//            cpds.setDriverClass("org.postgresql.Driver");
//            cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/lessons");
//            cpds.setUser("jjd");
//            cpds.setPassword("jjd");
//            // изначальное количество соединений в пуле,
//            // 3 по умолчанию
//            cpds.setInitialPoolSize(5);
//            // минимальное количество соединений в пуле,
//            // 3 по умолчанию
//            cpds.setMinPoolSize(4);
//            // максимально количество соединений в пуле,
//            // 15 по умолчанию
//            cpds.setMaxPoolSize(10);
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//        }
//    }

    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }
}
