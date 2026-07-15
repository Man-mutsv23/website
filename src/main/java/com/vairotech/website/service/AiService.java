package com.vairotech.website.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
public class AiService {
    
        private final RestClient restClient;

    // This constructor perfectly initializes the restClient so Java doesn't complain
        public AiService() {
        this.restClient = RestClient.builder()
                .baseUrl("http://127.0.0.1:8000")
                .build();
    }

    public String askGemini(String userMessage) {
        try {
            //We will first Package the user's message
            ChatRequest request = new ChatRequest(userMessage);

            //Next We will send the POST request to the Python AI Engine
            ChatResponse response = restClient.post()
                    .uri("/api/chat")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(request)
                    .retrieve()
                    .body(ChatResponse.class);
            
            //Now we return the AI's Response
            return response !=null ? response.reply() : "AI Engine returned an empty response.";

        } catch (Exception e) {
            System.err.println("Failed to connect to Python AI Engine " + e.getMessage());
            return "Sorry, the Vairotech AI brain is currently offline for maintenance.";
        }
    }
}
// Creating records to instantly map the JSON payload to Java objects
record ChatRequest(String message){}
record ChatResponse(String status, String provider, String reply){}
