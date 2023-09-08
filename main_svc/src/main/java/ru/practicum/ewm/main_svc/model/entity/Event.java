package ru.practicum.ewm.main_svc.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.practicum.ewm.main_svc.model.util.enums.EventState;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

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

    @Size(max = 120)
    @Column(nullable = false)
    String title;

    @Size(max = 1024)
    @Column(nullable = false)
    String annotation;

    @Size(max = 1024)
    @Column(nullable = false)
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    Category category;

    @Column(nullable = false)
    String state;

    Boolean paid;

    @Column(name = "request_moderation")
    Boolean requestModeration;

    @Column(name = "participant_limit")
    Integer participantLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    Location location;

    @Column(name = "created_on")
    LocalDateTime createdOn;

    @Column(name = "event_date", nullable = false)
    LocalDateTime eventDate;

    @Column(name = "published_on")
    LocalDateTime publishedOn;
}
