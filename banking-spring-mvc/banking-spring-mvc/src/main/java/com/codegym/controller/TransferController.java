package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Transfer;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.transfer.ITransferService;
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
@RequestMapping("/transfers")
public class TransferController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ITransferService transferService;

    @ModelAttribute("provinces")
    public Iterable<Customer> customers() {
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
            modelAndView.setViewName("error/404");
            return modelAndView;
        } else {
            List<Customer> recipients = customerService.findAllByIdNot(senderId);
            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("sender", senderOptional.get());
            modelAndView.addObject("recipients", recipients);
        }
        return modelAndView;
    }

    @PostMapping("/create/{senderId}")
    public ModelAndView transfer(@PathVariable long senderId,
                                 @Validated @ModelAttribute Transfer transfer,
                                 BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        Optional<Customer> senderOptional = customerService.findById(senderId);

        if (!senderOptional.isPresent()) {
            modelAndView.setViewName("error/404");
            return modelAndView;
        }

        Customer sender = senderOptional.get();

        List<Customer> recipients = customerService.findAllByIdNot(senderId);

        Customer recipient = transfer.getRecipient();
        if (recipient == null) {
            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("sender", sender);
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("error", true);
            modelAndView.addObject("errorAction", "Người nhận không hợp lệ");
            return modelAndView;
        }

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("sender", sender);
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("transfer", transfer);
            modelAndView.addObject("error", true);
            modelAndView.setViewName("transfer/create");
            return modelAndView;
        }

        BigDecimal currentBalanceSender = sender.getBalance();
        BigDecimal transferAmount = transfer.getTransferAmount();
        int fees = 10;
        BigDecimal feesAmount = transferAmount.multiply(new BigDecimal(fees)).divide(new BigDecimal(100));
        BigDecimal transactionAmount = transferAmount.add(feesAmount);

        if (currentBalanceSender.compareTo(transactionAmount) < 0) {
            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("sender", sender);
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("errorAction", "Số dư người gửi không đủ để thực hiện giao dịch");
            modelAndView.setViewName("transfer/create");
            return modelAndView;
        }
        transfer.setSender(sender);
        transfer.setTransactionAmount(transactionAmount);
        transfer.setFeesAmount(feesAmount);
        Customer newSender = customerService.transfer(transfer);
        try {
            transfer.setId(0L);
            transfer.setSender(sender);
            transfer.setFees(fees);
            transfer.setFeesAmount(feesAmount);
            transfer.setTransactionAmount(transactionAmount);

            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("sender", newSender);
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("success", true);
        } catch (Exception e) {
            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("sender", newSender);
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("errorAction", "Vui lòng hiên hệ Admin");
        }
        modelAndView.setViewName("transfer/create");
        return modelAndView;
    }
}
