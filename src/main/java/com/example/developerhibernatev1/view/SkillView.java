package com.example.developerhibernatev1.view;

import com.example.developerhibernatev1.controller.SkillController;
import com.example.developerhibernatev1.model.Skill;
import com.example.developerhibernatev1.repository.hibernate.SkillRepositoryImpl;

public class SkillView {
    private  final SkillController developerController = new SkillController(new SkillRepositoryImpl());
    public  void allSkills() {
        System.out.println(developerController.onlySkillGetAll());

    }
    public  void skillById(Long id) {
        System.out.println(developerController.onlySkillById(id));
    }
    public  void createSkill(Skill skill) {
        System.out.println(developerController.createOnlySkill(skill));
    }
    public  void updateSkill(Skill skill, Long id) {
        System.out.println(developerController.updateSkillById(skill, id));
    }
    public  boolean deleteSkillById(Long id){
        developerController.deleteSkillById(id);
        return true;
    }
}
