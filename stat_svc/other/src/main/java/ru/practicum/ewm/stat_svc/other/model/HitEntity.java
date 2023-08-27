package ru.practicum.ewm.stat_svc.other.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stats")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = false, length = 255)
    String app;

    @Column(nullable = false, unique = false, length = 255)
    String uri;

    @Column(nullable = false, unique = false, length = 255)
    String ip;

    @Column(name = "CREATED", nullable = false, unique = false)
    LocalDateTime timestamp;
}
