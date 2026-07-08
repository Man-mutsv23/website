package com.vairotech.website.controller;

import com.vairotech.website.model.Project;
import com.vairotech.website.repository.ProjectRepository;
import com.vairotech.website.service.CVGenService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayInputStream;
import java.util.List;


@Controller
public class portfolioController {

    private final ProjectRepository projectRepository;
    private final CVGenService cvGenService;

    public portfolioController(ProjectRepository projectRepository, CVGenService cvGenService) {
        this.projectRepository = projectRepository;
        this.cvGenService = cvGenService;
    }

    @GetMapping("/")
    public String home(Model model) {
        //The tagline
        model.addAttribute("tagline", "This website was created on the basis of making really cool stuff!");

        //Spring should look for and display the index/home page
        return "index";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        List<Project> allProjects = projectRepository.findAll();

        model.addAttribute("projects",allProjects);
        return "projects";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/myCV")
    public String viewCV(Model model) {
        // Fetch all your projects from the permanent database
        List<Project> allProjects = projectRepository.findAll();
        
        // Hand the data to the HTML template
        model.addAttribute("projects", allProjects);
        
        // Tells Spring Boot to look for a file named "myCV.html"
        return "myCV"; 
    }



    @GetMapping(value = "/download-cv", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadCV() {
        //Trigger the factory to build the PDF
        ByteArrayInputStream pdfStream = cvGenService.generateCV();

        //Tell the browser to download it as a file called "Vairotech_CV.pdf"
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Vairotech_CV.pdf");

        return ResponseEntity
        .ok()
        .headers(headers)
        .contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(pdfStream));

    }
    
    
    
}
