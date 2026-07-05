package com.api.cortex.model.entity.event;


import com.api.cortex.model.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 500)
    private String eventText;

    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;



}
