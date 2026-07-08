package com.vairotech.website.model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    void testGettersAndSetters() {
        Category c = new Category();
        c.setId(7L);
        c.setName("Utilities");

        Set<Project> projects = new HashSet<>();
        Project p = new Project();
        projects.add(p);
        c.setProjects(projects);

        assertEquals(7L, c.getId());
        assertEquals("Utilities", c.getName());
        assertSame(projects, c.getProjects());
    }
}
