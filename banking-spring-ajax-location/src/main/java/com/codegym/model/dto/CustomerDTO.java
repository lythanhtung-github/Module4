package com.codegym.model.dto;

import com.codegym.model.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerDTO {
    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private BigDecimal balance;

    private LocationRegionDTO locationRegion;

    public CustomerDTO(Long id, String fullName, String email, String phone, BigDecimal balance, LocationRegion locationRegion) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
        this.locationRegion = locationRegion.toLocationRegionDTO();
    }
}
