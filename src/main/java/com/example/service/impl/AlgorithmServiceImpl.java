package com.example.service.impl;

import com.example.config.GeminiConfig;
import com.example.dtos.request.GeminiRequest;
import com.example.dtos.response.ComplexityResponse;
import com.example.dtos.response.GeminiResponse;
import com.example.mappers.AlgorithmMapper;
import com.example.service.AlgorithmService;
import com.example.utils.ContentGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.net.UnknownHostException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlgorithmServiceImpl implements AlgorithmService {

    private final GeminiConfig config;
    private final WebClient.Builder webClientBuilder;
    private final ContentGenerator contentGenerator;

    @Override
    public ComplexityResponse getComplexity(String prompt) {
        WebClient webClient = createWebClient();
        GeminiRequest request = createGeminiRequest(prompt);
        try {
            return callGeminiApi(webClient, request);
        } catch (WebClientResponseException e) {
            throw handleResponseException(e);
        } catch (WebClientRequestException e) {
            throw handleRequestException(e);
        }
    }

    private WebClient createWebClient() {
        return webClientBuilder
                .baseUrl(config.getApiUrl() + config.getApiKey())
                .build();
    }

    private GeminiRequest createGeminiRequest(String prompt) {
        String generateContent = contentGenerator.generateContent(prompt);
        return GeminiRequest.builder()
                .contents(List.of(
                        GeminiRequest.Content.builder().parts(
                                        List.of(GeminiRequest.Content.Part.builder().text(generateContent).build()))
                                .build()))
                .build();
    }

    private ComplexityResponse callGeminiApi(WebClient webClient, GeminiRequest request) {
        return webClient
                .method(HttpMethod.POST)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, this::handleClientError)
                .onStatus(HttpStatusCode::is5xxServerError, this::handleServerError)
                .bodyToMono(GeminiResponse.class)
                .doOnTerminate(() -> System.out.println("Request to Gemini API completed"))
                .doOnSuccess(response -> System.out.println("Response received: " + response))
                .doOnError(error -> System.err.println("Error occurred: " + error.getMessage()))
                .map(AlgorithmMapper::toDTO)
                .retry(2)
                .block();
    }

    private Mono<RuntimeException> handleClientError(ClientResponse clientResponse) {
        return clientResponse.bodyToMono(String.class)
                .map(errorBody -> new RuntimeException("Client error: " + errorBody));
    }

    private Mono<RuntimeException> handleServerError(ClientResponse clientResponse) {
        return clientResponse.bodyToMono(String.class)
                .map(errorBody -> new RuntimeException("Server error: " + errorBody));
    }

    private RuntimeException handleResponseException(WebClientResponseException e) {
        return new RuntimeException("Error while calling Gemini API. Status: " + e.getStatusCode(), e);
    }

    private RuntimeException handleRequestException(WebClientRequestException e) {
        if (e.getCause() instanceof UnknownHostException) {
            return new RuntimeException("Host resolution error for Gemini API", e);
        } else {
            return new RuntimeException("Request error while calling Gemini API", e);
        }
    }
}

