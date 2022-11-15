package com.codegym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class WithdrawDTO implements Validator {
    private long id;

    private long customerId;

    private String transactionAmount;

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return WithdrawDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        WithdrawDTO withdrawDTO = (WithdrawDTO) target;

        String transactionAmount = withdrawDTO.getTransactionAmount();

        if (transactionAmount != null && transactionAmount.length() > 0) {
            if (transactionAmount.length() > 9){
                errors.rejectValue("transactionAmount", "transactionAmount.max");
                return;
            }

            if (!transactionAmount.matches("(^$|[0-9]*$)")){
                errors.rejectValue("transactionAmount", "transactionAmount.number");
            }

            float transactionAmountFloat= Float.parseFloat(transactionAmount);

            if (transactionAmountFloat % 10 > 0) {
                errors.rejectValue("transactionAmount", "transactionAmount.decimal");
            }

        } else {
            errors.rejectValue("transactionAmount",  "transactionAmount.null");
        }
    }
}
