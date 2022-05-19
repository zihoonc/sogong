package com.kanari.booking.dto;

import com.kanari.booking.domain.BookingEntity;
import lombok.Getter;

@Getter
public class BookingResponseDto {
    private Long bookingId;
    private String name;
    private String phone;
    private String email;
    private int people;
    private String bookDay;
    private String time;
    private int tableNum;
    private String arrive;

    public BookingResponseDto(BookingEntity entity){
        this.bookingId = entity.getBookingId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.people = entity.getPeople();
        this.bookDay = entity.getBookDay();
        this.time = entity.getTime();
        this.tableNum = entity.getTableNum();
        this.arrive = entity.getArrive();
    }
}
