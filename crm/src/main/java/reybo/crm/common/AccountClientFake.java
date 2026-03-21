package reybo.crm.common;

import reybo.crm.common.api.provider.table.TableType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccountClientFake {

    private static final Logger log = LoggerFactory.getLogger(AccountClientFake.class);

    public Map<String, String> getDisplayedParametersByEntityType(TableType tableType) {

        Map<String, String> parametersMap = new HashMap<>();

        // Contractor
        parametersMap.put("surname", "фамилия");
        parametersMap.put("firstname", "имя");
        parametersMap.put("lastname", "отчество");
        parametersMap.put("phone", "телефон");
        parametersMap.put("advertisingSource", "реклама");

        // ABOUT DEVICES
        parametersMap.put("brand", "марка");
        parametersMap.put("model", "модель");
        parametersMap.put("malfunctions", "неисправности");

        // PARAMETERS
        parametersMap.put("agreedPrice", "согласованная стоимость");
        parametersMap.put("currentMaster", "мастер");
        parametersMap.put("deadline", "крайний срок");
        parametersMap.put("currentManager", "менеджер");
        parametersMap.put("urgently", "срочно");

        // ID (обычно оставляют в конце или начале)
        parametersMap.put("id", "заказ");

        String tableTypeStr = tableType.getEntityName().toString().toLowerCase();
        log.info("запрос по сущности" + tableType);

        //todo смысл этого хардкода в том, чтобы создать список полей выводимых в таблице в зависимости от сущности
        switch (tableTypeStr) {
            case ("serviceorder") : return parametersMap;
        }
        return Collections.emptyMap();
    }
}
