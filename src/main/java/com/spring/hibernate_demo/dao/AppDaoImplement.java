package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class AppDaoImplement implements AppDao {

    private EntityManager entityManager;

    public AppDaoImplement(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(Integer theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(Integer theId) {
        // retreve the instructor
        Instructor instructor = entityManager.find(Instructor.class, theId);

        entityManager.remove(instructor);
    }

}
