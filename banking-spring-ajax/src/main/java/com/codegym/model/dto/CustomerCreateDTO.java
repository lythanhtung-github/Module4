package com.codegym.model.dto;

import com.codegym.model.Customer;
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
public class CustomerCreateDTO {

    private long id;

    //    @NotEmpty(message = "Vui lòng nhập tên đầy đủ")
//    @Size(min = 10, max = 50, message = "Họ tên có độ dài nằm trong khoảng 10-50 ký tự")
    private String fullName;

    private String email;
    private String phone;
    private String address;
    private String balance;

    public Customer toCustomer(){
        return new Customer()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setAddress(address)
                .setBalance(new BigDecimal(0L));
    }
}

