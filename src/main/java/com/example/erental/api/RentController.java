package com.example.erental.api;

import com.example.erental.service.rented.RentedDto;
import com.example.erental.service.rented.RentedService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RentController {
    private final RentedService rentedService;

    public RentController(RentedService rentedService) {
        this.rentedService = rentedService;
    }

    @PostMapping("/rented")
    public String createRented(@ModelAttribute("rentedDto") RentedDto rentedDto) throws Exception {
        rentedService.createRented(rentedDto.getUsers(),
                rentedDto.getRentalItem(),
                rentedDto.getDuration());
        return "redirect:/home";
    }
}
