package com.kursova.travel.entity.dictionary;

public enum EntityStatus {

    ACTIVE,
    INACTIVE;

    public static Boolean isInactive(EntityStatus entityStatus) {
        return INACTIVE.equals(entityStatus);
    }

    public static Boolean isActive(EntityStatus entityStatus) {
        return ACTIVE.equals(entityStatus);
    }
}
