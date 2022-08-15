package com.books.books.service;

import com.books.books.entity.UserEntity;
import com.books.books.exception.UserAlreadyExistsException;
import com.books.books.model.UserGetModel;
import com.books.books.model.UserRegisterModel;
import com.books.books.model.UserUpdateModel;
import com.books.books.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public List<UserGetModel> getUsersFromDatabase() {
        List<UserEntity> users = userRepo.findAll();
        return convertUsersToGetModel(users);
    }

    private List<UserGetModel> convertUsersToGetModel(List<UserEntity> users) {
        List<UserGetModel> userGetModels = new ArrayList<>();
        for(var user: users) {
            userGetModels.add(UserGetModel.toModel(user));
        }
        return userGetModels;
    }

    public UserGetModel getUserById(long userId) {
        UserEntity user = userRepo.findById(userId).orElseThrow();
        return UserGetModel.toModel(user);
    }

    public UserEntity getCurrentUser() {
        String userPrincipal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findAllByUsername(userPrincipal);
    }

    public void deleteUser(long userId) {
        userRepo.deleteById(userId);
    }

    public void updateUser(long userId, UserUpdateModel userUpdateModel) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        UserEntity user = userRepo.findById(userId).orElseThrow();
        modelMapper.map(userUpdateModel, user);
    }
}
