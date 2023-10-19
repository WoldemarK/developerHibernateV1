package com.example.developerhibernatev1.service;
import com.example.developerhibernatev1.model.Skill;
import com.example.developerhibernatev1.repository.hibernate.SkillRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class SkillServiceTest {
    private SkillRepositoryImpl skillRepository;
    private Long SKILL_ID = 1L;
    private Skill skill;
    @BeforeEach
    void init() {
        skillRepository = new SkillRepositoryImpl();
        skill = Skill.builder()
                .name("Hibernate1")
                .build();
    }
    @Test
    @DisplayName("Получение всех Skills")
    void getAllSkill() {
        Optional<List<Skill>> skills = Optional.ofNullable(skillRepository.getAll());
        assertTrue(skills.isPresent());
        assertNotNull(skill);
    }
    @Test
    @DisplayName("Получение Skill по ID")
    void getId() {
        Optional<Skill> skill = skillRepository.getId(SKILL_ID);
        assertNotNull(skill);
        assertEquals(skill.get().getId(), SKILL_ID);
    }
    @Test
    @DisplayName("Сохранение Skill")
    void createSkill() {
        skillRepository.save(skill);
        List<Skill>skills = skillRepository.getAll();
        assertThat(skills).hasSize(8);
    }

}
