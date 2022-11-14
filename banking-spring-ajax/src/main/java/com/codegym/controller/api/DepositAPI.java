package com.codegym.controller.api;

import com.codegym.exception.DataInputException;
import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.dto.DepositDTO;
import com.codegym.service.customer.ICustomerService;
import com.codegym.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/deposits")
public class DepositAPI {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private AppUtils appUtils;

    @PostMapping
    public ResponseEntity<?> deposit( @RequestBody DepositDTO depositDTO){
        Long customerId = depositDTO.getCustomerId();
        Optional<Customer> customerOptional = customerService.findById(customerId);
        if (!customerOptional.isPresent()) {
            throw new DataInputException("ID khách hàng không hợp lệ");
        }
        BigDecimal transactionAmount = new BigDecimal(depositDTO.getTransactionAmount());

        Deposit deposit = new Deposit();
        deposit.setTransactionAmount(transactionAmount);
        deposit.setCustomer(customerOptional.get());

        Customer newCustomer = customerService.deposit(customerOptional.get(), deposit);
        return new ResponseEntity<>(newCustomer.toCustomerDTO(), HttpStatus.CREATED);
    }
}
