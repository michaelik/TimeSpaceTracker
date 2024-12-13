package com.example.dtos.response;

import lombok.Builder;

@Builder
public record ComplexityResponse(
        TimeSpaceComplexity timeComplexity,
        TimeSpaceComplexity spaceComplexity
) {
}
