package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Instructor;

public interface AppDao {

    void save(Instructor theInstructor);

    Instructor findInstructorById(Integer theId);

    void deleteInstructorById(Integer theId);

}
