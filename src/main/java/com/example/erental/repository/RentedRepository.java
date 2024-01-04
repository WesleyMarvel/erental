package com.example.erental.repository;

import com.example.erental.domain.Rented;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentedRepository extends JpaRepository<Rented, Long> {
    List<Rented> findAllByUsers_Id(Long usersId);
    List<Rented> findAllByRentalItem_Id(Long rentalItemId);
}
