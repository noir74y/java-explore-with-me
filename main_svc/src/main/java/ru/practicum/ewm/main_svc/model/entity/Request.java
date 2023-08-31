package ru.practicum.ewm.main_svc.model.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.practicum.ewm.main_svc.model.enums.EventStatus;
import ru.practicum.ewm.main_svc.model.enums.RequestStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "request")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "initiator_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Person initiator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Event event;

    @Column(name = "created_on", nullable = false)
    LocalDateTime createdOn;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    RequestStatus status;
}