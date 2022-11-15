package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.LocationRegion;
import com.codegym.model.dto.CustomerDTO;
import com.codegym.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {
    List<CustomerDTO> getAllCustomerDTO();

    Customer save(Customer customer, LocationRegion locationRegion);

    void softDelete(long customerId);

    List<Customer> findAllByIdNot(long id);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByEmailAndIdIsNot(String email, Long id);

    Optional<CustomerDTO> getByEmailDTO(String email);

    Customer deposit(Customer customer, Deposit deposit);
}
