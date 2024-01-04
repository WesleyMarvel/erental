package com.example.erental.service.rented;

import com.example.erental.domain.RentalItem;
import com.example.erental.domain.Rented;
import com.example.erental.domain.Users;
import com.example.erental.repository.RentalItemRepository;
import com.example.erental.repository.RentedRepository;
import com.example.erental.repository.UsersRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RentedServiceImpl implements RentedService{
    private final RentedRepository rentedRepository;
    private final RentalItemRepository rentalItemRepository;
    private final UsersRepository usersRepository;

    public RentedServiceImpl(RentedRepository rentedRepository, RentalItemRepository rentalItemRepository, UsersRepository usersRepository) {
        this.rentedRepository = rentedRepository;
        this.rentalItemRepository = rentalItemRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Rented> getAll() {
        return rentedRepository.findAll();
    }

    @Override
    public List<Rented> getAllByUserId(Long usersId) {
        return rentedRepository.findAllByUsers_Id(usersId);
    }

    @Override
    public List<Rented> getAllByRentalItemId(Long rentalItemId) {
        return rentedRepository.findAllByRentalItem_Id(rentalItemId);
    }

    @Override
    public RentedDto createRented(Long usersId, Long rentalItemId, Integer duration) throws Exception {
        Optional<Users> optionalUsers = usersRepository.findById(usersId);
        if (optionalUsers.isEmpty()) {
            throw new NoSuchElementException("user not found");
        }
        Optional<RentalItem> optionalRentalItem = rentalItemRepository.findById(rentalItemId);
        if (optionalRentalItem.isEmpty()) {
            throw new NoSuchElementException("item not found");
        }
        var numberOfAllRentalItems = optionalRentalItem.get().getQuantity();
        List<Rented> listOfRented = rentedRepository.findAllByRentalItem_Id(rentalItemId);
        var numberOfRented = listOfRented.size();
        var isAvailable = numberOfAllRentalItems - numberOfRented;
        if (isAvailable > 0) {
            Rented rented = new Rented();
            rented.setUsers(optionalUsers.get());
            rented.setRentalItem(optionalRentalItem.get());
            rented.setDuration(duration);
            Rented savedRented = rentedRepository.save(rented);
            return RentedDto.of(savedRented);
        }else{
            throw new Exception("Item is not available");
        }
    }

    @Override
    public RentedDto updateRented(Long rentedId, Long usersId, Long rentalItemId, Integer duration) {
        return null;
    }
}
