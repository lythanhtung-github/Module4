package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.Withdraw;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {
    List<Customer> findAllByFullNameLikeOrEmailOrPhoneOrAddressLike(String valueSearch);

    List<Customer> findAllByDeletedIsFalse();

    void deposit(Deposit deposit, Customer customer);

    boolean withdraw(Withdraw withdraw, Customer customer);

    Customer transfer(Transfer transfer);

    Boolean existsByIdEquals(long id);

    List<Customer> findAllByIdNot(Long id);
}