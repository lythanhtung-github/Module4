package com.codegym.controller.api;

import com.codegym.exception.DataInputException;
import com.codegym.exception.EmailExistsException;
import com.codegym.model.Customer;
import com.codegym.model.dto.ICustomerDTO;
import com.codegym.service.customer.ICustomerService;
import com.codegym.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {
    @Autowired
    private AppUtil appUtil;

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ResponseEntity<?> findAllByDeletedIsFalse() {

//        List<ICustomerDTO> customers = customerService.getAllICustomerDTOByDeletedIsFalse();
        List<Customer> customers = customerService.getAllCustomerByDeletedIsFalse();

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getById(@PathVariable long customerId) {

        Optional<Customer> customerOptional = customerService.findById(customerId);

//        if (!customerOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        if (!customerOptional.isPresent()) {
            throw new DataInputException("Customer ID not valid");
        }

        Customer customer = customerOptional.get();

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {

        Optional<Customer> customerOptional = customerService.findByEmail(customer.getEmail());

        if (customerOptional.isPresent()) {
            throw new EmailExistsException("Customer ID not valid");
        }

        customer.setId(0L);
        customer.setBalance(BigDecimal.ZERO);
        Customer newCustomer = customerService.save(customer);

        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PatchMapping("/{customerId}")
    public ResponseEntity<Customer> update(@PathVariable Long customerId, @RequestBody Customer customer) {

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            throw new DataInputException("Customer ID not valid");
        }

        Optional<Customer> emailOptional = customerService.findByEmailAndIdIsNot(customer.getEmail(), customerId);

        if (emailOptional.isPresent()) {
            throw new EmailExistsException("Email is exists");
        }

        customer.setId(customerId);
        customer.setBalance(customerOptional.get().getBalance());
        Customer updatedCustomer = customerService.save(customer);

        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
}
