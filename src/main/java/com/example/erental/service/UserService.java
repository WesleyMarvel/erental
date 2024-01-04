package com.example.erental.service;

import com.example.erental.commons.Roles;
import com.example.erental.domain.Users;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<Users> getAll();
    List<Users> getAllByRole(Roles role);
    List<Users> getAllByStatus(Boolean status);
    UsersDto createUser(
            String firstName,
            String lastName,
            String password,
            Roles role,
            Boolean status
    ) throws IOException;

    UsersDto updateUser(
            Long userId,
            String firstName,
            String lastName,
            String userName,
            MultipartFile userProfileImage,
            String password,
            Roles role,
            Boolean status
    ) throws IOException;
}
