package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

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

    @Override
    public InstructorDetail findInstructorDetailById(Integer theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetail(Integer theId) {
        // retrieve instructor detail
        InstructorDetail details = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference
        details.getInstructor().setInstructorDetails(null);

        // delete instructor detail
        entityManager.remove(details);
    }

    @Override
    public List<Course> findCoursesByInstructorId(Integer theId) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "FROM Course WHERE instructor.id=:id", Course.class);

        query.setParameter("data", theId);

        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(Integer theId) {
        // create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "SELECT i from Instructor i "
                        + "JOIN FETCH i.courses "
                        + "JOIN FETCH i.instructorDetails "
                        + "WHERE i.id=:data",
                Instructor.class);
        query.setParameter("data", theId);

        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorByIdWithoutRemovingCourses(Integer theId) {
        // retrieve instructor
        Instructor instructor = entityManager.find(Instructor.class, theId);

        // break the associations of courses for instructor
        List<Course> courses = instructor.getCourses();
        for (Course course : courses) {
            // remove the instructor from the courses
            course.setInstructor(null);
        }

        // delete only the instructor
        // does not include courses associated
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(Integer theId) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c "
                        + "JOIN FETCH c.review "
                        + "WHERE c.id=:data",
                Course.class);

        query.setParameter("data", theId);

        Course course = query.getSingleResult();

        return course;
    }

}
