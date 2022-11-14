package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.Withdraw;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<Customer> findAll();

    List<Customer> findAllByDeletedIsFalse();

    Optional<Customer> findById(Long id);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByEmailAndIdIsNot(String email, Long id);

    Customer save(Customer customer);

    void remove(Long id);

    void deposit(Deposit deposit, Customer customer);

    void withdraw(Withdraw withdraw, Customer customer);

    void transfer(Transfer transfer);
}
