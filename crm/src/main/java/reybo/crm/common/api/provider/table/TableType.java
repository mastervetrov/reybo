package reybo.crm.common.api.provider.table;

import reybo.crm.order.model.ServiceOrder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TableType {
    SERVICE_ORDER(ServiceOrder.class);

    private final Class<?> entityClass;

    public String getEntityName() {
        return entityClass.getSimpleName();
    }

}
