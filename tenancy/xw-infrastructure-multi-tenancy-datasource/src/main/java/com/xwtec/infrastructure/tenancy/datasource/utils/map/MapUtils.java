package com.xwtec.infrastructure.tenancy.datasource.utils.map;

import java.util.Map;

public class MapUtils {

    public static <String,V> V getIgnoreCase(Map<String,V> map, String key){
        Map<String,V> caseInsensitiveMap = new CaseInsensitiveMap(map);
        return caseInsensitiveMap.get(key);
    }

}
