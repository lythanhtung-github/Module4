package com.codegym.repository;

import com.codegym.model.Customer;
import com.codegym.model.dto.ICustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByDeletedIsFalse();
    List<Customer> findAllByIdNot(long id);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByEmailAndIdIsNot(String email, Long id);

    @Modifying
    @Query("UPDATE Customer AS c " +
            "SET c.balance = c.balance + :transactionAmount " +
            "WHERE c.id = :customerId"
    )
    void incrementBalance(@Param("transactionAmount") BigDecimal transactionAmount, @Param("customerId") long customerId);

    @Modifying
    @Query("UPDATE Customer AS c " +
            "SET c.balance = c.balance - :transactionAmount " +
            "WHERE c.id = :customerId"
    )
    void reduceBalance(@Param("transactionAmount") BigDecimal transactionAmount, @Param("customerId") long customerId);

    @Modifying
    @Query("UPDATE Customer AS c SET c.deleted = true WHERE c.id = :customerId")
    void softDelete(@Param("customerId") long customerId);

    List<ICustomerDTO> getAllICustomerDTOByDeletedIsFalse();
    List<Customer> getAllICustomerByDeletedIsFalse();
}
