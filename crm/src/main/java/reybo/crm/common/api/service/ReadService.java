package reybo.crm.common.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface ReadService<DTO> {
    List<DTO> findAll();
    DTO findById(UUID id);
    Page<DTO> findAll(Pageable pageable);
    List<DTO> findAll(Sort sort);
}