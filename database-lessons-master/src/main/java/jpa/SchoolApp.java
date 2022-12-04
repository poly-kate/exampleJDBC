package jpa;



import jpa.dao.GroupDao;
import jpa.entity.Group;
import jpa.specification.GroupByDuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class SchoolApp {
    public static void main(String[] args) {
        // "entityManager" значение name в <persistence-unit name="entityManager">
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("entityManager");
        EntityManager manager = factory.createEntityManager();
        // new - только что создан, не управляется EntityManager
        // managed - перешел под управление EntityManager
        // unmanaged/detached
        // removed - remove()

        Group group = new Group("jjd", 5.5, 70000);

        manager.getTransaction().begin();
        manager.persist(group);

        manager.getTransaction().commit();

        GroupDao groupDao = new GroupDao(manager);
        System.out.println(groupDao.getByPK(567)); // null
        groupDao.deleteByPK(567);

        System.out.println(groupDao.getAll());

        List<Group> groups = groupDao.getBySpecification(new GroupByDuration(3));
        System.out.println(groups);

        // JDBC(Driver)
        // JPA(например, Hibernate)
        // Spring Data JPA

    }
}
