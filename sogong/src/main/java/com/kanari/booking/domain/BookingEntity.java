package com.kanari.booking.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "booking")
@NoArgsConstructor
@DynamicInsert
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
    @Column(length = 255, nullable = false)
    private int tableNum;
    @Column(length = 255, nullable = true)
    private String arrive;
    @Builder
    public BookingEntity(Long bookingId, String name, String phone, String email, int people,
                         String bookDay, String time, int tableNum, String arrive) {
        this.bookingId = bookingId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.people = people;
        this.bookDay = bookDay;
        this.time = time;
        this.tableNum = tableNum;
        this.arrive = arrive;
    }


    public void update(String arrive){
        this.arrive = arrive;
    }

}
