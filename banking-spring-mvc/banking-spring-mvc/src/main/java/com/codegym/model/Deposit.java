package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "deposits")
public class Deposit extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "transaction_amount", precision = 12, scale = 0, nullable = false)
    @DecimalMin(value="100000", message = "Số tiền muốn nạp vào tài khoản phải lớn hơn 100.000 VNĐ")
    @DecimalMax(value="1000000000", message = "Số tiền muốn nạp vào tài khoản không vượt quá 1.000.000.000 VNĐ")
    private BigDecimal transactionAmount;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
}
