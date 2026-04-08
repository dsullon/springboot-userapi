package com.gradmi.usersapp.users_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gradmi.usersapp.users_backend.dtos.user.UserData;
import com.gradmi.usersapp.users_backend.dtos.user.UserRequest;
import com.gradmi.usersapp.users_backend.entities.User;

public interface UserService {
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(Long id);
    User save(UserData user);
    Optional<User> update(UserRequest user, long id);
    void deleteById(long id);

}
