package com.gradmi.usersapp.users_backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gradmi.usersapp.users_backend.dtos.user.IUser;
import com.gradmi.usersapp.users_backend.dtos.user.UserData;
import com.gradmi.usersapp.users_backend.dtos.user.UserRequest;
import com.gradmi.usersapp.users_backend.entities.Role;
import com.gradmi.usersapp.users_backend.entities.User;
import com.gradmi.usersapp.users_backend.repositories.RoleRepository;
import com.gradmi.usersapp.users_backend.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional()
    public User save(UserData data) {        
        User user = new User(data);
        List<Role> roles = getRoles(data);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    

    @Override
    @Transactional
    public Optional<User> update(UserRequest user, long id) {
        Optional<User> userOptional = repository.findById(id);
        if(userOptional.isPresent()){
            User userDB = userOptional.get();
            userDB.setName(user.name());
            userDB.setLastName(user.lastName());
            userDB.setEmail(user.email());
            userDB.setUsername(user.username());

            List<Role> roles = getRoles(user);
            userDB.setRoles(roles);

            repository.save(userDB);
            return Optional.of(userDB);
        }
        return Optional.empty();
    }

    @Override
    @Transactional()
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    private List<Role> getRoles(IUser data) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        optionalRoleUser.ifPresent(roles::add);
        if(Boolean.TRUE.equals(data.isAdmin())){
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        return roles;
    }   

}
