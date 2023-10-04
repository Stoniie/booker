package com.example.booker.Controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.booker.Model.Desk;
import com.example.booker.Model.Restaurant;
import com.example.booker.Model.User;
import com.example.booker.Repository.DeskRepository;
import com.example.booker.Repository.RestaurantRepository;
import com.example.booker.Repository.UserRepository;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping
public class DeskController {
    
    @Autowired
    private DeskRepository deskRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/saveNewDesk/{resId}")
    public ModelAndView saveNewDesk(@Valid @ModelAttribute Desk desk, BindingResult bindingResult, @PathVariable int resId) 
    {
        ModelAndView mv = new ModelAndView();
        boolean err = false;
        bindingResult.hasErrors();
        
        Optional<Restaurant> optRes = restaurantRepository.findById(resId);

        if(!err && optRes.isPresent())
        {
            try
            {
                Restaurant res = optRes.get();
                desk.setRestaurant(res);
                deskRepository.save(desk);

            }
            catch(Exception e)
            {
                err = true;
                bindingResult.addError(new ObjectError("global Error", e.getCause().getCause().getLocalizedMessage()));
            }
        if(!err)
        {
            mv.setViewName("redirect:/restaurant/" + optRes.get().getResId());
        }
        else
        {
            mv.setViewName("redirect:/home");
        }

        }
        
        return mv;
    }

    @PostMapping("/saveDeskRes/{deskId}")
    public ModelAndView saveDeskRes(@Valid @ModelAttribute User userIn, BindingResult bindingResult, @PathVariable int deskId) 
    {
        ModelAndView mv = new ModelAndView();
        boolean err = false;
        bindingResult.hasErrors();
        
        Optional<User> optUser = userRepository.findByEmailAndPassword(userIn.getEmail(),userIn.getPassword());
        Optional<Desk> optDesk = deskRepository.findById(deskId);

        if(!err && optUser.isPresent() && optDesk.isPresent())
        {
            try
            {
                User user = optUser.get();
                Desk desk = optDesk.get();

                desk.setUser(user);
                desk.setIsRes(true);
                deskRepository.save(desk);
                    
            }
            catch(Exception e)
            {
                err = true;
                bindingResult.addError(new ObjectError("global Error", e.getCause().getCause().getLocalizedMessage()));
            }

        if(!err)
        {
            mv.setViewName("redirect:/user/"+optUser.get().getUserId()+"/allRest");
        }
        else
        {
            mv.setViewName("redirect:/restaurant/getAll");
        }  
        }
        
        return mv;
    }
    


}
