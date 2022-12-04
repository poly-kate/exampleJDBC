package jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends BaseIdentify{
    @Setter
    @Getter
    @NonNull
    @Column(nullable = false, length = 20)
    private String name;

    @Setter
    @Getter
    @NonNull
    @Column(nullable = false, length = 100)
    private String surname;

    @Setter
    @Getter // FetchType.EAGER
    @OneToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE}) // CascadeType.PERSIST
    private Contact contact;

    @Setter
    @Getter
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Group group;

    // Student events @ManyToMany   -   @ManyToMany students Event
    @Setter
    @Getter
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "students")
    private List<Event> events = new ArrayList<>();
}
