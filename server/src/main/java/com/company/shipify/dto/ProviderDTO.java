package com.company.shipify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProviderDTO {

    private Integer id;
    private String providerName;
}