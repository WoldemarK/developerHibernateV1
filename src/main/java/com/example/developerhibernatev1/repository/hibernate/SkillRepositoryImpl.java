package com.example.developerhibernatev1.repository.hibernate;

import com.example.developerhibernatev1.exception.NotFoundException;
import com.example.developerhibernatev1.model.Developer;
import com.example.developerhibernatev1.model.Skill;
import com.example.developerhibernatev1.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static com.example.developerhibernatev1.util.HibernateSessionFactoryUtil.session;

@RequiredArgsConstructor
public class SkillRepositoryImpl implements SkillRepository {
    @Override
    public Optional<Skill> save(Skill skill) {
        try(Session session = session()) {
            skill = Skill.builder()
                    .name(skill.getName())
                    .build();
            session.beginTransaction();

            List<Skill> skills = getAll();
            for (Skill res:skills){
                if (res.getName().equals(skill.getName())){
                    throw new NotFoundException("Такой Skill имеется не нужно повторно его создавать " + skill.getName());
                }
            }
            Skill createdDeveloper = session.merge(skill);
            session.getTransaction().commit();
            return Optional.of(createdDeveloper);
        }
    }
    @Override
    public Optional<Skill> getId(Long id) {
        try (Session session = session()) {
            Optional<Skill> skill = Optional.ofNullable(session().get(Skill.class, id));
            if (skill.isPresent()) {
                session.getTransaction().commit();
                return skill;
            }
        }
        throw new NotFoundException("По данному запросу ID не чего не найдено " + id);
    }
    @Override
    public List<Skill> getAll() {
        try (Session session = session()) {
            return session.createQuery("from Skill").getResultList();
        }
    }
    @Override
    public void deleteById(Long id) {
        try (Session session = session()){
            session.beginTransaction();
            Skill skill = (Skill) session.createQuery("from Skill d where d.id=:id").getSingleResult();
            session.remove(skill);
            session.getTransaction().commit();
        }
    }
    @Override
    public Optional<Skill> update(Skill skill, Long id) {
        try (Session session = session()) {
            skill = Skill.builder()
                    .id(id)
                    .name(skill.getName())
                    .build();
            session.beginTransaction();
            session.saveOrUpdate(skill);
            session.getTransaction().commit();
            return Optional.of(skill);
        }

    }

}
