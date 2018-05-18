package com.example.demo.common.dynamicdb;

import com.example.demo.enums.DynamicDBType;

public class DynamicDbContextHolder {

    private static final ThreadLocal<DynamicDBType> contextHolder = new ThreadLocal<DynamicDBType>();

    public static void setDbType(DynamicDBType dbType) {
        if (dbType == null) {
        	throw new NullPointerException();
        }        	
        contextHolder.set(dbType);
    }

    public static DynamicDBType getDbType() {
        return contextHolder.get() == null ? DynamicDBType.MASTER : contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }

}

