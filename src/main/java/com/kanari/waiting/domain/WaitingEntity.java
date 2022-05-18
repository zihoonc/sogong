package com.kanari.waiting.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "waiting")
@NoArgsConstructor
public class WaitingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long waitingId;
    @Column(length = 255, nullable = false)
    private String name;
    @Column(length = 255, nullable = false)
    private String phone;
    @Column(length = 255, nullable = false)
    private String email;
    @Column(length = 255, nullable = false)
    private int people;

    @Builder
    public WaitingEntity(Long waitingId, String name, String phone, String email, int people) {
        this.waitingId = waitingId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.people = people;
    }
}
