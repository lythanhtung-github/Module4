package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Calculator {
    @RequestMapping("/calculate")
    public String calculate(ModelMap model, @RequestParam(name = "number1", defaultValue = "0") double number1, @RequestParam(name = "number2", defaultValue = "0") double number2, @RequestParam(name = "calculation", defaultValue = "") String calcul) {

        double result = 0;
        switch (calcul) {
            case "(+)":
                result = number1 + number2;
                calcul = "(+)";
                break;
            case "(-)":
                result = number1 - number2;
                calcul = "(-)";
                break;
            case "(X)":
                result = number1 * number2;
                calcul = "(X)";
                break;
            case "(/)":
                result = number1 / number2;
                calcul = "(/)";
                break;
        }
        model.addAttribute("number1", number1);
        model.addAttribute("number2", number2);
        model.addAttribute("calcul", calcul);
        model.addAttribute("result", result);
        return "index";
    }
}
