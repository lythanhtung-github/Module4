package com.codegym.repository;

import com.codegym.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByFullNameLikeOrEmailOrPhoneOrAddressLike(String fullName, String email,String phone, String address);
}
