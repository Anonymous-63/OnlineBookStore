package com.anonymous63.onlinebookstore.services;

import com.anonymous63.onlinebookstore.payloads.response.CrudResponse;

public interface CrudService<DTO, ID> {


    DTO findById(ID id);

    CrudResponse findAll(Integer page, Integer size, String sortBy, String sortDir);

    DTO create(DTO dto);

    DTO update(DTO dto, ID id);

    void delete(ID id);
}
