package jpa.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contact.class)
public abstract class Contact_ extends jpa.entity.BaseIdentify_ {

	public static volatile SingularAttribute<Contact, String> address;
	public static volatile SingularAttribute<Contact, String> phone;
	public static volatile SingularAttribute<Contact, String> email;

	public static final String ADDRESS = "address";
	public static final String PHONE = "phone";
	public static final String EMAIL = "email";

}

