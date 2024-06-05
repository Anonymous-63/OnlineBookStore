package com.anonymous63.onlinebookstore.services.impl;

import com.anonymous63.onlinebookstore.models.User;
import com.anonymous63.onlinebookstore.payloads.dtos.UserDto;
import com.anonymous63.onlinebookstore.repositories.UserRepo;
import com.anonymous63.onlinebookstore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CrudServiceImpl<User, UserDto, Long> implements UserService {
    public UserServiceImpl(UserRepo repository, ModelMapper modelMapper) {
        super(User.class, UserDto.class);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

}
