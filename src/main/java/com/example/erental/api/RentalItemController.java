package com.example.erental.api;

import com.example.erental.domain.RentalItem;
import com.example.erental.service.rentalitem.RentalItemDto;
import com.example.erental.service.rentalitem.RentalItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.example.erental.commons.FileConstant.RENTALITEM_FOLDER;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@Controller
public class RentalItemController {
    private final RentalItemService rentalItemService;

    public RentalItemController(RentalItemService rentalItemService) {
        this.rentalItemService = rentalItemService;
    }

    @GetMapping("/home")
    public String home(Model model){
        List<RentalItem> rentalItems = rentalItemService.getAll();
        model.addAttribute("rentalItems", rentalItems);
        return "home";
    }

    @GetMapping("/create-rental-item")
    public String createRentalItem(Model model){
        model.addAttribute("rentalItemDto", new RentalItemDto());
        return "create-rental";
    }

    @GetMapping(value = "/{rentalitem}", produces = IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getRentalItem(@PathVariable String rentalitem) throws IOException {
        byte[] imageData = Files.readAllBytes(Paths.get(RENTALITEM_FOLDER + rentalitem));
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
    }

    @PostMapping("/create-rental-item")
    public String createRentalItem(@ModelAttribute("rentalItemDto")RentalItemDto rentalItemDto, @RequestParam(value = "itemImage", required = true) MultipartFile itemImage) throws IOException{
        rentalItemService.createRentalItem(rentalItemDto.getName(),
                rentalItemDto.getItemType(),
                rentalItemDto.getRentalPeriod(),
                rentalItemDto.getQuantity(),
                rentalItemDto.getLocation(),
                rentalItemDto.getPrice(),
                rentalItemDto.getUsers().getId(),
                itemImage);
        return "redirect:/admin";
    }
}
