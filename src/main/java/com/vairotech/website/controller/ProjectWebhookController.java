package com.vairotech.website.controller;

import com.vairotech.website.model.*;
import com.vairotech.website.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;



@RestController
@RequestMapping("/api/v1/automation")
public class ProjectWebhookController {
    
    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping("/github-deploy")
    public ResponseEntity<String> handleGitHubWebhook(@RequestBody Map<String, String> payload) {
        
        String title = payload.get("title");
        String description = payload.get("description");
        String githubUrl = payload.get("githubUrl");

        //Creating the new project instance
        Project newProject = new Project();
        newProject.setTitle(title);
        newProject.setDescription(description);
        newProject.setGithubUrl(githubUrl);
        
        //The logic for the category that the new project will fall under
        Category defaultCategory = new Category();
        defaultCategory.setName("Software");

        Set<Category> projectCategories = new HashSet<>();
        projectCategories.add(defaultCategory);

        newProject.setCategories(projectCategories);

        projectRepository.save(newProject);// This saves that new project to the database automatically

        // Generate and print the LinkedIn Post to the Java Console
        System.out.println("\n--- GENERATED LINKEDIN POST ---");
        System.out.println("🚀 Just deployed some really cool stuff to VAIROTECH.");
        System.out.println("I recently built '" + newProject.getTitle() + "' using my core stack.");
        System.out.println(newProject.getDescription());
        System.out.println("🛠️ System Specs & Deep-Dive Documentation: " + newProject.getGithubUrl());
        System.out.println("#SoftwareEngineering #Java #CloudArchitecture #BuildInPublic");
        System.out.println("-------------------------------\n");


     
        return ResponseEntity.ok("Project successfully ingested and saved to the Vairotech database!");
    }
}
