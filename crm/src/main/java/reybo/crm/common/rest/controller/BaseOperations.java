package reybo.crm.common.rest.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BaseOperations<T, REQUEST> {
    Page<T> getAll(Pageable pageable);
    T getById(UUID id);
    T create(REQUEST request);
    T update(UUID id, REQUEST request);
    void delete(UUID id);
}