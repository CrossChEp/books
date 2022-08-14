package com.books.books.service;

import com.books.books.entity.UserEntity;
import com.books.books.exception.UserAlreadyExistsException;
import com.books.books.model.UserRegisterModel;
import com.books.books.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findAllByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(var role: user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    public UserRegisterModel addUserToDatabase(UserRegisterModel userData) throws UserAlreadyExistsException {
        UserEntity user = userRepo.findAllByUsername(userData.getUsername());
        if(user != null) {
            throw new UserAlreadyExistsException("User already exists");
        }
        user = new UserEntity();
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        user.setUsername(userData.getUsername());
        user.setPassword(userData.getPassword());
        userRepo.save(user);
        return UserRegisterModel.toModel(user);
    }
}
