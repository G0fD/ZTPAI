package com.company.shipify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UniversalIntRequest {
    private Integer integer;
}
