package com.example.developerhibernatev1.repository.hibernate;

import com.example.developerhibernatev1.exception.NotFoundException;
import com.example.developerhibernatev1.model.Developer;
import com.example.developerhibernatev1.model.Specialty;
import com.example.developerhibernatev1.repository.DeveloperRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.developerhibernatev1.util.HibernateSessionFactoryUtil.session;
@RequiredArgsConstructor
public class DeveloperRepositoryImpl implements DeveloperRepository {
    @Override
    public Optional<Developer> save(Developer developer) {
        developer = Developer.builder()
                .firstName(developer.getFirstName())
                .lastName(developer.getLastName())
                .build();
        session()
                .beginTransaction();
        session()
                .persist(developer);
        session()
                .getTransaction().commit();
        return Optional.of(developer);
    }
    @Override
    public Optional<Developer> update(Developer developer, Long id) {
        developer = Developer.builder()
                .id(id)
                .firstName(developer.getFirstName())
                .lastName(developer.getLastName())
                .build();
        session()
                .beginTransaction();
        session()
                .saveOrUpdate(developer);
        session()
                .getTransaction()
                .commit();
        return Optional.of(developer);
    }

    @Override
    public Optional<Developer> getId(Long id) {
        session()
                .beginTransaction();
        Developer developer = session()
                .get(Developer.class, id);
        session()
                .getTransaction()
                .commit();
        return Optional.ofNullable(developer);
    }
    @Override
    @Transactional
    public List<Developer> getAll() {
        session()
                .beginTransaction();
        List developers = session()
                .createQuery("from Developer")
                .list();

        session()
                .getTransaction()
                .commit();
        return developers;
    }
    @Override
    public void deleteById(Long id) {
        session().beginTransaction();
        Optional<Developer> developer = Optional.ofNullable(session().get(Developer.class, id));
        if (developer.isPresent()) {
            session().remove(developer);
        } else throw new NotFoundException("По данному запросу ID не найден " + id);
        session()
                .getTransaction()
                .commit();
    }
    @Override
    public boolean assignmentDevSpecialty(Long devId, Long specId) {
        session().beginTransaction();
        Developer developer = Objects.requireNonNull(session()
                .find(Developer.class, devId), "По данному запросу ID не найден " + devId);
        Specialty specialty = Objects.requireNonNull(session()
                .find(Specialty.class, specId), "По данному запросу ID не найден " + specId);

        developer.setSpecialty(specialty);
        session().persist(developer);
        session()
                .getTransaction()
                .commit();
        return true;
    }
    @Override
    public List<Developer> allInformation() {
        session().beginTransaction();
        List<Developer> developers = session()
                .createQuery("from Developer")
                .list();
        session()
                .getTransaction()
                .commit();

        return developers;
    }

}

