package com.company.shipify.model;

public enum Roles {
    NONE, ADMIN, USER;

    public static Role mapToObject(Roles roles) {
        return Role.builder()
                .id(roles.ordinal())
                .name(roles.name().toLowerCase())
                .build();
    }
}