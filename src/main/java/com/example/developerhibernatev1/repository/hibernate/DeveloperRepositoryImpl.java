package com.example.developerhibernatev1.repository.hibernate;

import com.example.developerhibernatev1.exception.NotFoundException;
import com.example.developerhibernatev1.model.Developer;
import com.example.developerhibernatev1.model.Specialty;
import com.example.developerhibernatev1.repository.DeveloperRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

import static com.example.developerhibernatev1.util.HibernateSessionFactoryUtil.session;

@RequiredArgsConstructor
public class DeveloperRepositoryImpl implements DeveloperRepository {
    @Override
    public Optional<Developer> save(Developer developer) {
        try (Session session = session()) {
            developer = Developer.builder()
                    .firstName(developer.getFirstName())
                    .lastName(developer.getLastName())
                    .build();
            session.beginTransaction();
            Developer createdDeveloper = session.merge(developer);
            session.getTransaction().commit();
            return Optional.of(createdDeveloper);
        }

    }
    @Override
    public Optional<Developer> update(Developer developer, Long id) {
        try (Session session = session()){
            developer = Developer.builder()
                    .id(id)
                    .firstName(developer.getFirstName())
                    .lastName(developer.getLastName())
                    .build();
            session.beginTransaction();
            session.saveOrUpdate(developer);
            session.getTransaction().commit();
            return Optional.of(developer);
        }
    }
    @Override
    public Optional<Developer> getId(Long id) {
        try (Session session = session()) {
            session.beginTransaction();
            Optional<Developer> developer = Optional.ofNullable(session.get(Developer.class, id));
            if (developer.isPresent()) {
                session.getTransaction().commit();
                return developer;
            }
        }
        throw new NotFoundException("По данному запросу ID не чего не найдено " + id);
    }
    @Override
    @Transactional
    public List<Developer> getAll() {
        try (Session session = session()){
            session.beginTransaction();
            List<Developer> developers = session.createQuery("from Developer").list();
            session.getTransaction().commit();
            return developers;
        }

    }
    @Override
    public void deleteById(Long id) {
        try (Session session = session()){
            session.beginTransaction();
            Queue<Developer> developer = (Queue<Developer>) session.createQuery("from Developer d where d.id=:id");
            session.remove(developer);
            session.getTransaction().commit();
        }
    }
}

