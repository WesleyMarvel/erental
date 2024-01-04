package com.example.erental.api;

import com.example.erental.domain.RentalItem;
import com.example.erental.service.UsersDto;
import com.example.erental.service.rentalitem.RentalItemService;
import com.example.erental.service.rented.RentedDto;
import com.example.erental.service.rented.RentedService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RentedController {
    private final RentedService rentedService;
    private final RentalItemService rentalItemService;

    public RentedController(RentedService rentedService, RentalItemService rentalItemService) {
        this.rentedService = rentedService;
        this.rentalItemService = rentalItemService;
    }

    @GetMapping("/rent-{rentalItemId}")
    public String createRented(Model model, @PathVariable Long rentalItemId){
        RentalItem item = rentalItemService.getById(rentalItemId);
        model.addAttribute("item", item);
        RentedDto rentedDto = new RentedDto();
        model.addAttribute("rentedDto", rentedDto);
        return "rent";
    }

    @PostMapping("/rent")
    public String createRented(Model model, @ModelAttribute("rentedDto") RentedDto rentedDto) throws Exception {
        rentedService.createRented(rentedDto.getUsers(),
                rentedDto.getRentalItem(),
                rentedDto.getDuration());
        return "redirect:/home";
    }
}
