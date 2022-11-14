package com.codegym.model.dto;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
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

    private long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String balance;

    public CustomerDTO(long id, String fullName, String email, String phone, String address, BigDecimal balance) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance.toString();
    }

    //    public Customer toCustomer(){
//        return new Customer()
//                .setId(id)
//                .setFullName(fullName)
//                .setEmail(email)
//                .setPhone(phone)
//                .setAddress(address)
//                .setBalance(new BigDecimal(balance));
//    }
}
