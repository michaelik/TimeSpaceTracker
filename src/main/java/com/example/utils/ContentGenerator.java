package com.example.utils;

import org.springframework.stereotype.Component;

@Component
public class ContentGenerator {

    public String generateContent(String prompt) {
        return " Analyze the time and space complexity of the following algorithm ONLY if it's a complete, valid algorithm or code snippet. Provide the analysis as a JSON response using exactly this structure:\n" +
                "\n" +
                prompt +
                "\n" +
                "Required JSON structure:\n" +
                "```json\n" +
                "{\n" +
                "    \"timeComplexity\": {\n" +
                "        \"bestCase\": \"O(?)\",\n" +
                "        \"averageCase\": \"O(?)\",\n" +
                "        \"worstCase\": \"O(?)\"\n" +
                "    },\n" +
                "    \"spaceComplexity\": {\n" +
                "        \"bestCase\": \"O(?)\",\n" +
                "        \"averageCase\": \"O(?)\",\n" +
                "        \"worstCase\": \"O(?)\"\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "Validation Rules:\n" +
                "1. Return NO RESPONSE if:\n" +
                "   - The input is not an algorithm or code snippet (e.g., plain text, questions, or general statements)\n" +
                "   - The algorithm/code is incomplete or has syntax errors\n" +
                "   - The complexity cannot be determined due to missing implementation details\n" +
                "   - The input lacks clear algorithmic structure or logic\n" +
                "   - The code contains undefined functions or missing dependencies that affect complexity analysis\n" +
                "\n" +
                "Analysis Rules (only if input passes validation):\n" +
                "1. Provide a complete analysis for all cases: best, average, and worst case for both time and space complexities.\n" +
                "   - **Best case:** Consider scenarios where the algorithm completes the task with the fewest operations or minimal space usage (e.g., no iterations or early returns).\n" +
                "   - **Average case:** Consider typical scenarios for general inputs where the algorithm performs a reasonable number of operations or uses a standard amount of memory.\n" +
                "   - **Worst case:** Consider the scenario with the maximum number of operations or the highest memory usage, often occurring with inputs that trigger the worst behavior of the algorithm\n" +
                "2. Use **Big O notation** (`O(?)`) for all values.\n" +
                "3. Provide only the JSON response, with no explanation or commentary.\n" +
                "4. Account for Variability: If time or space complexity differs across cases (e.g., shorter input, immediate termination, or no significant auxiliary space used), ensure all cases are distinct and accurate.\n" +
                "5. Include all cases even if they are the same.\n" +
                "6. Use **lowercase keys** exactly as shown.\n" +
                "7. Do not include any additional fields or commentary.\n" +
                "\n" +
                "Important: Return ONLY the JSON object if the input is a valid, complete algorithm. Otherwise, return nothing.";
    }
}
