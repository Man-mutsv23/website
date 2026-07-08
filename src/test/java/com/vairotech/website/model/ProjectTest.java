package com.vairotech.website.model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

class ProjectTest {

    @Test
    void testGettersAndSetters() {
        Project p = new Project();
        p.setId(42L);
        p.setTitle("Test Project");
        p.setDescription("A sample project description");
        p.setGithubUrl("https://github.com/example");
        p.setLiveDemoUrl("https://example.com/demo");

        Set<Category> cats = new HashSet<>();
        Category c = new Category();
        cats.add(c);
        p.setCategories(cats);

        assertEquals(42L, p.getId());
        assertEquals("Test Project", p.getTitle());
        assertEquals("A sample project description", p.getDescription());
        assertEquals("https://github.com/example", p.getGithubUrl());
        assertEquals("https://example.com/demo", p.getLiveDemoUrl());
        assertSame(cats, p.getCategories());
    }
}
