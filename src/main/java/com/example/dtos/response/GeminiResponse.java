package com.example.dtos.response;

import lombok.Builder;

import java.util.List;

@Builder
public record GeminiResponse(List<Candidate> candidates) {
    @Builder
    public record Candidate(Content content, String finishReason, double avgLogprobs) {
        @Builder
        public record Content(List<Part> parts, String role) {
            @Builder
            public record Part(String text) {
            }
        }
    }
}
