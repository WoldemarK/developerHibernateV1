package com.example.developerhibernatev1.view;

import com.example.developerhibernatev1.controller.DeveloperController;
import com.example.developerhibernatev1.model.Developer;

import com.example.developerhibernatev1.repository.hibernate.DeveloperRepositoryImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeveloperView {

    private  final DeveloperController developerController =
            new DeveloperController(new DeveloperRepositoryImpl());
    public  void allDevelopers() {
        System.out.println(developerController.onlyDeveloperGetAll());

    }
    public  void getByIdDeveloper(Long id) {
        System.out.println(developerController.onlyDeveloperById(id));
    }
    public void createDeveloper(Developer developer) {
        System.out.println(developerController.createOnlyDeveloper(developer));
    }
    public  void updateDeveloper(Developer developer, Long id) {
        System.out.println(developerController.updateDeveloperById(developer, id));
    }
    public  boolean deleteDeveloperById(Long id){
    developerController.deleteById(id);
    return true;
    }



}

