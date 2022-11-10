package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
@Accessors(chain = true)
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private String address;

    @Column(precision = 12, scale = 0, nullable = false, updatable = false)
    private BigDecimal balance;


    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Deposit> deposits;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Withdraw> withdraws;

    @OneToMany(mappedBy = "sender", fetch = FetchType.EAGER)
    private Set<Transfer> senders;

    @OneToMany(mappedBy = "recipient", fetch = FetchType.EAGER)
    private Set<Transfer> recipients;
}
