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

    public BookingEntity toEntity() {
        BookingEntity bookingEntity = BookingEntity.builder()
                .bookingId(bookingId)
                .name(name)
                .email(email)
                .phone(phone)
                .people(people)
                .bookDay(bookDay)
                .build();
        return bookingEntity;
    }

    @Builder
    public BookingDto(Long bookingId, String name, String email, int people, String bookDay) {
        this.bookingId = bookingId;
        this.name = name;
        this.email = email;
        this.people = people;
        this.bookDay = bookDay;
    }
}
