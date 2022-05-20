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

    public BookingEntity toEntity() {
        BookingEntity bookingEntity = BookingEntity.builder()
                .bookingId(bookingId)
                .name(name)
                .email(email)
                .phone(phone)
                .people(people)
                .bookDay(bookDay)
                .time(time)
                .tableNum(tableNum)
                .build();
        return bookingEntity;
    }

    @Builder
    public BookingDto(Long bookingId, String name, String email,  String phone, int people, String bookDay, String time, int tableNum) {
        this.bookingId = bookingId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.people = people;
        this.bookDay = bookDay;
        this.time = time;
        this.tableNum = tableNum;
    }
}
