package com.codegym.service.customer;

import com.codegym.model.*;
import com.codegym.model.dto.CustomerDTO;
import com.codegym.model.dto.RecipientDTO;
import com.codegym.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Override
    public List<CustomerDTO> getAllCustomerDTO() {
        return customerRepository.getAllCustomerDTO();
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer, LocationRegion locationRegion) {
        LocationRegion newLocationRegion =  locationRegionRepository.save(locationRegion);
        customer.setLocationRegion(newLocationRegion);
        return customerRepository.save(customer);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {

    }
    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void softDelete(long customerId) {
        customerRepository.softDelete(customerId);
    }

    @Override
    public List<Customer> findAllByIdNot(long id) {
        return customerRepository.findAllByIdNot(id);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Optional<Customer> findByEmailAndIdIsNot(String email, Long id) {
        return customerRepository.findByEmailAndIdIsNot(email, id);
    }

    @Override
    public Optional<CustomerDTO> getByEmailDTO(String email){
        return customerRepository.getByEmailDTO(email);
    }

    @Override
    public List<RecipientDTO> getAllRecipientDTO(long senderId) {
        return customerRepository.getAllRecipientDTO(senderId);
    }
    @Override
    public Customer deposit(Customer customer, Deposit deposit) {
        BigDecimal currentBalance = customer.getBalance();
        BigDecimal transactionAmount = deposit.getTransactionAmount();

        try {
            customerRepository.incrementBalance(transactionAmount, customer.getId());

            deposit.setId(0L);
            deposit.setCustomer(customer);
            depositRepository.save(deposit);

            Optional<Customer> newCustomer = customerRepository.findById(customer.getId());
            return newCustomer.get();
        } catch (Exception e) {
            customer.setBalance(currentBalance);
            return customer;
        }
    }

    @Override
    public Customer withdraw(Customer customer, Withdraw withdraw) {
        BigDecimal currentBalance = customer.getBalance();
        BigDecimal transactionAmount = withdraw.getTransactionAmount();

        try {
            customerRepository.reduceBalance(transactionAmount, customer.getId());

            withdraw.setId(0L);
            withdraw.setCustomer(customer);
            withdrawRepository.save(withdraw);

            Optional<Customer> newCustomer = customerRepository.findById(customer.getId());

            return newCustomer.get();
        } catch (Exception e) {
            e.printStackTrace();
            customer.setBalance(currentBalance);
            return customer;
        }
    }


    @Override
    public Customer transfer(Transfer transfer) {
        customerRepository.reduceBalance(transfer.getTransactionAmount(), transfer.getSender().getId());
        customerRepository.incrementBalance(transfer.getTransferAmount(), transfer.getRecipient().getId());
        transferRepository.save(transfer);
        Optional<Customer> newSender = customerRepository.findById(transfer.getSender().getId());

        return newSender.get();
    }
}
