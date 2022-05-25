package com.kanari.booking.dto;

import com.kanari.booking.domain.CustomerEntity;
import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomerDto {

    private Long id;
    private String name;
    private String pwd;
    private String phone;
    private String email;
    private String role;

    public CustomerEntity toEntity() {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .id(id)
                .name(name)
                .pwd(pwd)
                .phone(phone)
                .email(email)
                .role(role)
                .build();
        return customerEntity;
    }

    @Builder
    public CustomerDto(Long id, String name, String pwd, String phone, String email, String role) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }
}
