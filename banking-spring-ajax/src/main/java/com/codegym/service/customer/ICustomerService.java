package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.Withdraw;
import com.codegym.model.dto.CustomerDTO;
import com.codegym.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {
    List<CustomerDTO> findAllICustomerDTOByDeletedIsFalse();

    List<Customer> findAllByIdNot(long id);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByEmailAndIdIsNot(String email, Long id);

    Boolean existsByIdEquals(long id);

    Customer deposit(Customer customer, Deposit deposit);

    Customer withdraw(Customer customer, Withdraw withdraw);

    Customer transfer(Transfer transfer);

    void softDelete(@Param("customerId") long customerId);

//    List<RecipientDTO> getAllRecipientDTO(long senderId);

}
