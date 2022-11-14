package com.codegym.service.transfer;

import com.codegym.model.Transfer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ITransferService {
    List<Transfer> findAll();

    Transfer getById(Long id);

    Optional<Transfer> findById(Long id);

    Transfer save(Transfer transfer);

    BigDecimal getSumFeesAmount();

    void remove(Long id);
}
