package com.anonymous63.onlinebookstore.services.impl;

import com.anonymous63.onlinebookstore.configs.Constants;
import com.anonymous63.onlinebookstore.exceptions.ResourceNotFoundException;
import com.anonymous63.onlinebookstore.models.Role;
import com.anonymous63.onlinebookstore.models.User;
import com.anonymous63.onlinebookstore.payloads.dtos.UserDto;
import com.anonymous63.onlinebookstore.repositories.RoleRepo;
import com.anonymous63.onlinebookstore.repositories.UserRepo;
import com.anonymous63.onlinebookstore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CrudServiceImpl<User, UserDto, Long> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    public UserServiceImpl(UserRepo repository, ModelMapper modelMapper) {
        super(User.class, UserDto.class);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto register(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        String password = userDto.getPassword();
        user.setPassword(this.passwordEncoder.encode(password));
        Role role = this.roleRepo.findById(Constants.ROLE_USER).orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), "id", Constants.ROLE_USER));
        user.getRoles().add(role);
        User newUser = this.repository.save(user);

        return this.modelMapper.map(newUser, UserDto.class);
    }
}
