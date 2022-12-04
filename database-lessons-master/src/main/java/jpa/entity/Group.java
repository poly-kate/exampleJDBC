package jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "school_group")
@NamedQueries({                                // SELECT * FROM school_group;
   @NamedQuery(name = "Group.getAll", query = "SELECT g FROM Group g"), // JPQL
   @NamedQuery(name = "Group.findByTitle",
           query = "SELECT g FROM Group g WHERE g.title = :titleFromArgs")
        // "SELECT * FROM school_group WHERE some_title = " + title;
})
public class Group extends BaseIdentify{
    @Setter
    @Getter
    @NonNull
    @Column(nullable = false)
    private String title;
    @Setter
    @Getter
    @NonNull                   // всего      // после запятой, например, 1,5
    @Column(nullable = false, precision = 2, scale = 1)
    private double duration;
    @Setter
    @Getter
    @NonNull
    private int price;
    @Setter
    @Getter
    private LocalDate start;

    // Student @ManyToOne      @OneToMany Group
    @OneToMany(mappedBy = "group",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Student> students = new ArrayList<>();
}
