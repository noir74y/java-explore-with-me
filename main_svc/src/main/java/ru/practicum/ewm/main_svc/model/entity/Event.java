package ru.practicum.ewm.main_svc.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.practicum.ewm.main_svc.model.enums.EventState;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "initiator_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    User initiator;

    @Size(max = 250)
    @Column(nullable = false)
    String name;

    @Size(max = 1024)
    @Column(nullable = false)
    String annotation;

    @Size(max = 1024)
    @Column(nullable = false)
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    CategoryEntity category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    EventState status;

    Boolean paid;
    Boolean moderation;

    @Column(name = "participants_limit")
    Integer participantsLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    Location location;

    @Column(name = "created_on", nullable = false)
    LocalDateTime createdOn;

    @Column(name = "planned_on", nullable = false)
    LocalDateTime plannedOn;

    @Column(name = "published_on", nullable = false)
    LocalDateTime publishedOn;
}
