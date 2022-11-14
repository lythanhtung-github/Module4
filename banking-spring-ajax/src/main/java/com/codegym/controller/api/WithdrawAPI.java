//package com.codegym.controller.api;
//
//import com.codegym.model.Customer;
//import com.codegym.model.Withdraw;
//import com.codegym.model.dto.WithdrawDTO;
//import com.codegym.service.customer.ICustomerService;
//import com.codegym.utils.AppUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.math.BigDecimal;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/withdraws")
//public class WithdrawAPI {
//    @Autowired
//    private ICustomerService customerService;
//
//    @Autowired
//    private AppUtils appUtils;
//
//    @PostMapping
//    public ResponseEntity<Customer> withdraw(@Validated  @RequestBody WithdrawDTO withdrawDTO){
//        Long customerId = withdrawDTO.getCustomerId();
//        Optional<Customer> customerOptional = customerService.findById(customerId);
//        if(!customerOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        BigDecimal transactionAmount = new BigDecimal(withdrawDTO.getTransactionAmount());
//        if(customerOptional.get().getBalance().compareTo(transactionAmount) < 0) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        Withdraw withdraw = new Withdraw();
//        withdraw.setTransactionAmount(transactionAmount);
//        withdraw.setCustomer(customerOptional.get());
//
//        Customer newCustomer = customerService.withdraw(customerOptional.get(), withdraw);
//
//        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
//    }
//}
