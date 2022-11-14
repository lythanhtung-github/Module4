package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String address;

    @Column(precision = 12, scale = 0, nullable = false, updatable = false)
    private BigDecimal balance;

    @OneToMany
    private List<Deposit> deposits;

    @OneToMany
    private List<Withdraw> withdraws;

    @OneToMany
    private List<Transfer> transfers;
}
