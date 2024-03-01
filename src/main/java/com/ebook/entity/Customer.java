package com.ebook.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    private String email;

    private String fullName;

    private String address;

    private String city;

    private String country;

    private String phone;

    private String zipCode;

    private String password;

    @Column(name = "register_date")
    private LocalDate registerDate;
}
