package dao;

import java.sql.SQLException;
import java.util.List;

// T - тип данных сущности
// PK - тип данных первичного ключа
public interface Dao<T, PK> {
    // добавление в таблицу
    void add(T entity) throws SQLException;
    // boolean add(T entity);
    // T add(T entity);
    // обновление записи в таблице
    void update(T entity) throws SQLException;
    // boolean update(T entity);
    // T update(T entity);
    // удаление по первичному ключу
    void removeByPK(PK id) throws SQLException;
    // T removeByPK(PK id); getByPK(PK id) -> remove
    // получение по первичному ключу
    T getByPK(PK id) throws SQLException;
    // получение всех записей
    List<T> getAll() throws SQLException;
}
