package com.example.dtos.request;

import lombok.Builder;

@Builder
public record PromptRequest(
        String algorithmCode
) {
}
