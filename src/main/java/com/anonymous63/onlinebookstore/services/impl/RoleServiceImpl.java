package com.anonymous63.onlinebookstore.services.impl;

import com.anonymous63.onlinebookstore.models.Role;
import com.anonymous63.onlinebookstore.payloads.dtos.RoleDto;
import com.anonymous63.onlinebookstore.repositories.RoleRepo;
import com.anonymous63.onlinebookstore.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends CrudServiceImpl<Role, RoleDto, Long> implements RoleService {

    public RoleServiceImpl(RoleRepo repository, ModelMapper modelMapper) {
        super(Role.class, RoleDto.class);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }
}
