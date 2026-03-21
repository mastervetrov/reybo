package reybo.crm.common.api.service;

import java.util.UUID;

public interface WriteService<REQUEST, DTO> {
    DTO create(REQUEST request);
    DTO update(UUID id, REQUEST request);
    void delete(UUID id);
}