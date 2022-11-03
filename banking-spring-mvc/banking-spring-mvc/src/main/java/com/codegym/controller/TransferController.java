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
            List    <Customer> recipients = customerService.findAllByIdNot(senderId);
            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("sender", senderOptional.get());
            modelAndView.addObject ( "recipients", recipients );
        }
        return modelAndView;
    }

    @PostMapping("/create/{senderId}")
    public ModelAndView transfer(@PathVariable long senderId,
                                 @Validated @ModelAttribute Transfer transfer,
                                 BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transfer/create");

        Optional<Customer> senderOptional = customerService.findById(senderId);

        if (!senderOptional.isPresent()) {
            modelAndView.addObject("errorAction", "ID người gửi không hợp lệ");
            return modelAndView;
        }

        try{
            Optional<Customer> recipientOptional = customerService.findById(transfer.getRecipient().getId());
            if(!recipientOptional.isPresent()){
                modelAndView.addObject("errorAction", "Người nhận không hợp lệ");
                return modelAndView;
            }
        }catch (Exception e){
            modelAndView.addObject("errorAction", "Người nhận không hợp lệ");
            return modelAndView;
        }

        Customer sender = senderOptional.get();

        List<Customer> recipients = customerService.findAllByIdNot(senderId);

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("sender", sender);
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("transfer", transfer);
            modelAndView.addObject("error", true);
            return modelAndView;
        }

        BigDecimal currentBalanceSender = sender.getBalance();

        BigDecimal transferAmount = transfer.getTransferAmount();
        int fees = 10;
        BigDecimal feesAmount = transferAmount.multiply(new BigDecimal(fees)).divide(new BigDecimal(100L));
        BigDecimal transactionAmount = transferAmount.add(feesAmount);

        if (currentBalanceSender.compareTo(transactionAmount) < 0) {
            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("sender", sender);
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("error", true);
            modelAndView.addObject("errorAction", "SỐ dư người gửi không đủ thực hiện giao dịch");
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
