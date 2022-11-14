package com.codegym.model.dto;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.service.customer.ICustomerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepositDTO {

    private long id;

    private long customerId;

    @Pattern(regexp = "^\\d+$", message = "Số tiền gửi phải là số")
    private String transactionAmount;

//    public Deposit toDeposit() {
//        return new Deposit()
//                .setId(id)
//                .setCustomer(customerId.toCustomer())
//                .setTransactionAmount(new BigDecimal(transactionAmount));
//    }
}

