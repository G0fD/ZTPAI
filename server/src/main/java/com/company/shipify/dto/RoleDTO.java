package com.company.shipify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {

    private Integer id;
    private String roleName;
}