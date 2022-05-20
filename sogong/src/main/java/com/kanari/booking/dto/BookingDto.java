package com.kanari.booking.dto;

import com.kanari.booking.domain.BookingEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookingDto {


    private Long bookingId;
    private String name;
    private String phone;
    private String email;
    private int people;
    private String bookDay;
    private String time;
    private int tableNum;
    private String arrive;

    public BookingEntity toEntity() {
        BookingEntity bookingEntity = BookingEntity.builder()
                .bookingId(bookingId)
                .name(name)
                .phone(phone)
                .email(email)
                .people(people)
                .time(time)
                .tableNum(tableNum)
                .arrive(arrive)
                .build();
        return bookingEntity;
    }

    @Builder
    public BookingDto(Long bookingId, String name,String phone, String email, int people, String time, int tableNum, String arrive) {
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
}
