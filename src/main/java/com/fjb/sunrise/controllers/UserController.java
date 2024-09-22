package com.fjb.sunrise.controllers;

import com.fjb.sunrise.dtos.requests.UserCreateDTO;
import com.fjb.sunrise.dtos.requests.UserUpdateDTO;
import com.fjb.sunrise.dtos.responses.UserResponseDTO;
import com.fjb.sunrise.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("user/add");
        modelAndView.addObject("userCreate", new UserCreateDTO());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView add(@ModelAttribute("userCreate") UserCreateDTO userCreateDTO,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("user/add");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("userCreate", userCreateDTO);
            return modelAndView;
        }
        userService.addUser(userCreateDTO);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView info(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("user/edit");
        modelAndView.addObject("userUpdate", new UserUpdateDTO());
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        modelAndView.addObject("userResponseDTO", userResponseDTO);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Long id,
                             @ModelAttribute("userUpdate") UserUpdateDTO userUpdateDTO,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("user/edit");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("userUpdate", userUpdateDTO);
            return modelAndView;
        }
        userService.updateUser(id, userUpdateDTO);
        return modelAndView;
    }

    @GetMapping("/disable/{id}")
    public ModelAndView disable(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("user/index");
        userService.disableUser(id);
        return modelAndView;
    }
}
