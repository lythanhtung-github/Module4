package com.codegym.controller;

import com.codegym.model.dto.CustomerDTO;
import com.codegym.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping({"/customers",""})
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/list");

        List<CustomerDTO> customers = customerService.getAllCustomerDTO();

        modelAndView.addObject("customers", customers);

        return modelAndView;
    }
}
