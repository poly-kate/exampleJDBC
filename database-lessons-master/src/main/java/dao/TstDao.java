package dao;

import java.sql.SQLException;

public class TstDao {
    public static void main(String[] args) throws SQLException {
        // ПЕРЕД запуском отметьте папку resources как Resources Root (правой кнопкой мыши->Mark Directory As)
        BookDao bookDao = new BookDao();

        Book book = bookDao.getByPK(1);
        System.out.println("book " + book);

        book.setTitle("ВМЕСТО ПЕРВОЙ КНИГИ");

        bookDao.update(book);
        System.out.println(bookDao.getByPK(1));

        Book newBook = new Book();
        newBook.setTitle("НОВАЯ КНИГА");
        newBook.setPageCount(1000);

        bookDao.add(newBook);

        System.out.println(bookDao.getAll());
    }
}
