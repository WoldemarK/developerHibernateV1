package com.example.developerhibernatev1.repository;

import com.example.developerhibernatev1.model.Developer;


public interface DeveloperRepository extends GenericRepository<Developer,Long> {

    boolean assignmentDevSpecialty(Long devId,Long specId);

}
