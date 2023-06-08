package com.company.shipify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UniversalStringRequest {
    private String string;
}
