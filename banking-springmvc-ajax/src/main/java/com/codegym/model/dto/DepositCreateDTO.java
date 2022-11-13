package com.codegym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepositCreateDTO {
    private Long id;

    @Pattern(regexp = "^\\d+$", message = "Sô tiền gửi phải là số")
    private String transactionAmount;

    private long customerId;
}
