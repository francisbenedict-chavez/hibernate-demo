package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * JPA repository implementation for data access operations involving
 * instructors, courses, and students.
 */
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
    InstructorDetail details = entityManager.find(
        InstructorDetail.class, theId);

    // remove the associated object reference
    details.getInstructor().setInstructorDetails(null);

    // delete instructor detail
    entityManager.remove(details);
  }

  @Override
  public List<Course> findCoursesByInstructorId(Integer theId) {
    // create query
    TypedQuery<Course> query = entityManager.createQuery(
        "FROM Course WHERE instructor.id=:data", Course.class);

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
  public void update(Instructor theInstructor) {
    entityManager.merge(theInstructor);
  }

  @Override
  @Transactional
  public void deleteInstructorByIdWithoutRemovingCourses(Integer theId) {
    Instructor instructor = entityManager.find(Instructor.class, theId);

    List<Course> courses = instructor.getCourses();
    for (Course course : courses) {
      course.setInstructor(null);
    }

    entityManager.remove(instructor);
  }

  @Override
  @Transactional
  public void deleteCourseByIdWithoutRemovingInstructor(Integer theId) {
    Course course = entityManager.find(Course.class, theId);

    Instructor instructor = course.getInstructor();
    instructor.getCourses().remove(course);

    course.setInstructor(null);
    entityManager.remove(course);
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

  @Override
  @Transactional
  public void deleteCourseById(Integer theId) {
    // retrieve the course
    Course course = entityManager.find(Course.class, theId);

    // delete the course
    entityManager.remove(course);
  }

  @Override
  public Course findCourseAndStudentsByCourseId(Integer theId) {
    // create query
    TypedQuery<Course> query = entityManager.createQuery(
        "SELECT c FROM  Course c "
            + "JOIN FETCH c.students "
            + "WHERE c.id=:data",
        Course.class);
    query.setParameter("data", theId);

    // execute query
    Course course = query.getSingleResult();

    return course;
  }

  @Override
  public Student findStudentAndCoursesByStudentId(Integer theId) {
    // create query
    TypedQuery<Student> query = entityManager.createQuery(
        "SELECT s FROM Student s "
            + "JOIN FETCH s.courses "
            + "WHERE s.id=:data",
        Student.class);
    query.setParameter("data", theId);

    // execute query
    Student student = query.getSingleResult();

    return student;
  }

  @Override
  @Transactional
  public void update(Student student) {
    entityManager.merge(student);
  }

  @Override
  @Transactional
  public void deleteStudentById(Integer theId) {
    // retrieve student
    Student student = entityManager.find(Student.class, theId);

    if (student != null) {
      // get the courses
      List<Course> courses = student.getCourses();

      // break the associations of courses for student
      for (Course course : courses) {
        course.getStudents().remove(student);
      }
    }

    // delete only the student
    // does not include courses associated
    entityManager.remove(student);
  }

}
