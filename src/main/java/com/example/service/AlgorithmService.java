package com.example.service;

import com.example.dtos.response.ComplexityResponse;

public interface AlgorithmService {
    ComplexityResponse getComplexity(String prompt);
}
