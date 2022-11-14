package com.codegym.service.deposit;

import com.codegym.model.Deposit;

import java.util.List;
import java.util.Optional;

public interface IDepositService {
    List<Deposit> findAll();

    Deposit getById(Long id);

    Optional<Deposit> findById(Long id);

    Deposit save(Deposit deposit);

    void remove(Long id);
}
