package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transfers")
public class Transfer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    private Customer sender;

    @Min(0)
    @Column(name = "transaction_amount", precision = 12, scale = 0, nullable = false)
    private BigDecimal transactionAmount;

    @Min(0)
    private int fees;

    @Min(value = 100000, message = "Số tiền muốn chuyển phải lớn hơn 100.000 VNĐ")
    @Column(name = "fees_amount", precision = 12, scale = 0, nullable = false)
    private BigDecimal feesAmount;

    @Min(0)
    @Column(name = "transfer_amount", precision = 12, scale = 0, nullable = false)
    private BigDecimal transferAmount;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id", nullable = false)
    private Customer recipient;
}