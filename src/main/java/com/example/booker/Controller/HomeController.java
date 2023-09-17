package com.example.booker.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.booker.Model.User;
import com.example.booker.Repository.UserRepository;

@Controller
public class HomeController 
{

    @Autowired
    UserRepository userRepository;

    @GetMapping("/home")
    public ModelAndView home()
    {
        ModelAndView mv = new ModelAndView("Home");
        User user = new User();
        mv.addObject("user", user);
        return mv;
    }

}
