package com.anonymous63.onlinebookstore.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrudResponse<Entity> {
    private List<Entity> content;
    private int totalPages;
    private long totalElements;
    private int noOfElements;
    private int currentPage;
    private int itemsPerPage;
    private boolean isEmpty;
    private boolean hasNext;
    private boolean hasPrevious;
    private boolean isFirst;
    private boolean isLast;
}
