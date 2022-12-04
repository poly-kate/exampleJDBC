package base;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// org.postgresql.util.PSQLException: ОШИБКА: ошибка синтаксиса
//  ВАЖНО: пользователь "qqq" не прошёл проверку подлинности (по паролю)
public class JDBCBase {
    private static final String CONNECTION_STR =
            "jdbc:postgresql://localhost:5432/lessons";
    private static final String LOGIN = "jjd";
    private static final String PWD = "jjd";

    // "jdbc:sqlite:lessons.db";

    public static void main(String[] args) {
        // JDBC API {throw new DriverEcx("Метод не был реализован");}
        // 1. Регистрация драйвера: Class.forName("org.postgresql.Driver");
        // 2. Connection (строка подключения, логин, пароль)
        // 3. Statement / PreparedStatement для выполнения запросов
        // PreparedStatement: предотвращение sql инъекций,
        // повторное использование запроса,
        // вызов группы запросов будет выполняться быстрее
        // 4. Получение результата:
        // ResultSet (необходимо соединение с сервером БД)
        // JdbcRowSet (необходимо соединение с сервером БД)
        // CachedRowSet (кэширует результат в памяти,
        // не требует соединения с сервером БД)
        // FilteredRowSet
        // WebRowSet
        Map<String, Integer> booksInfo = new HashMap<>();
        booksInfo.put("Java 15", 1400);
        booksInfo.put("Java 14", 1612);
        booksInfo.put("Java 13", 800);


        try {
            createTable();
            // bufferQueries(booksInfo);
            jdbcRowSet();
            CachedRowSet cached = getRowSet();
            while (cached.next()){
                System.out.println("title" +
                        cached.getString("title"));
                System.out.println("pc" +
                        cached.getInt("page_count"));
            }
            // JavaBean

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable() throws ClassNotFoundException, SQLException {
        // book: id title page_count
        // название тип данных доп характеристики
        String createSql = "CREATE TABLE IF NOT EXISTS book(" +
                "id SERIAL PRIMARY KEY," +
                "title VARCHAR(50) NOT NULL," +
                "page_count INTEGER NOT NULL);";

//        String createSql = "CREATE TABLE IF NOT EXISTS 'book'(" +
//                "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "'title' TEXT NOT NULL," +
//                "'page_count' INTEGER NOT NULL);";
        // регистрация драйвера - загрузка класса
        Class.forName("org.postgresql.Driver");
        try (Connection connection =
                     DriverManager.getConnection(CONNECTION_STR, LOGIN, PWD)){
            // PreparedStatement
            try (Statement statement = connection.createStatement()){
                // для запросов типа create, возвращает true / false
                statement.execute(createSql);
                // для обновления, удаления, добавления данных,
                // возвращает количество модифицированных строк
                // statement.executeUpdate(createSql);
                // для получения данных, вернет ResultSet
                // statement.executeQuery(createSql);
            }
        }
    }

    private static void insertIntoBook(String title, int pageCount) throws ClassNotFoundException, SQLException {
         // String insertSql = "INSERT INTO book(title, page_count) " +
         //       "VALUES(" + title + ", " + pageCount + ")";
        String insertSql = "INSERT INTO book(title, page_count) " +
               "VALUES(?, ?)";
        Class.forName("org.postgresql.Driver");
        try (Connection connection =
                     DriverManager.getConnection(CONNECTION_STR, LOGIN, PWD)){
            try (PreparedStatement statement =
                    connection.prepareStatement(insertSql)){
                statement.setString(1, title);
                statement.setInt(2, pageCount);
                int res = statement.executeUpdate();
                System.out.println("res " + res);
            }
        }
    }

    private static void getAllBooks() throws ClassNotFoundException, SQLException {
        String selectAll = "SELECT * FROM book";
        Class.forName("org.postgresql.Driver");
        try (Connection connection =
                     DriverManager.getConnection(CONNECTION_STR, LOGIN, PWD)) {
            try (Statement statement = connection.createStatement()){
                // executeQuery()
                ResultSet resultSet = statement.executeQuery(selectAll);
                while (resultSet.next()){
                    String title = resultSet.getString("title");
                    int pageCount = resultSet.getInt("page_count");
                    System.out.println("title: " + title);
                    System.out.println("pageCount: " + pageCount);
                }
            }
        }
    }

    private static void bufferQueries(Map<String, Integer> booksInfo) throws ClassNotFoundException, SQLException {
        String insertSql = "INSERT INTO book(title, page_count) " +
                "VALUES(?, ?)";
        Class.forName("org.postgresql.Driver");
        try (Connection connection =
                     DriverManager.getConnection(CONNECTION_STR, LOGIN, PWD)){
            try (PreparedStatement statement =
                         connection.prepareStatement(insertSql)){
                for (Map.Entry<String, Integer> entry : booksInfo.entrySet()) {
                    statement.setString(1, entry.getKey());
                    statement.setInt(2, entry.getValue());
                    statement.addBatch(); // добавляет запрос в пакет
                }
                // executeBatch отправляет пакет запросов на выполнение,
                // возвращает количество обновлений для каждого запроса
                int[] res = statement.executeBatch();
                System.out.println("res: " + Arrays.toString(res));
            }
        }
    }

    private static void jdbcRowSet() throws SQLException {
        // new JdbcRowSetImpl(conn);
        JdbcRowSet rowSet =
                RowSetProvider.newFactory().createJdbcRowSet();
        rowSet.setUrl(CONNECTION_STR);
        rowSet.setUsername(LOGIN);
        rowSet.setPassword(PWD);
        rowSet.setCommand("SELECT * FROM book");
        rowSet.execute();

        rowSet.moveToInsertRow();
        rowSet.updateString("title", "JdbcRowSet");
        rowSet.updateInt("page_count", 2);
        rowSet.insertRow();
        rowSet.execute();

        rowSet.beforeFirst();
        System.out.println("Извлечение RowSet");
        while (rowSet.next()){
            System.out.println("title: " +
                    rowSet.getString("title"));
            System.out.println("page count: " +
                    rowSet.getInt("page_count"));
        }

    }

    private static CachedRowSet getRowSet() throws SQLException {
        CachedRowSet cachedRowSet =
                RowSetProvider.newFactory().createCachedRowSet();
        cachedRowSet.setUrl(CONNECTION_STR);
        cachedRowSet.setUsername(LOGIN);
        cachedRowSet.setPassword(PWD);
        cachedRowSet.setCommand("SELECT * FROM book");
        cachedRowSet.execute();
        return cachedRowSet;
    }


}


