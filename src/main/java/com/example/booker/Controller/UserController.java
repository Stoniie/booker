package com.example.booker.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.booker.Model.Restaurant;
import com.example.booker.Model.User;
import com.example.booker.Repository.RestaurantRepository;
import com.example.booker.Repository.UserRepository;

@Controller
@RequestMapping
public class UserController 
{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @PostMapping("/saveNewUser")
    public ModelAndView saveNewUser(@Valid @ModelAttribute User user, BindingResult bindingResult)
    {
        boolean err = false;
        err = bindingResult.hasErrors();
        user.setSecret(UUID.randomUUID().toString());
        ModelAndView mv = new ModelAndView();
        
        if(!err)
        {
            try
            {
                userRepository.save(user); 
            }
            catch(Exception e)
            {
                err = true;
                bindingResult.addError(new ObjectError("global Error", e.getCause().getCause().getLocalizedMessage()));
            }
           
        }
        if(!err)
        {
            mv.setViewName("redirect:/home");
        }
        else
        {
            mv.setViewName("redirect:/newUser");
        }
        
        return mv;
    }

    @GetMapping("/newUser")
    public ModelAndView newUser()
    {
        ModelAndView mv = new ModelAndView("NewUser");
        User user = new User();
        mv.addObject("user", user);
        return mv;
    }

    @PostMapping("/validateUser")
    public ModelAndView validateUser(@ModelAttribute User logUser)
    {
        ModelAndView mv = new ModelAndView();
        Optional<User> optUser = userRepository.findByEmailAndPassword(logUser.getEmail(),logUser.getPassword());

        if(optUser.isPresent())
        {
            User user = optUser.get();
            mv.addObject("user", user);
            mv.addObject("desk", user.getDesk());
            mv.setViewName("redirect:/user/" + user.getUserId());   
        }
        else
        {
            mv.setViewName("redirect:/home");
        }
        
        return mv;
    }

    @GetMapping("/user/{userId}")
    public ModelAndView user(@PathVariable int userId)
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("UserHome");
        Optional<User> optUser = userRepository.findById(userId);
        if(optUser.isPresent())
        {
            mv.addObject("user", optUser.get());
        }
        return mv;
    }


    @GetMapping("/deskList/{userId}")
    public ModelAndView restaurantHome(@PathVariable int userId)
    {
        ModelAndView mv = new ModelAndView("UserDeskList");
        
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent())
        {
        mv.addObject("user", user.get());
        mv.addObject("desks", user.get().getDesk());
        }
        return mv;
    }


    @GetMapping("/user/{userId}/allRest")
    public ModelAndView getAllRes(@PathVariable int userId)
    {
        ModelAndView mv = new ModelAndView("UserRestaurantList");
        List<Restaurant> restaurants = (List<Restaurant>) restaurantRepository.findAll();
        Optional<User> user = userRepository.findById(userId);
        mv.addObject("restaurants", restaurants);
        mv.addObject("user", user);
        return mv;
    }

    @GetMapping("/user/allRest/{resId}")
    public ModelAndView getResById(@PathVariable int resId)
    {
        ModelAndView mv = new ModelAndView("DeskList");
        Optional<Restaurant> optRestaurant = restaurantRepository.findById(resId);
        if(optRestaurant.isPresent())
        {
        Restaurant restaurant = optRestaurant.get();
        mv.addObject("restaurant", restaurant);
        mv.addObject("desks", restaurant.getDesk());
        
        }
        
        return mv;
    }


    
}
