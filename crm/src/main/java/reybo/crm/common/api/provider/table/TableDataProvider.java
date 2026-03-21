package reybo.crm.common.api.provider.table;

import reybo.crm.common.api.entity.CrmEntity;
import reybo.crm.common.api.entity.CrmView;


import java.util.List;

public interface TableDataProvider {

    TableType getSupportedType();

    List<CrmEntity> findByFilter(List<String> displayedParameters);

    List<CrmView> findAll();
}
