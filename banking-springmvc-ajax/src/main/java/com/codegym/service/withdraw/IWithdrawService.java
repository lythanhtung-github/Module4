package com.codegym.service.withdraw;

import com.codegym.model.Withdraw;

import java.util.List;
import java.util.Optional;

public interface IWithdrawService {
    List<Withdraw> findAll();

    Withdraw getById(Long id);

    Optional<Withdraw> findById(Long id);

    Withdraw save(Withdraw withdraw);

    void remove(Long id);
}
