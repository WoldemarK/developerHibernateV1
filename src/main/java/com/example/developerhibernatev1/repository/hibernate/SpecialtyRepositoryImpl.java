package com.example.developerhibernatev1.repository.hibernate;

import com.example.developerhibernatev1.exception.NotFoundException;
import com.example.developerhibernatev1.model.Specialty;
import com.example.developerhibernatev1.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static com.example.developerhibernatev1.util.HibernateSessionFactoryUtil.session;

@RequiredArgsConstructor
public class SpecialtyRepositoryImpl implements SpecialtyRepository {
    @Override
    public Optional<Specialty> save(Specialty specialty) {
        try (Session session = session()) {
            specialty = Specialty.builder()
                    .name(specialty.getName())
                    .build();
            session.beginTransaction();

            List<Specialty>specialties = getAll();
            for (Specialty result:specialties){
                if (result.getName().equals(specialty.getName())){
                    throw new NotFoundException("Такая Specialty имеется не нужно повторно еу создавать " + specialty.getName());
                }
            }
            Specialty createdSpecialty = session.merge(specialty);
            session.getTransaction().commit();
            return Optional.of(createdSpecialty);
        }

    }

    @Override
    public Optional<Specialty> update(Specialty specialty, Long id) {
        try (Session session = session()) {
            specialty = Specialty.builder()
                    .id(id)
                    .name(specialty.getName())
                    .build();
            session.beginTransaction();
            session.saveOrUpdate(specialty);
            session.getTransaction().commit();
            return Optional.of(specialty);
        }
    }

    @Override
    public Optional<Specialty> getId(Long id) {
        try (Session session = session()) {
            session.beginTransaction();
            Optional<Specialty> specialty = Optional.ofNullable(session.get(Specialty.class, id));
            if (specialty.isPresent()) {
                session.getTransaction().commit();
                return specialty;
            }
        }
        throw new NotFoundException("По данному запросу ID не чего не найдено " + id);
    }

    @Override
    public List<Specialty> getAll() {
        try (Session session = session()) {
            session.beginTransaction();
            List<Specialty> specialties = session.createQuery("from Specialty").getResultList();
            session.getTransaction().commit();
            return specialties;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = session()) {
            session.beginTransaction();
            Specialty specialty = session.get(Specialty.class, id);
            session.remove(specialty);
            session.getTransaction().commit();
        }
    }
}