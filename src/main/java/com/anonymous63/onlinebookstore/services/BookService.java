package com.anonymous63.onlinebookstore.services;

import com.anonymous63.onlinebookstore.payloads.dtos.BookDto;
import com.anonymous63.onlinebookstore.payloads.response.CrudResponse;

public interface BookService extends CrudService<BookDto, Long> {

    CrudResponse findBookByCategoryId(Long id, Integer page, Integer size, String sortBy, String sortDir);
    CrudResponse findBookByUserId(Long id, Integer page, Integer size, String sortBy, String sortDir);
}
