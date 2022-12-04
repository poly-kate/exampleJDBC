package jpa.dao;

import jpa.entity.Group;
import jpa.entity.Group_;
import jpa.specification.Specification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class GroupDao implements Dao<Group, Integer>{

    private EntityManager manager;

    public GroupDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Group group) {
        manager.persist(group);
    }

    @Override
    public void update(Group group) {
        manager.merge(group);
    }

    @Override
    public Group getByPK(Integer integer) {
        return manager.find(Group.class, integer);
    }

    @Override
    public void delete(Group group) {
        // java.lang.IllegalArgumentException, если group == null
        manager.remove(group);
    }

    @Override
    public void deleteByPK(Integer integer) {
        Group group = getByPK(integer); // null
        if (group != null) {
            delete(group);
        }
    }


    public List<Group> getAll(){
        /* 1. named queries */
        /*TypedQuery<Group> query =
                manager.createNamedQuery("Group.getAll", Group.class);
        List<Group> groups = query.getResultList();*/ // query.getResultStream();

        /* 2. JPQL */
        /*Query query = manager.createQuery("SELECT g FROM Group g");
        // Query query = manager.createNativeQuery(SQL)
        List<Group> groups = (List<Group>) query.getResultList();*/

        // Driver -> JDBC -> JPA (Hibernate) + Spring Data

        /* 3. Criteria API */
        // SELECT * FROM school_group
        // SELECT g FROM Group g
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery =
                criteriaBuilder.createQuery(Group.class); //
        Root<Group> root = criteriaQuery.from(Group.class);
        criteriaQuery.select(root);

        TypedQuery<Group> query = manager.createQuery(criteriaQuery);
        List<Group> groups = query.getResultList();

        return groups;
    }


    public Group getGroupByTitle(String titleFromArgs){
        // "SELECT * FROM school_group WHERE title = jjd" ;
        /* 1. named queries */
        // "SELECT g FROM Group g WHERE g.title = :titleFromArgs"
        /*TypedQuery<Group> query =
                manager.createNamedQuery("Group.findByTitle", Group.class);
        query.setParameter("titleFromArgs", titleFromArgs);
        Group group = query.getSingleResult();*/

        /* 2. JPQL */
        /*TypedQuery<Group> query = manager.createQuery("SELECT g FROM Group g WHERE g.title = :titleFromArgs", Group.class);
        query.setParameter("titleFromArgs", titleFromArgs);
        Group group = query.getSingleResult();*/

        /* 3. Criteria API */
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = builder.createQuery(Group.class);
        // корневой объект
        Root<Group> root = criteriaQuery.from(Group.class);
        // "SELECT * FROM school_group WHERE title = " + titleFromArgs ;
        // блок WHERE
        Predicate condition = builder.equal(root.get(Group_.title), titleFromArgs);
//         Predicate condition2 = builder.equal(root.get(Group_.title), titleFromArgs);
//         Predicate and = builder.and(condition, condition2);
        criteriaQuery.select(root).where(condition);

        TypedQuery<Group> query = manager.createQuery(criteriaQuery);
        Group group = query.getSingleResult();

        return group;
    }

    @Override
    public List<Group> getBySpecification(Specification<Group> specification) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = builder.createQuery(Group.class);
        // корневой объект
        Root<Group> root = criteriaQuery.from(Group.class);

        Predicate condition = specification.getPredicate(root, builder);

        criteriaQuery.where(condition);

        return manager.createQuery(criteriaQuery).getResultList();
    }




}
