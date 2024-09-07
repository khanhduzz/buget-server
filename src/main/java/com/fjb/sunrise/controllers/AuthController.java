package com.fjb.sunrise.controllers;

import com.fjb.sunrise.dtos.requests.RegisterRequest;
import com.fjb.sunrise.dtos.responses.UserResponseDTO;
import com.fjb.sunrise.services.UserService;
import com.fjb.sunrise.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView login () {
        return new ModelAndView("auth/login");
    }

    @GetMapping("/register")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(Constants.ApiConstant.REGISTER_VIEW);
        modelAndView.addObject("register", new RegisterRequest());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView doRegister(@ModelAttribute("register") RegisterRequest registerRequest,
                                   BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(Constants.ApiConstant.AUTH_VIEW);
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        modelAndView.setViewName(Constants.ApiConstant.AUTH_REDIRECT_LOGIN);
        UserResponseDTO userResponseDTO = userService.registerUser(registerRequest);
        modelAndView.addObject("user", userResponseDTO);
        return modelAndView;
    }

}
