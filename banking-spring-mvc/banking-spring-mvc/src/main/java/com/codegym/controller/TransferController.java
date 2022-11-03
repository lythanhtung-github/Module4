package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.deposit.IDepositService;
import com.codegym.service.transfer.ITransferService;
import com.codegym.service.withdraw.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/transfers")
public class TransferController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDepositService depositService;

    @Autowired
    private IWithdrawService withdrawService;

    @Autowired
    private ITransferService transferService;

    @ModelAttribute("provinces")
    public Iterable<Customer> customers(){
        return customerService.findAll();
    }

    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transfer/list");
        List<Transfer> transfers = transferService.findAll();
        BigDecimal getAllFeesAmount = transferService.getSumFeesAmount();
        modelAndView.addObject("transfers", transfers);
        modelAndView.addObject("allFeesAmount", getAllFeesAmount);
        return modelAndView;
    }

    @GetMapping("/create/{senderId}")
    public ModelAndView showCreatePage(@PathVariable long senderId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transfer/create");

        Optional<Customer> senderOptional = customerService.findById(senderId);

        if (!senderOptional.isPresent()) {
            modelAndView.addObject("error", "Id khách hàng không hợp lệ");
        }
        else {
            Iterable<Customer> recipients = customerService.findAllByIdNot(senderId);
            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("customer", senderOptional.get());
            modelAndView.addObject ( "recipients", recipients );
        }
        return modelAndView;
    }
}
