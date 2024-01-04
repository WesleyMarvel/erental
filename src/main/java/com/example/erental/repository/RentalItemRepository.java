package com.example.erental.repository;

import com.example.erental.domain.RentalItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalItemRepository extends JpaRepository<RentalItem, Long> {
    List<RentalItem> findAllByUsers_Id(Long usersId);
    List<RentalItem> findAllByLocation(String location);
}
