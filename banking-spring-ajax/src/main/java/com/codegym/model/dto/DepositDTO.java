package com.codegym.model.dto;

import com.codegym.model.Deposit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepositCreateDTO {

    private long id;

    private long customerId;

    @Pattern(regexp = "^\\d+$", message = "Số tiền gửi phải là số")
    private String transactionAmount;

}

