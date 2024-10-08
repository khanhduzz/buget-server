package com.fjb.sunrise.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("transaction/index");
    }

}
