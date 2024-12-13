package com.example.dtos.request;

import lombok.Builder;

import java.util.List;

@Builder
public record GeminiRequest(List<Content> contents) {
    @Builder
    public static record Content(List<Part> parts) {
        @Builder
        public static record Part(String text) {
        }
    }
}
