package com.vairotech.website.service;

import com.lowagie.text.Chunk; // Swapped Anchor for Chunk
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.vairotech.website.model.Project;
import com.vairotech.website.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class CVGenService {

    private final ProjectRepository projectRepository;

    public CVGenService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ByteArrayInputStream generateCV() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Setup Fonts
            Font nameFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22);
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.ITALIC);
            Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 11);
            
            // Your clickable hyperlink font
            Font hyperLinkFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.UNDERLINE, Color.BLUE);

            // 1. HEADER
            Paragraph name = new Paragraph("TAPIWANASHE M. MUTSVAIRO", nameFont);
            name.setAlignment(Element.ALIGN_CENTER);
            document.add(name);

            Paragraph title = new Paragraph("SOFTWARE ENGINEER & SYSTEMS ARCHITECT", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            Paragraph contact = new Paragraph("mutsvairotm18@gmail.com | +27 (0) 82 474 4204 | Cape Town, ZA", normalFont);
            contact.setAlignment(Element.ALIGN_CENTER);
            document.add(contact);
            document.add(new Paragraph(" "));

            // 2. PROFESSIONAL SUMMARY
            document.add(new Paragraph("PROFESSIONAL SUMMARY", sectionFont));
            document.add(new Paragraph("Passionate and results-driven Information Technology student specializing in Software Engineering, with a proven track record of designing, building, and deploying robust backend architectures and full-stack solutions.", normalFont));
            document.add(new Paragraph(" "));

            // 3. TECHNICAL SKILLS
            document.add(new Paragraph("CORE TECHNICAL CAPABILITIES", sectionFont));
            document.add(new Paragraph("Java (Spring Boot, JavaFX) | Python (FastAPI, Streamlit) | C# | PostgreSQL & SQLite | Docker Containerization | Thymeleaf | Git & CI/CD Pipelines | Embedded Systems (ESP32)", normalFont));
            document.add(new Paragraph(" "));

            // 4. PROFESSIONAL EXPERIENCE
            document.add(new Paragraph("PROFESSIONAL EXPERIENCE", sectionFont));
            
            document.add(new Paragraph("Junior Backend Engineer / Contractor | Vairo Tech (2025 - Present)", boldFont));
            document.add(new Paragraph("- Architected custom Spring Boot enterprise backend microservices.\n- Designed dynamic JavaFX desktop interfaces linking embedded databases.\n- Streamlined deployment workflows using Docker.", normalFont));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Software Engineering Solutions Intern | Tech Solutions Enterprise (2024 - 2025)", boldFont));
            document.add(new Paragraph("- Built robust automation pipelines utilizing n8n and Python scripts.\n- Configured and optimized PostgreSQL and SQLite databases.", normalFont));
            document.add(new Paragraph(" "));

            // 5. DYNAMIC CORE SYSTEM DEPLOYMENTS
            document.add(new Paragraph("CORE SYSTEM DEPLOYMENTS", sectionFont));
            List<Project> projects = projectRepository.findAll();
            for (Project project : projects) {
                document.add(new Paragraph(project.getTitle(), boldFont));
                document.add(new Paragraph(project.getDescription(), normalFont));
                
                // FIXED: Using Chunk to guarantee clickability for GitHub URLs
                Chunk githubChunk = new Chunk("View Source Repository", hyperLinkFont);
                githubChunk.setAnchor(project.getGithubUrl());
                document.add(new Paragraph(githubChunk));
                document.add(new Paragraph(" "));
            }

            // 6. EDUCATION & CREDENTIALS
            document.add(new Paragraph("EDUCATION & CREDENTIALS", sectionFont));
            
            // Eduvos Degree
            document.add(new Paragraph("BCompSc in Information Technology | Eduvos (Expected 2028)", boldFont));
            
            // FIXED: Using Chunk for the Transcript Link
            Chunk transcriptChunk = new Chunk(">> View Official Transcript (PDF)", hyperLinkFont);
            transcriptChunk.setAnchor("http://localhost:8080/assets/BCompSc_Transcript.pdf"); 
            document.add(new Paragraph(transcriptChunk));
            document.add(new Paragraph(" "));

            // Higher Certificate
            document.add(new Paragraph("Higher Certificate in Information Systems (Software Dev) | Eduvos (Completed 2026)", boldFont));
            
            // FIXED: Using Chunk for the Certificate Link
            Chunk certChunk = new Chunk(">> View Official Certificate (PDF)", hyperLinkFont);
            certChunk.setAnchor("http://localhost:8080/assets/Higher_Cert_Certificate.pdf");
            document.add(new Paragraph(certChunk));

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}