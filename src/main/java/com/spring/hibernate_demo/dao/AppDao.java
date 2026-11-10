package com.spring.hibernate_demo.dao;

import java.util.List;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;

public interface AppDao {

    void save(Instructor theInstructor);

    Instructor findInstructorById(Integer theId);

    void deleteInstructorById(Integer theId);

    InstructorDetail findInstructorDetailById(Integer theId);

    void deleteInstructorDetail(Integer theId);

    List<Course> findCoursesByInstructorId(Integer theId);

    Instructor findInstructorByIdJoinFetch(Integer theId);

    void update(Instructor instructor);

    void deleteInstructorByIdWithoutRemovingCourses(Integer theId);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(Integer theId);

    void deleteCourseById(Integer theId);

    Course findCourseAndStudentsByCourseId(Integer theId);

    Student findStudentAndCoursesByStudentId(Integer theId);

    void update(Student student);

    void deleteStudentById(Integer theId);

}
