package com.example.developerhibernatev1;

import com.example.developerhibernatev1.model.Skill;
import com.example.developerhibernatev1.model.Specialty;
import com.example.developerhibernatev1.view.SkillView;
import com.example.developerhibernatev1.view.SpecialtyView;


public class DeveloperHibernateV1Application {
    public static void main(String[] args) {
//       SpecialtyView specialtyView = new SpecialtyView();
//        specialtyView.createSpecialty(new Specialty().toBuilder()
//                .name("Testing")
//                .build());
//        specialtyView.updateSpecialty(Specialty.builder()
//                .name("QAAAggggA")
//                .build(), 1L);
//        specialtyView.specialtyById(1L);
//        specialtyView.allSpecialty();
//        specialtyView.deleteSpecialtyById(11L);
        SkillView skillView = new SkillView();
//        skillView.deleteSkillById(7L);
//        skillView.updateSkill(Skill.builder()
//                        .name("Teskkkkkkting")
//                .build(), 1L);
//        skillView.allSkills();
//        skillView.skillById(1L);
skillView.createSkill(Skill.builder()
                .name("Test36")
        .build());

    }

}
