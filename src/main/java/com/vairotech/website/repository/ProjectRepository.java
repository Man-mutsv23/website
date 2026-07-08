package com.vairotech.website.repository;

import com.vairotech.website.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    //This will populate with the files automatically once we list them in the database
}
