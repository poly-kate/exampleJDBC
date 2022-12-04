package jpa.entity;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.entity.Message.MessageKey;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Message.class)
public abstract class Message_ {

	public static volatile SingularAttribute<Message, String> text;
	public static volatile SingularAttribute<Message, LocalDateTime> sent;
	public static volatile SingularAttribute<Message, MessageKey> key;

	public static final String TEXT = "text";
	public static final String SENT = "sent";
	public static final String KEY = "key";

}

