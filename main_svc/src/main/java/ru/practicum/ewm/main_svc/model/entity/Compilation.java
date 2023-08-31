package ru.practicum.ewm.main_svc.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "compilation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(max = 50)
    @Column(nullable = false, unique = true)
    String title;

    @Column
    Boolean pinned;
}
