package com.example.controller;

import com.example.dtos.BaseResponse;
import com.example.dtos.request.PromptRequest;
import com.example.dtos.response.ComplexityResponse;
import com.example.service.AlgorithmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/algorithm")
@Validated
public class AlgorithmController {

    private final AlgorithmService algorithmService;


    @PostMapping(
            path = "/analyze",
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public BaseResponse<ComplexityResponse> analyzeAlgorithm(
            @RequestBody @Valid
            PromptRequest request
    ) {
        return new BaseResponse<>(
                true,
                HttpStatus.OK,
                "Time and Space Complexity Retrieved Successfully",
                algorithmService.getComplexity(request.algorithmCode())
        );
    }

}
