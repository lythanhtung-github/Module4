package com.codegym.model;

import com.codegym.model.dto.CustomerDTO;
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
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private String address;

    @Column(precision = 12, scale = 0, nullable = false, updatable = false)
    private BigDecimal balance;

    @OneToMany(targetEntity = Deposit.class, mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Deposit> deposits;

    @OneToMany(targetEntity = Withdraw.class, mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Withdraw> withdraws;

    @OneToMany(targetEntity = Transfer.class, mappedBy = "sender", fetch = FetchType.EAGER)
    private Set<Transfer> senders;

    @OneToMany(targetEntity = Transfer.class, mappedBy = "recipient", fetch = FetchType.EAGER)
    private Set<Transfer> recipients;

    public CustomerDTO toCustomerDTO () {
        return new CustomerDTO()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setAddress(address)
                .setBalance(balance.toString());
    }
}
