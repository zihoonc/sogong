package com.kanari.waiting.dto;

import com.kanari.waiting.domain.WaitingEntity;
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
                .email(email)
                .phone(phone)
                .people(people)
                .build();
        return waitingEntity;
    }

    @Builder
    public WaitingDto(Long waitingId, String name, String email, int people) {
        this.waitingId = waitingId;
        this.name = name;
        this.email = email;
        this.people = people;
    }
}
