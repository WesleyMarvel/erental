package com.example.erental.repository;

import com.example.erental.commons.Roles;
import com.example.erental.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findAllByRole(Roles role);
    List<Users> findAllByStatus(Boolean status);
}
