package com.example.erental.service.rented;

import com.example.erental.domain.RentalItem;
import com.example.erental.domain.Rented;
import com.example.erental.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentedDto {
    private Long id;
    private Long users;
    private Long rentalItem;
    private Integer duration;

    public static RentedDto of(Rented rented){
        Objects.requireNonNull(rented);
        RentedDto rentedDto = new RentedDto();
        rentedDto.setId(rented.getId());
        rentedDto.setUsers(rented.getUsers().getId());
        rentedDto.setRentalItem(rented.getRentalItem().getId());
        rentedDto.setDuration(rented.getDuration());
        return rentedDto;
    }
}
