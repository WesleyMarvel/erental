package com.example.erental.service.rentalitem;

import com.example.erental.domain.RentalItem;
import com.example.erental.domain.Users;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RentalItemService {
    List<RentalItem> getAll();
    List<RentalItem> getAllByLocation(String location);
    List<RentalItem> getAllByUser(Long userId);

    RentalItem getById(Long rentalItemId);

    RentalItemDto createRentalItem(
            String name,
            String itemType,
            Integer rentalPeriod,
            Integer quantity,
            String location,
            Float price,
            Long userId,
            MultipartFile itemImage
    ) throws IOException;

    RentalItemDto updateRentalItem(
            Long rentalItemId,
            String name,
            String itemType,
            Integer rentalPeriod,
            Integer quantity,
            String location,
            Float price,
            MultipartFile itemImage,
            Long userId
    ) throws IOException;
}
