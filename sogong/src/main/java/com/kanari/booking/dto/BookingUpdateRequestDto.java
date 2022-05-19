package com.kanari.booking.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookingUpdateRequestDto {

    private  String arrive;

    @Builder
    public BookingUpdateRequestDto(String arrive) {
        this.arrive = arrive;
    }
}
