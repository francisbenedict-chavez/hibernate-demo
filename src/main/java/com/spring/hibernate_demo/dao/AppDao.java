package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;
import java.util.List;

/**
 * Defines data access operations for instructors, courses, and students.
 */
public interface AppDao {

  void save(Instructor theInstructor);

  Instructor findInstructorById(Integer theId);

  void deleteInstructorById(Integer theId);

  InstructorDetail findInstructorDetailById(Integer theId);

  void deleteInstructorDetail(Integer theId);

  List<Course> findCoursesByInstructorId(Integer theId);

  Instructor findInstructorByIdJoinFetch(Integer theId);

  void update(Instructor theInstructor);

  void deleteInstructorByIdWithoutRemovingCourses(Integer theId);

  void deleteCourseByIdWithoutRemovingInstructor(Integer theId);

  void save(Course theCourse);

  Course findCourseAndReviewsByCourseId(Integer theId);

  void deleteCourseById(Integer theId);

  Course findCourseAndStudentsByCourseId(Integer theId);

  Student findStudentAndCoursesByStudentId(Integer theId);

  void update(Student student);

  void deleteStudentById(Integer theId);

}
