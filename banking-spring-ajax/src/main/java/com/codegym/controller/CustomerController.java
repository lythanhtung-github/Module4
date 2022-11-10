package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.dto.DepositDTO;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.deposit.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDepositService depositService;


    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/list");

        List<Customer> customers = customerService.findAllByDeletedIsFalse();

        modelAndView.addObject("customers", customers);

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreatePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/create");

        Customer customer = new Customer();
        modelAndView.addObject("customer", customer);

        return modelAndView;
    }

    @GetMapping("/edit/{customerId}")
    public ModelAndView showUpdatePage(@PathVariable long customerId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/update");

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()){
            modelAndView.addObject("error", true);
        }

        Customer customer = customerOptional.get();

        modelAndView.addObject("customer", customer);

        return modelAndView;
    }

    @GetMapping("/deposit/{customerId}")
    public ModelAndView showDepositPage(@PathVariable long customerId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/deposit");

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
        }
        else {
            Deposit deposit = new Deposit();
            modelAndView.addObject("deposit", deposit);
            modelAndView.addObject("customer", customerOptional.get());
        }

        return modelAndView;
    }

    @GetMapping("/transfer/{senderId}")
    public ModelAndView showTransferPage(@PathVariable long senderId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/transfer");

        Optional<Customer> customerOptional = customerService.findById(senderId);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
        }
        else {

            List<Customer> recipients = customerService.findAllByIdNot(senderId);

            Transfer transfer = new Transfer();
            modelAndView.addObject("transfer", transfer);
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("sender", customerOptional.get());
        }

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView doCreate(@Validated @ModelAttribute Customer customer, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/create");

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("error", true);
            return modelAndView;
        }

        try {
            customer.setId(0L);
            customer.setBalance(new BigDecimal(0L));
            customerService.save(customer);

            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("success", true);
        } catch (Exception e) {
            modelAndView.addObject("error", true);
        }

        return modelAndView;
    }

    @PostMapping("/edit/{customerId}")
    public ModelAndView doUpdate(@PathVariable long customerId, @ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/update");

        Boolean existsCustomer = customerService.existsByIdEquals(customerId);

        if (!existsCustomer){
            modelAndView.addObject("error", true);
        }

        customer.setId(customerId);

        Customer newCustomer = customerService.save(customer);

        modelAndView.addObject("customer", newCustomer);

        return modelAndView;
    }

    @PostMapping("/deposit/{customerId}")
    public ModelAndView doCreate(@PathVariable long customerId, @Validated @ModelAttribute DepositDTO depositDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/deposit");

        new DepositDTO().validate(depositDTO, bindingResult);

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("deposit", new Deposit());
            modelAndView.addObject("customer", customerOptional.get());
            modelAndView.addObject("error", true);
            return modelAndView;
        }

        try {
            Customer customer = customerOptional.get();

            Deposit deposit = new Deposit();
            BigDecimal transactionAmount = new BigDecimal(depositDTO.getTransactionAmount());
            deposit.setTransactionAmount(transactionAmount);
            deposit.setCustomer(customer);

            Customer newCustomer = customerService.deposit(customer, deposit);

            modelAndView.addObject("deposit", new Deposit());
            modelAndView.addObject("customer", newCustomer);
            modelAndView.addObject("success", true);
        } catch (Exception e) {
            modelAndView.addObject("deposit", new Deposit());
            modelAndView.addObject("customer", customerOptional.get());
            modelAndView.addObject("error", true);
        }

        return modelAndView;
    }


    @PostMapping("/transfer/{senderId}")
    public ModelAndView doTransfer(@PathVariable long senderId, @ModelAttribute Transfer transfer) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/transfer");

        Optional<Customer> senderOptional = customerService.findById(senderId);

        if (!senderOptional.isPresent()) {
            modelAndView.addObject("error", true);
        }

        Optional<Customer> recipientOptional = customerService.findById(senderId);

        if (!recipientOptional.isPresent()) {
            modelAndView.addObject("error", true);
        }

        Customer sender = senderOptional.get();

        Customer recipient = recipientOptional.get();

        List<Customer> recipients = customerService.findAllByIdNot(senderId);

        BigDecimal currentBalanceSender = sender.getBalance();

        BigDecimal transferAmount = transfer.getTransferAmount();
        long fees = 10;
        BigDecimal feesAmount = transferAmount.multiply(new BigDecimal(fees)).divide(new BigDecimal(100L));
        BigDecimal transactionAmount = transferAmount.add(feesAmount);

        if (currentBalanceSender.compareTo(transactionAmount) < 0) {
            modelAndView.addObject("deposit", new Transfer());
            modelAndView.addObject("sender", sender);
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "SỐ dư người gửi không đủ thực hiện giao dịch");
            return modelAndView;
        }

        try {
            transfer.setId(0L);
            transfer.setSender(sender);
            transfer.setFees(fees);
            transfer.setFeesAmount(feesAmount);
            transfer.setTransactionAmount(transactionAmount);

            Customer newSender = customerService.transfer(transfer);

            modelAndView.addObject("deposit", new Transfer());
            modelAndView.addObject("sender", newSender);
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("success", true);
        } catch (Exception e) {
            modelAndView.addObject("deposit", new Transfer());
            modelAndView.addObject("sender", sender);
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("error", true);
        }

        return modelAndView;
    }

}

