package com.kanari.booking.dto;

import com.kanari.booking.domain.WaitingEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WaitingDto {


    private Long waitingId;
    private String name;
    private String phone;
    private String email;
    private int people;
    
    public WaitingEntity toEntity() {
        WaitingEntity waitingEntity = WaitingEntity.builder()
                .waitingId(waitingId)
                .name(name)
                .phone(phone)
                .email(email)
                .people(people)
                .build();
        return waitingEntity;
    }

    @Builder
    public WaitingDto(Long waitingId, String name, String phone, String email, int people) {
        this.waitingId = waitingId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.people = people;
    }
}
