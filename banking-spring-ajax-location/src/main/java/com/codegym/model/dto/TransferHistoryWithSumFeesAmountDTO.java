package com.codegym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferHistoryWithSumFeesAmountDTO {

    private List<TransferHistoryDTO> transferHistories;
    private BigDecimal sumFeesAmount;
}
