package jpa.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Student.class)
public abstract class Student_ extends jpa.entity.BaseIdentify_ {

	public static volatile SingularAttribute<Student, String> surname;
	public static volatile SingularAttribute<Student, Contact> contact;
	public static volatile SingularAttribute<Student, String> name;
	public static volatile ListAttribute<Student, Event> events;
	public static volatile SingularAttribute<Student, Group> group;

	public static final String SURNAME = "surname";
	public static final String CONTACT = "contact";
	public static final String NAME = "name";
	public static final String EVENTS = "events";
	public static final String GROUP = "group";

}

