package com.example.developerhibernatev1.controller;

import com.example.developerhibernatev1.exception.NotFoundException;
import com.example.developerhibernatev1.model.Specialty;
import com.example.developerhibernatev1.repository.hibernate.SpecialtyRepositoryImpl;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyRepositoryImpl specialtyRepository;
    public Specialty updateSpecialtyById(Specialty specialty, Long id) {
        return specialtyRepository.update(specialty, id).get();
    }
    public List<Specialty> onlySpecialtyGetAll()  {
        return specialtyRepository.getAll();
    }
    public Specialty onlySpecialtyById( Long id)  {
        return specialtyRepository.getId(id).get();
    }
    public Specialty createOnlySpecialty(Specialty specialty) {
        return specialtyRepository.save(specialty).get();
    }
    public void deleteSpecialtyById( Long id)  {
        specialtyRepository.deleteById(id);
    }

}
