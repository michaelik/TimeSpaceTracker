package com.example.mappers;

import com.example.dtos.response.GeminiResponse;
import com.example.dtos.response.ComplexityResponse;
import com.example.dtos.response.TimeSpaceComplexity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface AlgorithmMapper {
    static ComplexityResponse toDTO(GeminiResponse geminiResponse){
        // Get the JSON string from Gemini response
        String jsonResponse = geminiResponse.candidates().get(0).content().parts().get(0).text()
                .replaceAll("```json\\s*", "") // Remove ```json
                .replaceAll("```\\s*$", "")    // Remove closing ```
                .trim();                       // Remove extra whitespace

        try {
            // Parse JSON using ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // Parse time complexity
            JsonNode timeNode = rootNode.get("timeComplexity");
            TimeSpaceComplexity timeComplexity = new TimeSpaceComplexity(
                    timeNode.get("bestCase").asText(),
                    timeNode.get("averageCase").asText(),
                    timeNode.get("worstCase").asText()
            );

            // Parse space complexity
            JsonNode spaceNode = rootNode.get("spaceComplexity");
            TimeSpaceComplexity spaceComplexity = new TimeSpaceComplexity(
                    spaceNode.get("bestCase").asText(),
                    spaceNode.get("averageCase").asText(),
                    spaceNode.get("worstCase").asText()
            );

            return ComplexityResponse.builder()
                    .timeComplexity(timeComplexity)
                    .spaceComplexity(spaceComplexity)
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse complexity response", e);
        }
    }
}
