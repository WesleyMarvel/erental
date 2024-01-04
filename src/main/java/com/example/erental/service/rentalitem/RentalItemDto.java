package com.example.erental.service.rentalitem;

import com.example.erental.domain.RentalItem;
import com.example.erental.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentalItemDto {
    private Long id;
    private String name;
    private String itemType;
    private Integer rentalPeriod;
    private Integer quantity;
    private String location;
    private Float price;
    private MultipartFile itemImage;
    private Users users;

    public static RentalItemDto of(RentalItem rentalItem){
        Objects.requireNonNull(rentalItem);
        RentalItemDto rentalItemDto = new RentalItemDto();
        rentalItemDto.setId(rentalItem.getId());
        rentalItemDto.setName(rentalItem.getName());
        rentalItemDto.setItemType(rentalItem.getItemType());
        rentalItemDto.setRentalPeriod(rentalItem.getRentalPeriod());
        rentalItemDto.setQuantity(rentalItem.getQuantity());
        rentalItemDto.setLocation(rentalItem.getLocation());
        rentalItemDto.setPrice(rentalItem.getPrice());
        rentalItemDto.setItemImage(rentalItemDto.itemImage);
        rentalItemDto.setUsers(rentalItem.getUsers());
        return rentalItemDto;
    }
}
