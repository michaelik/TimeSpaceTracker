package com.example.dtos.response;

import lombok.Builder;

@Builder
public record TimeSpaceComplexity(
        String bestCase,
        String averageCase,
        String worstCase
) {
}
