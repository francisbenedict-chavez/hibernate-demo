package com.spring.hibernate_demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository 
public class StudentDaoImplement implements StudentDao {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    public StudentDaoImplement(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional 
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override 
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override 
    public List<Student> findAll() {
        // create query
        TypedQuery<Student> query = entityManager.createQuery(
            "FROM Student order by lastName", Student.class);
        
        // return query results
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        // create query
        TypedQuery<Student> query = entityManager.createQuery(
            "FROM Student WHERE lastName=:data", Student.class);
        
        // set query parameter
        query.setParameter("data", lastName);
        
        // return query results
        return query.getResultList();
    }

    @Override 
    @Transactional 
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override 
    @Transactional 
    public void delete(Integer id) {
        // retrieve the student
        Student student = entityManager.find(Student.class, id);

        // delete
        entityManager.remove(student);
    }

    @Override 
    @Transactional 
    public int deleteAll() {
        int numRows = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRows;
    }
}
