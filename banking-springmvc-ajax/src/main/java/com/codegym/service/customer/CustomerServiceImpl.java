package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.Withdraw;
import com.codegym.repository.CustomerRepository;
import com.codegym.repository.DepositRepository;
import com.codegym.repository.TransferRepository;
import com.codegym.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private WithdrawRepository withdrawRepository;
    @Autowired
    private TransferRepository transferRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findAllByDeletedIsFalse() {
        return customerRepository.findAllByDeletedIsFalse();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
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
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void deposit(Deposit deposit, Customer customer) {
        deposit.setId(0L);
        deposit.setCustomer(customer);
        depositRepository.save(deposit);

        BigDecimal newBalance = deposit.getTransactionAmount();
        customerRepository.incrementBalance(customer.getId(), newBalance);
    }

    @Override
    public void withdraw(Withdraw withdraw, Customer customer) {
        withdraw.setId(0L);
        withdraw.setCustomer(customer);
        withdrawRepository.save(withdraw);

        BigDecimal newBalance = withdraw.getTransactionAmount();
        customerRepository.incrementBalance(customer.getId(), newBalance);
    }

    @Override
    public void transfer(Transfer transfer) {

        Long senderId = transfer.getSender().getId();
        Long recipientId = transfer.getRecipient().getId();

        BigDecimal transferAmount = transfer.getTransferAmount();
        BigDecimal transactionAmount = transfer.getTransactionAmount();

        customerRepository.reduceBalance(senderId, transactionAmount);

        customerRepository.incrementBalance(recipientId, transferAmount);

        transferRepository.save(transfer);
    }
}
