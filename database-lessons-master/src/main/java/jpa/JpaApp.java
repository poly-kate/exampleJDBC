package jpa;

import jpa.entity.Message;
import jpa.utils.HibernateUtils;
import org.hibernate.Session;

import java.time.LocalDateTime;

public class JpaApp {
    public static void main(String[] args) {
        Message.MessageKey key = new Message.MessageKey();
        key.setAuthorName("Имя");
        key.setAuthorSurname("Фамилия");

        Message message = new Message(); // transient
        message.setText("Текст сообщения");
        message.setSent(LocalDateTime.now());
        message.setKey(key);

        System.out.println(message);

        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        // persistent
        session.persist(message); // JPA
        // session.save(message); // Hibernate
        message.setText("Новый текст сообщения");

        session.getTransaction().commit();
        session.close();
        // detached

        message.setText("Самый новый текст сообщения");

        // получение по первичному ключу get / load
        session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        // persistent
        // key : messageFromDB

        Message messageFromDB = session.get(Message.class, key);
        System.out.println("messageFromDB " + messageFromDB);
        // для обновления записи
        // session.update(message); Hibernate // A different object with the same identifier value was already associated with the session
        session.merge(message); // JPA

        // session.delete(message);
        session.delete(messageFromDB); // удаление

        System.out.println(messageFromDB);

        session.getTransaction().commit();
        session.close();

        Session s1 = HibernateUtils.getSessionFactory().openSession();
        s1.close();

        //  <property name="current_session_context_class">thread</property>
        Session s2 = HibernateUtils.getSessionFactory().getCurrentSession();

    }
}
