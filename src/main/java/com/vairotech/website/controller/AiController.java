package com.vairotech.website.controller;

import com.vairotech.website.service.AiService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/internal")
public class AiController {

    private final AiService aiService;

    // Standard constructor parameter injection 
    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/ask")
    public Map<String, String> askAi(@RequestBody Map<String, String> payload) {
        // 1. Extract the user's text from the incoming JavaScript JSON payload
        String userMessage = payload.get("message");
        
        // 2. Pass it to your AiService to cross the polyglot bridge to Python
        // (Note: Adjust "askPythonAi" to match whatever you named the method in your AiService class)
        String pythonResponse = aiService.askGemini(userMessage); 
        
        // 3. Package the response exactly how the JavaScript texting bubbles expect it: { "reply": "..." }
        Map<String, String> response = new HashMap<>();
        response.put("reply", pythonResponse);
        
        return response;
    }
}