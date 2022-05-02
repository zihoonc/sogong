package com.kanari.booking.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "customer")
@NoArgsConstructor
@DynamicInsert
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 255, nullable = false)
    private String name;
    @Column(length = 255, nullable = false)
    private String pwd;
    @Column(length = 255, nullable = false)
    private String phone;
    @Column(length = 255, nullable = true)
    @ColumnDefault("'cus'")
    private String role;

    @Builder
    public CustomerEntity(Long id, String name, String pwd, String phone, String role) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.phone = phone;
        this.role = role;
    }
}
