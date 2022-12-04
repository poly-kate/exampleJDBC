package dao;

import pool.C3P0DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements Dao<Book, Integer>{


    @Override
    public void add(Book book) throws SQLException {
        // book: id title pageCount
        String insert = "INSERT INTO book(title, page_count) VALUES(?, ?)";
        try (PreparedStatement statement = C3P0DataSource.getConnection()
                .prepareStatement(insert)){
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getPageCount());
            int res = statement.executeUpdate();
            System.out.println("Добавлено строк: " + res);
        }
    }

    @Override
    public void update(Book entity) throws SQLException {
        String update = "UPDATE book SET title = ?, page_count = ? WHERE id = ?;";
        try (PreparedStatement statement = C3P0DataSource.getConnection().prepareStatement(update)) {
            statement.setString(1, entity.getTitle());
            statement.setInt(2, entity.getPageCount());
            statement.setInt(3, entity.getId());
            int res = statement.executeUpdate();
            System.out.println("Обновлено строк: " + res);
        }
    }

    @Override
    public void removeByPK(Integer id) throws SQLException {
        String remove = "DELETE FROM book  WHERE id = ?;";
        try (PreparedStatement statement = C3P0DataSource.getConnection().prepareStatement(remove)) {
            statement.setInt(1, id);
            int res = statement.executeUpdate();
            System.out.println("Удалено строк: " + res);
        }
    }

    @Override
    public Book getByPK(Integer pk) throws SQLException {
        String select = "SELECT * FROM book WHERE id = ?";
        Book book = null;
        try (PreparedStatement statement = C3P0DataSource.getConnection().prepareStatement(select)) {
            statement.setInt(1, pk);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String title = resultSet.getString("title");
            int id = resultSet.getInt("id");
            int pageCount = resultSet.getInt("page_count");
            book = new Book();
            book.setId(id);
            book.setTitle(title);
            book.setPageCount(pageCount);
        }
        return book;
    }

    @Override
    public List<Book> getAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        String select = "SELECT * FROM book;";
        try (Statement statement = C3P0DataSource.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                int id = resultSet.getInt("id");
                int pageCount = resultSet.getInt("page_count");
                Book book = new Book();
                book.setId(id);
                book.setTitle(title);
                book.setPageCount(pageCount);
                books.add(book);
            }
        }
        return books;
    }


}
