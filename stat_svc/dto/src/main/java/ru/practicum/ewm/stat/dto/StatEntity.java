package ru.practicum.ewm.stat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stats")
public class StatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = false, length = 255)
    private String app;

    @Column(nullable = false, unique = false, length = 255)
    private String uri;

    @Column(nullable = false, unique = false, length = 255)
    private String ip;

    @Column(name="CREATED", nullable = false, unique = false)
    private LocalDateTime timestamp;
}
