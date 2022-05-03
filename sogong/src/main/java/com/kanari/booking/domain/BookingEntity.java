package com.kanari.booking.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "booking")
@NoArgsConstructor
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @Column(length = 255, nullable = false)
    private String name;
    @Column(length = 255, nullable = false)
    private String phone;
    @Column(length = 255, nullable = false)
    private String email;
    @Column(length = 255, nullable = false)
    private int people;
    @Column(length = 255, nullable = false)
    private String bookDay;
    @Column(length = 255, nullable = false)
    private String time;

    @Builder
    public BookingEntity(Long bookingId, String name, String phone, String email, int people, String bookDay, String time) {
        this.bookingId = bookingId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.people = people;
        this.bookDay = bookDay;
        this.time = time;
    }
}
