package com.codegym.repository;

import com.codegym.model.Customer;

import javax.persistence.*;

public class CustomerRepository implements ICustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean insertWithStoredProcedure(Customer customer) {
        String sql = "Insert_Customer";
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(sql)
                .registerStoredProcedureParameter("firstName", String.class, ParameterMode.IN)
                .setParameter("firstName", customer.getFirstName())
                .registerStoredProcedureParameter("lastName", String.class, ParameterMode.IN)
                .setParameter("lastName", customer.getLastName());
        return query.execute();
    }
}