package com.kursova.travel.entity.dictionary;

public enum ApplicationType {
    ADMIN, TOURIST;

    public static Boolean isAdmin(String applicationType) {
        return ADMIN.name().equals(applicationType);
    }

    public static Boolean isTourist(String applicationType) {
        return TOURIST.name().equals(applicationType);
    }
}
