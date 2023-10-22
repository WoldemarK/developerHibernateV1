package com.example.developerhibernatev1.util;

import com.example.developerhibernatev1.model.Developer;
import com.example.developerhibernatev1.model.Skill;
import com.example.developerhibernatev1.model.Specialty;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@NoArgsConstructor
public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .configure();
                configuration.addAnnotatedClass(Developer.class);
                configuration.addAnnotatedClass(Skill.class);
                configuration.addAnnotatedClass(Specialty.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.
                                getProperties());
                sessionFactory = configuration
                        .buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
    public static Session session() {
        //return getSessionFactory().getCurrentSession();
        return getSessionFactory().openSession();
    }
}
