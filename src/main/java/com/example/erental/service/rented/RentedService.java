package com.example.erental.service.rented;

import com.example.erental.domain.Rented;

import java.util.List;

public interface RentedService {
    List<Rented> getAll();
    List<Rented> getAllByUserId(Long usersId);
    List<Rented> getAllByRentalItemId(Long rentalItemId);
    RentedDto createRented(Long usersId, Long rentalItemId, Integer duration) throws Exception;
    RentedDto updateRented(Long rentedId, Long usersId, Long rentalItemId, Integer duration);

}
