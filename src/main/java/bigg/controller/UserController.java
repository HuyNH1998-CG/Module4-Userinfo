package bigg.controller;

import bigg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import bigg.services.IUserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }

    @PostMapping("/home")
    public ModelAndView result(@Valid @ModelAttribute User user, BindingResult bindingResult){
        user.validate(user,bindingResult);
        ModelAndView modelAndView;
        if(bindingResult.hasFieldErrors()){
            modelAndView = new ModelAndView("/index");
        } else {
            modelAndView = new ModelAndView("/result");
            userService.save(user);
        }
        return modelAndView;
    }
}
