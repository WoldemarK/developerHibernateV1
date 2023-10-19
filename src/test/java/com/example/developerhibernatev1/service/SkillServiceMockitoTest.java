package com.example.developerhibernatev1.service;

import com.example.developerhibernatev1.model.Skill;
import com.example.developerhibernatev1.repository.SkillRepository;
import com.example.developerhibernatev1.repository.hibernate.SkillRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
@ExtendWith(MockitoExtension.class)
public class SkillServiceMockitoTest {
    @InjectMocks
    private SkillRepositoryImpl skillRepository;
    @Mock
    private SkillRepository repository;
    private Skill skill;
    private Long GET_SKILL_ID = 1L;

    @BeforeEach
    void initSkill() {
        skill = Skill.builder().id(GET_SKILL_ID).name("Hibernate").build();
    }

    @Test
    @DisplayName("Получение всех Skills")
    void getAllSkill() {
        Optional<List<Skill>> skills = Optional.ofNullable(skillRepository.getAll());
        assertTrue(skills.isPresent());
    }

}
