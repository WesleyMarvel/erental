package com.example.erental.service;

import com.example.erental.commons.Roles;
import com.example.erental.domain.Users;
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
import static com.example.erental.commons.FileConstant.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Slf4j
public class UsersServiceImpl implements UserService{
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public List<Users> getAllByRole(Roles role) {
        return usersRepository.findAllByRole(role);
    }

    @Override
    public List<Users> getAllByStatus(Boolean status) {
        return usersRepository.findAllByStatus(status);
    }

    @Override
    public UsersDto createUser(String firstName, String lastName, String password, Roles role, Boolean status) throws IOException{
        List<Users> usersList = usersRepository.findAll();
        int userNumber = usersList.size() + 1;
        Users users = new Users();
        users.setFirstName(firstName);
        users.setLastName(lastName);
        users.setRole(role);
        users.setStatus(false);
        users.setPassword(password);
        users.setUserName(firstName + lastName + userNumber);
//        saveUserProfileImage(users, userProfileImage);
        Users savedUser = usersRepository.save(users);
        return UsersDto.of(savedUser);
    }

    @Override
    public UsersDto updateUser(Long userId, String firstName, String lastName, String userName, MultipartFile userProfileImage, String password, Roles role, Boolean status) throws IOException{
        return null;
    }

//    private void saveUserProfileImage(Users users, MultipartFile userProfileImage) throws IOException {
//        if (userProfileImage != null){
//            Path userFolder = Paths.get(USERS_FOLDER);
//            if (!Files.exists(userFolder)){
//                Files.createDirectories(userFolder);
//                log.info(DIRECTORY_CREATED + userFolder);
//            }
//            Files.deleteIfExists(Paths.get(userFolder + users.getUserName() + DOT + JPG_EXTENSION));
//            Files.copy(userProfileImage.getInputStream(),
//                    userFolder.resolve(users.getUserName() + DOT + JPG_EXTENSION), REPLACE_EXISTING
//            );
//            users.setUserProfileImage(setProfileImageUrl(users.getUserName()));
//            log.info(FILE_SAVED_IN_FILE_SYSTEM + userProfileImage.getOriginalFilename());
//        }
//    }
//
//    private String setProfileImageUrl(String users) {
//        return ServletUriComponentsBuilder
//                .fromCurrentContextPath()
////                .scheme("https")
//                .path(USERS_IMAGE_PATH + users + ".jpg")
//                .toUriString();
//    }
}
