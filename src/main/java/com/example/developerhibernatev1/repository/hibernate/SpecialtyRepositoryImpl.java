package com.example.developerhibernatev1.repository.hibernate;

import com.example.developerhibernatev1.model.Specialty;
import com.example.developerhibernatev1.repository.SpecialtyRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.example.developerhibernatev1.util.HibernateSessionFactoryUtil.session;

@RequiredArgsConstructor
public class SpecialtyRepositoryImpl implements SpecialtyRepository {
    @Override
    public Optional<Specialty> save(Specialty specialty) {
        specialty = Specialty.builder()
                .name(specialty.getName())
                .build();
        session()
                .beginTransaction();
        session()
                .persist(specialty);
        session().getTransaction().commit();
        return Optional.of(specialty);
    }
    @Override
    public Optional<Specialty> update(Specialty specialty, Long id) {
        specialty = Specialty.builder()
                .id(id)
                .name(specialty.getName())
                .build();
        session()
                .beginTransaction();
        session()
                .saveOrUpdate(specialty);
        session()
                .getTransaction()
                .commit();
        return Optional.of(specialty);
    }
    @Override
    public Optional<Specialty> getId(Long id) {
        session()
                .beginTransaction();
        Specialty specialty = session()
                .get(Specialty.class, id);
        session().getTransaction()
                .commit();
        return Optional.ofNullable(specialty);
    }
    @Override
    public List<Specialty> getAll() {
        session()
                .beginTransaction();
        List specialties = session()
                .createQuery("from Specialty")
                .getResultList();
        session()
                .getTransaction().
                commit();
        return specialties;
    }
    @Override
    public void deleteById(Long id) {
        session()
                .beginTransaction();
        Specialty specialty = session()
                .get(Specialty.class,id);
        session()
                .delete(specialty);
        session()
                .getTransaction()
                .commit();
    }
}
