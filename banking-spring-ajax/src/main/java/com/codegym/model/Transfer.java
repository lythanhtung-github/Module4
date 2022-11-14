package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transfers")
@Accessors(chain = true)
public class Transfer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    private Customer sender;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id", nullable = false)
    private Customer recipient;

    @Column(name = "transfer_amount", precision = 12, scale = 0, nullable = false)
    private BigDecimal transferAmount;

    @Column(nullable = false)
    private long fees;

    @Column(name = "fees_amount", precision = 12, scale = 0, nullable = false)
    private BigDecimal feesAmount;

    @Column(name = "transaction_amount", precision = 12, scale = 0, nullable = false)
    private BigDecimal transactionAmount;
}
