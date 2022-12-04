package jpa.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@RequiredArgsConstructor // конструктор, принимающий значения @NonNull полей
@NoArgsConstructor // генерирует конструктор без аргументов
@Entity
public class Contact extends BaseIdentify{

    @Getter
    @Setter
    @NonNull
    @Column(nullable = false)
    private String email;

    @Getter
    @Setter
    @NonNull
    @Column(nullable = false, length = 30)
    private String phone;

    @Getter
    @Setter
    private String address;

}
