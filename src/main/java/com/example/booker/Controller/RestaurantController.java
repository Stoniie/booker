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
import com.example.booker.Repository.RestaurantRepository;


@Controller
@RequestMapping
public class RestaurantController 
{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/restaurant/getAll")
    public ModelAndView getAllRes()
    {
        ModelAndView mv = new ModelAndView("RestaurantList");
        List<Restaurant> restaurants = (List<Restaurant>) restaurantRepository.findAll();
        mv.addObject("restaurants", restaurants);
        return mv;
    }

    @GetMapping("/newRestaurant")
    public ModelAndView newRestaurant()
    {
        ModelAndView mv = new ModelAndView("NewRestaurant");
        Restaurant restaurant = new Restaurant();
        mv.addObject("restaurant", restaurant);
        return mv;
    }

    @GetMapping("/{resId}/deskList")
    public ModelAndView restaurantHome(@PathVariable int resId)
    {
        ModelAndView mv = new ModelAndView("DeskList");
        
        Optional<Restaurant> restaurant = restaurantRepository.findById(resId);
        if(restaurant.isPresent())
        {
        mv.addObject("restaurant", restaurant.get());
        mv.addObject("desks", restaurant.get().getDesk());
        }
        return mv;
    }

    
    @PostMapping("/saveNewRes")
    public ModelAndView saveNewRes(@Valid @ModelAttribute Restaurant restaurant, BindingResult bindingResult)
    {
        boolean err = false;
        err = bindingResult.hasErrors();
        restaurant.setResSecret(UUID.randomUUID().toString());
        ModelAndView mv = new ModelAndView();
        
        if(!err)
        {
            try
            {
                restaurantRepository.save(restaurant); 
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
            mv.setViewName("redirect:/newRestaurant");
        }
        return mv;
    }




    

}
