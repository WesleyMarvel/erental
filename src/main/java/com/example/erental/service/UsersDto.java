package com.example.erental.service;

import com.example.erental.commons.Roles;
import com.example.erental.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsersDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String userProfileImage;
    private String password;
    private Roles role;
    private Boolean status;

    public static UsersDto of(Users users){
        Objects.requireNonNull(users);
        UsersDto usersDto = new UsersDto();
        usersDto.setId(users.getId());
        usersDto.setFirstName(users.getFirstName());
        usersDto.setLastName(users.getLastName());
        usersDto.setUserName(users.getUserName());
        usersDto.setUserProfileImage(users.getUserProfileImage());
        usersDto.setPassword(users.getPassword());
        usersDto.setRole(users.getRole());
        usersDto.setStatus(users.getStatus());
        return usersDto;
    }
}
