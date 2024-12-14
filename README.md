# Algorithm Complexity Analysis API

## Overview
The Algorithm Complexity Analysis API is a RESTful service designed to analyze the time and space complexity of algorithms. It accepts algorithm code as input, processes it via Gemini API, and returns detailed complexity metrics in a structured format.

## Features
- Analyze time complexity: Best Case, Average Case, and Worst Case.
- Analyze space complexity: Best Case, Average Case, and Worst Case.
- Access a well-documented Swagger UI to explore and test endpoints.

---

## Prerequisites
1. **Gemini API Key**
    - Visit [Google AI Studio](https://aistudio.google.com/app/apikey) and click **Get API Key** to generate your Gemini API Key.
    - Add this key to the environment file as explained below.

2. **Java and Maven**  
   Ensure you have the following installed on your system:
    - JDK 18 or higher
    - Apache Maven 3.8+

---

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/michaelik/TimeSpaceTracker.git
cd TimeSpaceTracker
```

### 2. Configure Environment Variables
1. Copy the example environment file:
   ```bash
   cp .env.example .env
   ```  
2. Open `.env` and replace the placeholder with your Gemini API Key:
   ```
   GEMINI_API_KEY=your_api_key_here
   ```

### 3. Build the Application
Run the following Maven command to build the project:
```bash
mvn clean install
```

### 4. Run the Application
Start the application on port `3010` using:
```bash
mvn spring-boot:run
```

---

## API Documentation
API documentation is available at the url below, when the server is running:

- Open your browser and go to:  
  **[http://localhost:3010/swagger-ui.html](http://localhost:3010/swagger-ui.html)**

---

## API Endpoints

### POST `/api/v1/algorithm/analyze`

**Description:**  
Analyzes the given algorithm code and returns time and space complexity metrics.

**Request Body:**
```json
{
  "algorithmCode": "public static boolean isPalindrome(String input){String sanitizedInput = input.replaceAll(\"[^a-zA-Z0-9]\", \"\").toLowerCase();int left = 0;int right = sanitizedInput.length() - 1;while (left < right) {if (sanitizedInput.charAt(left) != sanitizedInput.charAt(right)) {return false;}left++;right--;}return true;}"
}
```

**Response:**
```json
{
    "success": true,
    "message": "Time and Space Complexity Retrieved Successfully",
    "data": {
        "timeComplexity": {
            "bestCase": "O(1)",
            "averageCase": "O(n)",
            "worstCase": "O(n)"
        },
        "spaceComplexity": {
            "bestCase": "O(1)",
            "averageCase": "O(n)",
            "worstCase": "O(n)"
        }
    },
    "time": "2024-12-14T10:24:55.5193805"
}
```
