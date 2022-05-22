package com.kanari.booking.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookingUpdateDto {
    private String arrive;
    @Builder
    public BookingUpdateDto(String arrive) {
        this.arrive = arrive;
    }


}
