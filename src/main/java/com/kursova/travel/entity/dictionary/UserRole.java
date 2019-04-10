package com.kursova.travel.entity.dictionary;

public enum UserRole {

    SUPER_ADMIN, ADMIN, TRAINER, SPORTSMAN, AMATEUR, INSTRUCTOR;

    public static Boolean isAdmin(String userRole) {
        return ADMIN.name().equals(userRole);
    }

    public static Boolean isSuperAdmin(String userRole) {
        return SUPER_ADMIN.name().equals(userRole);
    }

    public static Boolean isTrainer(String userRole) {
        return TRAINER.name().equals(userRole);
    }

    public static Boolean isSportsman(String userRole) {
        return SPORTSMAN.name().equals(userRole);
    }

    public static Boolean isAmateur(String userRole) {
        return AMATEUR.name().equals(userRole);
    }

    public static Boolean isInstructor(String userRole) {
        return INSTRUCTOR.name().equals(userRole);
    }

}
