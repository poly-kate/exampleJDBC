package jpa.entity;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Group.class)
public abstract class Group_ extends jpa.entity.BaseIdentify_ {

	public static volatile SingularAttribute<Group, Double> duration;
	public static volatile SingularAttribute<Group, Integer> price;
	public static volatile SingularAttribute<Group, LocalDate> start;
	public static volatile ListAttribute<Group, Student> students;
	public static volatile SingularAttribute<Group, String> title;

	public static final String DURATION = "duration";
	public static final String PRICE = "price";
	public static final String START = "start";
	public static final String STUDENTS = "students";
	public static final String TITLE = "title";

}

