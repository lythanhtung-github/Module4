package com.codegym.controller.api;

import com.codegym.exception.DataInputException;
import com.codegym.exception.EmailExistsException;
import com.codegym.model.Customer;
import com.codegym.model.dto.CustomerDTO;
import com.codegym.service.customer.ICustomerService;
import com.codegym.utils.AppUtils;
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
    private AppUtils appUtils;

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ResponseEntity<?> findAllByDeletedIsFalse() {
        List<CustomerDTO> customers = customerService.findAllICustomerDTOByDeletedIsFalse();
        if (customers.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getById(@PathVariable long customerId) {

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            throw new DataInputException("ID Khách hàng không hợp lệ");
        }

        return new ResponseEntity<>(customerOptional.get().toCustomerDTO(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Customer customer) {

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
    public ResponseEntity<?> update(@PathVariable Long customerId, @RequestBody Customer customer) {

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

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<?> delete(@PathVariable Long customerId) {

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            throw new DataInputException("ID khách hàng không hợp lệ");
        }

        try {
            customerService.softDelete(customerId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataInputException("Vui lòng liên hệ Administrator");
        }
    }
}
