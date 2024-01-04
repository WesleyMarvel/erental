package com.example.erental.service.rentalitem;

import com.example.erental.domain.RentalItem;
import com.example.erental.domain.Users;
import com.example.erental.repository.RentalItemRepository;
import com.example.erental.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static com.example.erental.commons.FileConstant.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Slf4j
public class RentalItemServiceImpl implements RentalItemService{
    private final RentalItemRepository rentalItemRepository;
    private final UsersRepository usersRepository;

    public RentalItemServiceImpl(RentalItemRepository rentalItemRepository, UsersRepository usersRepository) {
        this.rentalItemRepository = rentalItemRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public List<RentalItem> getAll() {
        return rentalItemRepository.findAll();
    }

    @Override
    public RentalItem getById(Long rentalItemId){
        Optional<RentalItem> optionalRentalItem = rentalItemRepository.findById(rentalItemId);
        if(optionalRentalItem.isEmpty()){
            throw new NoSuchElementException("Not found");
        }
        return optionalRentalItem.get();
    }

    @Override
    public List<RentalItem> getAllByLocation(String location) {
        return rentalItemRepository.findAllByLocation(location);
    }

    @Override
    public List<RentalItem> getAllByUser(Long userId) {
        return rentalItemRepository.findAllByUsers_Id(userId);
    }

    @Override
    public RentalItemDto createRentalItem(String name, String itemType, Integer rentalPeriod, Integer quantity, String location, Float price, Long userId, MultipartFile itemImage) throws IOException {
        Optional<Users> users = usersRepository.findById(userId);
        if (users.isEmpty()){
            throw new NoSuchElementException("Not found");
        }
        RentalItem rentalItem = new RentalItem();
        rentalItem.setName(name);
        rentalItem.setItemType(itemType);
        rentalItem.setRentalPeriod(rentalPeriod);
        rentalItem.setQuantity(quantity);
        rentalItem.setLocation(location);
        rentalItem.setPrice(price);
        rentalItem.setUsers(users.get());
        saveImage(rentalItem, itemImage);
        RentalItem savedItem = rentalItemRepository.save(rentalItem);
        return RentalItemDto.of(savedItem);
    }

    @Override
    public RentalItemDto updateRentalItem(Long rentalItemId, String name, String itemType, Integer rentalPeriod, Integer quantity, String location, Float price, MultipartFile itemImage, Long userId) throws IOException {
        return null;
    }

    private void saveImage(RentalItem rentalItem, MultipartFile itemImage) throws IOException {
        if (itemImage != null){
            Path rentalItemFolder = Paths.get(RENTALITEM_FOLDER);
            if (!Files.exists(rentalItemFolder)){
                Files.createDirectories(rentalItemFolder);
                log.info(DIRECTORY_CREATED + rentalItemFolder);
            }
            Files.deleteIfExists(Paths.get(rentalItemFolder + rentalItem.getName() + DOT + JPG_EXTENSION));
            Files.copy(itemImage.getInputStream(),
                    rentalItemFolder.resolve(rentalItem.getName() + DOT + JPG_EXTENSION), REPLACE_EXISTING
            );
            rentalItem.setItemImage(setProfileImageUrl(rentalItem.getName()));
            log.info(FILE_SAVED_IN_FILE_SYSTEM + itemImage.getOriginalFilename());
        }
    }

    private String setProfileImageUrl(String rentalItem) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(RENTALITEM_IMAGE_PATH + rentalItem + ".jpg")
                .toUriString();
    }
}

