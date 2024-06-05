package com.anonymous63.onlinebookstore.payloads.response;

import org.springframework.data.domain.Page;

import java.util.List;

public class CrudResponseConverter {
    public static <Entity> CrudResponse<Entity> convertToResponse(List<Entity> content, Page<?> pageDetails) {
        CrudResponse<Entity> crudResponse = new CrudResponse<>();
        crudResponse.setContent(content);
        crudResponse.setTotalPages(pageDetails.getTotalPages());
        crudResponse.setTotalElements(pageDetails.getTotalElements());
        crudResponse.setNoOfElements(pageDetails.getNumberOfElements());
        crudResponse.setCurrentPage(pageDetails.getNumber());
        crudResponse.setItemsPerPage(pageDetails.getSize());
        crudResponse.setEmpty(pageDetails.isEmpty());
        crudResponse.setHasNext(pageDetails.hasNext());
        crudResponse.setHasPrevious(pageDetails.hasPrevious());
        crudResponse.setFirst(pageDetails.isFirst());
        crudResponse.setLast(pageDetails.isLast());
        return crudResponse;
    }
}
