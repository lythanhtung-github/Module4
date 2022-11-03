package com.codegym.service.transfer;

import com.codegym.model.Transfer;
import com.codegym.service.IGeneralService;

import java.math.BigDecimal;

public interface ITransferService extends IGeneralService<Transfer> {
    BigDecimal getSumFeesAmount();
}
