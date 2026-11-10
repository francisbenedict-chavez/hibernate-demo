package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Student;
import java.util.List;

/**
 * Defines the data-access contract for managing {@link Student} entities.
 */
public interface StudentDao {

  void save(Student theStudent);

  Student findById(Integer id);

  List<Student> findAll();

  List<Student> findByLastName(String lastName);

  void update(Student theStudent);

  void delete(Integer id);

  int deleteAll();

}
