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
    private String tableNum;
    
    public WaitingEntity toEntity() {
        WaitingEntity waitingEntity = WaitingEntity.builder()
                .waitingId(waitingId)
                .name(name)
                .phone(phone)
                .email(email)
                .people(people)
                .tableNum(tableNum)
                .build();
        return waitingEntity;
    }

    @Builder
    public WaitingDto(Long waitingId, String name,  String phone, String email, int people, String tableNum) {
        this.waitingId = waitingId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.people = people;
        this.tableNum = tableNum;
    }
}
