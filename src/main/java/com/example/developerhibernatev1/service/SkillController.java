package com.example.developerhibernatev1.service;
import com.example.developerhibernatev1.model.Skill;
import com.example.developerhibernatev1.repository.hibernate.SkillRepositoryImpl;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SkillController {

    private final SkillRepositoryImpl skillRepository;
    public List<Skill> onlySkillGetAll() {
        return skillRepository.getAll();
    }
    public Optional<Skill> onlySkillById(Long id) {
        return skillRepository.getId(id);
    }
    public Skill createOnlySkill(Skill skill)  {
        return skillRepository.save(skill).get();
    }
    public void deleteSkillById( Long id) {
        skillRepository.deleteById(id);
    }
    public Skill updateSkillById( Skill skill,  Long id)  {
        return skillRepository.update(skill, id).get();
    }
}
