package com.anonymous63.onlinebookstore.services.impl;

import com.anonymous63.onlinebookstore.exceptions.ResourceNotFoundException;
import com.anonymous63.onlinebookstore.payloads.response.CrudResponse;
import com.anonymous63.onlinebookstore.payloads.response.CrudResponseConverter;
import com.anonymous63.onlinebookstore.services.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CrudServiceImpl<Entity, DTO, ID> implements CrudService<DTO, ID> {

    protected JpaRepository<Entity, ID> repository;

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected AuditLogServiceImpl auditLogService;

    private Class<Entity> entityClass;
    private Class<DTO> dtoClass;

    protected CrudServiceImpl(Class<Entity> entityClass, Class<DTO> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public DTO findById(ID id) {
        Entity entity = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity", "id", id));
        return this.modelMapper.map(entity, dtoClass);
    }

    @Override
    public CrudResponse findAll(Integer page, Integer size, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<Entity> entityPage = this.repository.findAll(pageable);
        List<DTO> dtos = entityPage.getContent().stream().map(entity -> this.modelMapper.map(entity, dtoClass))
                .collect(Collectors.toList());
        CrudResponse response = CrudResponseConverter.convertToResponse(dtos, entityPage);
        return response;
    }

    @Override
    public DTO create(DTO dto) {
        Entity entity = this.modelMapper.map(dto, entityClass);
        Entity createEntity = repository.save(entity);
        this.auditLogService.log("CREATE", entityClass.getSimpleName(), null, "Anonymous63", 63L, entity.toString());
        return this.modelMapper.map(createEntity, dtoClass);
    }

    @Override
    public DTO update(DTO dto, ID id) {
        Entity entity = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity", "id", id));
        this.modelMapper.map(dto, entity);
        Entity savedEntity = repository.save(entity);
        this.auditLogService.log("UPDATE", entityClass.getSimpleName(), (Long) id, "Anonymous63", 63L, entity.toString());
        return this.modelMapper.map(savedEntity, dtoClass);
    }

    @Override
    public void delete(ID id) {
        Entity entity = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity", "id", id));
        repository.deleteById(id);
        this.auditLogService.log("DELETE", entityClass.getSimpleName(), (Long) id, "Anonymous63", 63L, entity.toString());
    }

}
