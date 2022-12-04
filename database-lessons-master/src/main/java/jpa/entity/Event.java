package jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Event extends BaseIdentify{
    @Getter
    @Setter
    @NonNull
    @Column(nullable = false, length = 150)
    private String title;
    @Getter
    @Setter  // unique = true
    @NonNull // LocalDate -> Date, LocalTime -> Time
    @Column(nullable = false) // columnDefinition = "SERIAL PRIMARY KEY"
    private LocalDateTime start;

    // fetch = FetchType.LAZY
    // cascade = CascadeType.PERSIST
    // {CascadeType.PERSIST, CascadeType.MERGE}
    @Getter
    @Setter
    @ManyToMany
    // @JoinTable(name = "st_event",
    //        joinColumns = @JoinColumn(name = "event_id"),
    //        inverseJoinColumns = @JoinColumn(name = "st_id"))
    private List<Student> students = new ArrayList<>();
}
