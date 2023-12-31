package com.example.developerhibernatev1.controller;

import com.example.developerhibernatev1.exception.NotFoundException;
import com.example.developerhibernatev1.model.Developer;
import com.example.developerhibernatev1.repository.hibernate.DeveloperRepositoryImpl;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperRepositoryImpl developerRepository;
    public List<Developer> onlyDeveloperGetAll() {
        return developerRepository.getAll();
    }
    public Developer onlyDeveloperById(Long id) {
        return developerRepository.getId(id).orElseThrow(()->new NotFoundException("По данному запросу ID не найден " + id));
    }
    public Developer createOnlyDeveloper(Developer developer) {
        return developerRepository.save(developer).get();
    }
    public Developer updateDeveloperById(Developer developer, Long id) {
        return developerRepository.update(developer, id).orElseThrow(()->new NotFoundException("По данному запросу ID не найден " + id));
    }
    public void deleteById(Long id) {
        developerRepository.deleteById(id);
    }

}
