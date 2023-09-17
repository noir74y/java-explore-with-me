package ru.practicum.ewm.main_svc.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.practicum.ewm.main_svc.model.util.enums.FriendshipStatus;

import javax.persistence.*;

@Entity
@Table(name = "friendships")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "friend1_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    User friend1;

    @ManyToOne
    @JoinColumn(name = "friend2_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    User friend2;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    FriendshipStatus status;
}
