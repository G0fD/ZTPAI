package com.company.shipify.model;

public enum Genders {
    NONE, MALE, FEMALE, OTHER;

    public static Gender mapToObject(Genders genders) {
        return Gender.builder()
                .id(genders.ordinal())
                .name(genders.name().toLowerCase())
                .build();
    }
}