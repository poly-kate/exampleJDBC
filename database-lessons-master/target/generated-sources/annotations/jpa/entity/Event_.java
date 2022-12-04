package jpa.entity;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Event.class)
public abstract class Event_ extends jpa.entity.BaseIdentify_ {

	public static volatile SingularAttribute<Event, LocalDateTime> start;
	public static volatile ListAttribute<Event, Student> students;
	public static volatile SingularAttribute<Event, String> title;

	public static final String START = "start";
	public static final String STUDENTS = "students";
	public static final String TITLE = "title";

}

