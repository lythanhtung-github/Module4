package com.codegym.controller.api;

import com.codegym.exception.DataInputException;
import com.codegym.exception.EmailExistsException;
import com.codegym.model.Customer;
import com.codegym.model.LocationRegion;
import com.codegym.model.dto.CustomerDTO;
import com.codegym.model.dto.LocationRegionDTO;
import com.codegym.model.dto.RecipientDTO;
import com.codegym.service.customer.ICustomerService;
import com.codegym.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<?> getAllByDeletedIsFalse() {
        List<CustomerDTO> customers = customerService.getAllCustomerDTO();
        if (customers.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getById(@PathVariable String customerId) {
        long cid;
        try {
            cid = Long.parseLong(customerId);
        }catch (NumberFormatException e){
            throw new DataInputException("ID Khách hàng không hợp lệ.");
        }

        Optional<Customer> customerOptional = customerService.findById(cid);

        if (!customerOptional.isPresent()) {
            throw new DataInputException("ID Khách hàng không hợp lệ.");
        }

        return new ResponseEntity<>(customerOptional.get().toCustomerDTO(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Optional<CustomerDTO> customerOptionalDTO = customerService.getByEmailDTO(customerDTO.getEmail());

        if (customerOptionalDTO.isPresent()) {
            throw new EmailExistsException("Email đã tồn tại trong hệ thống.");
        }
        LocationRegionDTO locationRegionDTO = customerDTO.getLocationRegion();
        LocationRegion locationRegion = locationRegionDTO.toLocationRegion();

        Customer customer = customerDTO.toCustomer();
        customer.setId(0L);
        customer.setBalance(BigDecimal.ZERO);
        Customer newCustomer = customerService.save(customer, locationRegion);

        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PatchMapping("/{customerId}")
    public ResponseEntity<?> update(@PathVariable Long customerId, @Validated @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Optional<Customer> customerOptional = customerService.findById(customerId);
        Customer customer = customerDTO.toCustomer();
        if (!customerOptional.isPresent()) {
            throw new DataInputException("ID khách hàng không tồn tại.");
        }

        Optional<Customer> emailOptional = customerService.findByEmailAndIdIsNot(customer.getEmail(), customerId);

        if (emailOptional.isPresent()) {
            throw new EmailExistsException("Email đã tồn tại trong hệ thống.");
        }

        customer.setId(customerId);
        customer.setBalance(customerOptional.get().getBalance());
        Customer newCustomer = customerService.save(customer);

        return new ResponseEntity<>(newCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<?> delete(@PathVariable Long customerId) {

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            throw new DataInputException("ID khách hàng không hợp lệ.");
        }

        try {
            customerService.softDelete(customerId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new DataInputException("Vui lòng liên hệ Administrator.");
        }
    }

    @GetMapping("/get-all-recipients-without-sender/{senderId}")
    public ResponseEntity<?> getAllRecipientsWithoutSender(@PathVariable long senderId) {

        Optional<Customer> senderOptional = customerService.findById(senderId);

        if (!senderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<RecipientDTO> recipientDTOS = customerService.getAllRecipientDTO(senderId);


        return new ResponseEntity<>(recipientDTOS, HttpStatus.OK);
    }
}
