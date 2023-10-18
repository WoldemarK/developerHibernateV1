package com.example.developerhibernatev1.repository.hibernate;
import com.example.developerhibernatev1.model.Skill;
import com.example.developerhibernatev1.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import static com.example.developerhibernatev1.util.HibernateSessionFactoryUtil.session;
@RequiredArgsConstructor
public class SkillRepositoryImpl implements SkillRepository {
    @Override
    public Optional<Skill> save(Skill skill) {
        try {
            skill = Skill.builder()
                    .name(skill.getName())
                    .build();
            session().beginTransaction();
            session().persist(skill);
            session().getTransaction().commit();
        } finally {
            session().close();
        }

        return Optional.ofNullable(skill);
    }
    @Override
    public Optional<Skill> getId(Long id) {
        session()
                .beginTransaction();
        Skill skill = session()
                .get(Skill.class, id);
        session()
                .getTransaction()
                .commit();
        return Optional
                .ofNullable(skill);
    }
    @Override
    public List<Skill> getAll() {
        session()
                .beginTransaction();
        List skills = session()
                .createQuery("from Skill")
                .getResultList();
        session()
                .getTransaction()
                .commit();
        return skills;
    }
    @Override
    public void deleteById(Long id) {
        session()
                .beginTransaction();
        Skill skill = session().get(Skill.class, id);
        session()
                .delete(skill);
        session()
                .getTransaction().commit();
    }
    @Override
    public Optional<Skill> update(Skill skill, Long id) {
        skill = Skill.builder()
                .id(id)
                .name(skill.getName())
                .build();
        session()
                .beginTransaction();
        session()
                .saveOrUpdate(skill);
        session()
                .getTransaction()
                .commit();
        return Optional.of(skill);
    }
}
