package com.algoExpert.demo.ExceptionHandler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccessDeniedController {

    @GetMapping("/accessDenied")
    @ExceptionHandler(value = AccessDeniedException.class)
    private ModelAndView confirmAccount(){
        return new ModelAndView("access-denied");
    }
}
