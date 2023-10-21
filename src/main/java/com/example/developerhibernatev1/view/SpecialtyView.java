package com.example.developerhibernatev1.view;

import com.example.developerhibernatev1.controller.SpecialtyController;
import com.example.developerhibernatev1.model.Specialty;
import com.example.developerhibernatev1.repository.hibernate.SpecialtyRepositoryImpl;

public class SpecialtyView {
    private  final SpecialtyController specialtyController = new SpecialtyController(new SpecialtyRepositoryImpl());
    public  void allSpecialty() {
        System.out.println(specialtyController.onlySpecialtyGetAll());

    }
    public  void specialtyById(Long id) {
        System.out.println(specialtyController.onlySpecialtyById(id));
    }
    public  void createSpecialty(Specialty specialty) {
        System.out.println(specialtyController.createOnlySpecialty(specialty));
    }
    public  void updateSpecialty(Specialty specialty, Long id) {
        System.out.println(specialtyController.updateSpecialtyById(specialty, id));
    }
    public  boolean deleteSpecialtyById(Long id){
        specialtyController.deleteSpecialtyById(id);
        return true;
    }
}
