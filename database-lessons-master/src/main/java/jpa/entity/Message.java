package jpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;

@ToString // User ->  // Group
@Entity(name = "my_message")
public class Message {
    @Setter
    @Getter
    @EmbeddedId
    private MessageKey key;

    @Setter
    @Getter
    @Column(name = "message_text", nullable = false)
    @Type(type = "text")
    private String text; // message_text тип данных -> text

    @Setter
    @Getter
    @Column(nullable = false)
    private LocalDateTime sent; // sent тип данных -> timestamp

    @EqualsAndHashCode
    @Embeddable
    public static class MessageKey implements Serializable {
        static final long serialVersionUID = 1L;

        @Setter
        @Getter
        @Column(length = 20)
        private String authorName; // author_name

        @Setter
        @Getter
        @Column(length = 20)
        private String authorSurname; // author_surname
    }
}

// Необходимо создать таблицу my_message c полями:
// author_name (varchar), author_surname (varchar),
// message_text (text), sent (timestamp)

// Идентификатор записи (primary key) authorName + authorSurname