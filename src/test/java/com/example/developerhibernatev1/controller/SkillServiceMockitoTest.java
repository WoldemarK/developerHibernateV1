package com.example.developerhibernatev1.controller;

import com.example.developerhibernatev1.model.Skill;
import com.example.developerhibernatev1.repository.SkillRepository;
import com.example.developerhibernatev1.repository.hibernate.SkillRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SkillServiceMockitoTest {
    private SkillRepositoryImpl skillRepository = Mockito.mock(SkillRepositoryImpl.class);

    private SkillController skillControllerUnderTest = new SkillController(skillRepository);
    private Long SKILL_ID = 1L;

    private Skill getSkill() {
        return Skill.builder()
                .name("Hibernate1")
                .build();

    }

    @BeforeEach
    void init() {
    }

    @Test
    @DisplayName("Получение всех Skills")
    void getAllSkill() {
        when(skillRepository.getAll()).thenReturn(List.of(getSkill()));
        List<Skill> skills = skillControllerUnderTest.onlySkillGetAll();
        assertFalse(skills.isEmpty());
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
        skillRepository.save(getSkill());
        List<Skill> skills = skillRepository.getAll();
        assertThat(skills).hasSize(8);
    }

}
