package com.spring.hibernate_demo;

import com.spring.hibernate_demo.dao.AppDao;
import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Review;
import com.spring.hibernate_demo.entity.Student;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main entry point for the Hibernate demo application.
 *
 * <p>
 * Starts the Spring Boot application context and initializes the demo.
 * </p>
 */
@SpringBootApplication
public class HibernateDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(HibernateDemoApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(StudentDao studentDao, AppDao app) {
    return runner -> {
      // First part: Using StudentDao
      createStudent(studentDao);
      readStudent(studentDao);
      queryStudents(studentDao);
      queryStudentsLastName(studentDao);
      updateStudent(studentDao);
      deleteStudent(studentDao);
      deleteAllStudents(studentDao);

      // Second part: Using AppDao
      createInstructor(app);
      findInstructor(app);
      deleteInstructor(app);
      findInstructorDetail(app);
      deleteInstructorDetail(app);
      createInstructorWithCourses(app);
      findInstructorWithCourses(app);
      findCoursesForInstructor(app);
      findInstructorWithCoursesJoinFetch(app);
      updateInstructor(app);
      deleteInstructorOnly(app);
      createCourseAndReviews(app);
      retrieveCourseAndReviews(app);
      deleteCourseAndReviews(app);
      createCourseAndStudents(app);
      findCourseAndStudents(app);
      findStudentAndCourses(app);
      addMoreCoursesForStudent(app);
      deleteCourseById(app);
      deleteStudent(app);
    };
  }

  /**
   * First part: Using StudentDao.
   */
  private void createStudent(StudentDao studentDao) {
    System.out.println("Creating student information...");

    Student student1 = new Student(
        "Light",
        "Yagami",
        "light.yagami@example.com");
    studentDao.save(student1);

    Student student2 = new Student(
        "Leslie",
        "Andrews",
        "leslie.andrews@example.com");
    studentDao.save(student2);

    Student student3 = new Student(
        "Dexter",
        "Lauron",
        "dexter.lauron@example.com");
    studentDao.save(student3);

    System.out.println("Saving student information...");

    System.out.println("Saved! Generated id: " + student1.getId());
    System.out.println("Saved! Generated id: " + student2.getId());
    System.out.println("Saved! Generated id: " + student3.getId());
  }

  private void readStudent(StudentDao studentDao) {
    System.out.println("Retrieving student id 2...");
    Student student = studentDao.findById(2);
    System.out.println("Found the student: " + student);
  }

  private void queryStudents(StudentDao studentDao) {
    List<Student> students = studentDao.findAll();

    for (Student student : students) {
      System.out.println(student);
    }
  }

  private void queryStudentsLastName(StudentDao studentDao) {
    List<Student> students = studentDao.findByLastName("Andrews");

    for (Student student : students) {
      System.out.println(student);
    }
  }

  private void updateStudent(StudentDao studentDao) {
    int id = 1;

    System.out.println("Student ID: " + id);
    Student student = studentDao.findById(id);

    System.out.println("Updating student name...");
    student.setFirstName("Dark");
    student.setLastName("Vader");
    student.setEmail("dark.vader@example.com");

    studentDao.update(student);
    System.out.println("Updated student information: " + student);
  }

  private void deleteStudent(StudentDao studentDao) {
    int id = 2;

    System.out.println("Delete ID: " + id);
    studentDao.delete(id);
  }

  private void deleteAllStudents(StudentDao studentDao) {
    System.out.println("Deleting all student information...");
    int rowsDeleted = studentDao.deleteAll();
    System.out.println("Number of deleted rows: " + rowsDeleted);
  }

  /**
   * Second part: Using AppDao.
   */
  private void createInstructor(AppDao app) {
    Instructor instructor1 = new Instructor(
        "Vicky",
        "Magtatanggol",
        "vicky.mags@example.com");
    InstructorDetail details1 = new InstructorDetail(
        "Math",
        "Protecting People With Geometry");

    instructor1.setInstructorDetails(details1);
    app.save(instructor1);

    Instructor instructor2 = new Instructor(
        "Ana",
        "Alimurung",
        "ana.alimurung@example.com");
    InstructorDetail details2 = new InstructorDetail(
        "Computer Science",
        "Solving Automata Theory");

    instructor2.setInstructorDetails(details2);
    app.save(instructor2);

    System.out.println("Information saved!");
  }

  private void findInstructor(AppDao app) {
    int id = 1;

    System.out.println("Find id: " + id);
    Instructor instructor = app.findInstructorById(id);

    System.out.println("Instructor: " + instructor);
    System.out.println("Details: " + instructor.getInstructorDetails());
  }

  private void deleteInstructor(AppDao app) {
    int id = 1;

    System.out.println("Delete id: " + id);
    app.deleteInstructorById(id);

    System.out.println("Deleted successfully!");
  }

  private void findInstructorDetail(AppDao app) {
    int id = 2;
    InstructorDetail instructorDetails = app.findInstructorDetailById(id);

    System.out.println("Details: " + instructorDetails);

    System.out.println("Instructor: " + instructorDetails.getInstructor());
  }

  private void deleteInstructorDetail(AppDao app) {
    int id = 2;

    System.out.println("Delete id: " + id);
    app.deleteInstructorDetail(id);

    System.out.println("Deleted successfully!");
  }

  private void createInstructorWithCourses(AppDao app) {
    Instructor instructor1 = new Instructor("Peri",
        "Winkle",
        "peri.winkle@example.com");
    InstructorDetail details1 = new InstructorDetail("Science",
        "Sprinkling dust");

    instructor1.setInstructorDetails(details1);

    Course course1 = new Course("Physics");
    Course course2 = new Course("Biology");

    instructor1.add(course1);
    instructor1.add(course2);

    app.save(instructor1);

    Instructor instructor2 = new Instructor(
        "Jamel",
        "Hadjirasul",
        "jamel.hadjirasul@example.com");
    InstructorDetail details2 = new InstructorDetail(
        "Computer Engineering",
        "Solving Ana's Problem");

    instructor2.setInstructorDetails(details2);

    Course course3 = new Course("Computer Architecture");
    Course course4 = new Course("Digital Signal Processing");

    instructor2.add(course3);
    instructor2.add(course4);

    app.save(instructor2);

    System.out.println("Course saved: " + instructor1.getCourses());
    System.out.println("Course saved: " + instructor2.getCourses());
  }

  /**
   * Finds an instructor and prints their courses.
   *
   * <p>
   * The {@code courses} relationship is configured for lazy loading. Accessing
   * it after the persistence context has closed can cause a
   * {@code LazyInitializationException}. Rather than changing the relationship
   * to {@code EAGER}, use
   * {@link AppDao#findInstructorByIdJoinFetch(Integer)} to fetch the courses
   * for this query.
   * </p>
   *
   * @param app the DAO used to retrieve the instructor and courses
   */
  private void findInstructorWithCourses(AppDao app) {
    int id = 3;

    System.out.println("Find instructor id: " + id);
    try {
      Instructor instructor = app.findInstructorById(id);

      if (instructor == null) {
        System.out.println("No instructor found with id " + id);
        return;
      }

      System.out.println("Instructor: " + instructor);
      System.out.println("Courses: " + instructor.getCourses());
    } catch (org.hibernate.LazyInitializationException e) {
      System.err.println(e.getMessage());
    }
  }

  private void findCoursesForInstructor(AppDao app) {
    int id = 3;

    System.out.println("Find instructor id: " + id);
    Instructor instructor = app.findInstructorById(id);

    System.out.println("Instructor: " + instructor);

    System.out.println("Courses from id: " + id);
    List<Course> courses = app.findCoursesByInstructorId(id);

    instructor.setCourses(courses);

    System.out.println(instructor.getCourses());
  }

  private void findInstructorWithCoursesJoinFetch(AppDao app) {
    int id = 3;

    System.out.println("Find instructor id: " + id);
    Instructor instructor = app.findInstructorByIdJoinFetch(id);

    System.out.println("Instructor: " + instructor);
    System.out.println("Courses: " + instructor.getCourses());
  }

  private void updateInstructor(AppDao app) {
    int id = 3;

    System.out.println("Find instructor id: " + id);
    Instructor instructor = app.findInstructorById(id);

    instructor.setFirstName("Dexter");
    instructor.setLastName("Lauron");
    instructor.setEmail("dexter.lauron@example.com");

    app.update(instructor);

    System.out.println("Updated!");
  }

  private void deleteInstructorOnly(AppDao app) {
    int id = 4;

    System.out.println("Delete instructor id: " + id);
    app.deleteInstructorByIdWithoutRemovingCourses(id);

    System.out.println("Deleted successfully!");
  }

  private void createCourseAndReviews(AppDao app) {
    Course course1 = new Course("How to Speed Run Spring in 1 Day");

    course1.addReview(new Review(
        "Great speed 2x... loved it!!!"));
    course1.addReview(new Review(
        "I wanted the speed 0.75x :(("));
    course1.addReview(new Review(
        "Hoping to do a speed of 3x :D. Anyways, nice!"));

    app.save(course1);

    Course course2 = new Course("How to Train Your Dragon");

    course2.addReview(new Review("Love the dragons!!!"));
    course2.addReview(new Review("The movie is nice."));
    course2.addReview(new Review("Hoping to have a part 2."));

    app.save(course2);

    System.out.println("Course saved!");
  }

  private void retrieveCourseAndReviews(AppDao app) {
    int id = 5;

    System.out.println("Get course and reviews from id: " + id);
    Course course = app.findCourseAndReviewsByCourseId(id);

    System.out.println(course);
    System.out.println(course.getReview());
  }

  private void deleteCourseAndReviews(AppDao app) {
    int id = 6;

    System.out.println("Delete course: " + id);
    app.deleteCourseById(id);

    System.out.println("Deleted successfully!");
  }

  private void createCourseAndStudents(AppDao app) {
    Course course = new Course(
        "How to Be A FullStack Dev in Half a Day");

    Student student1 = new Student(
        "Light",
        "Yagami",
        "light.yagami@example.com");
    Student student2 = new Student(
        "Leslie",
        "Andrews",
        "leslie.andrews@example.com");

    course.addStudent(student1);
    course.addStudent(student2);

    app.save(course);

    System.out.println("Successfully enrolled!");
  }

  private void findCourseAndStudents(AppDao app) {
    int id = 7;

    Course course = app.findCourseAndStudentsByCourseId(id);

    System.out.println("Course: " + course);
    System.out.println("Students: " + course.getStudents());
  }

  private void findStudentAndCourses(AppDao app) {
    int id = 4;

    Student student = app.findStudentAndCoursesByStudentId(id);

    System.out.println("Student: " + student);
    System.out.println("Courses: " + student.getCourses());
  }

  private void addMoreCoursesForStudent(AppDao app) {
    int id = 5;

    Student student = app.findStudentAndCoursesByStudentId(id);

    Course course1 = new Course("How to Test without Running Application");
    Course course2 = new Course("How to Develop without Computer");
    Course course3 = new Course("How to Be a Legit Developer");

    student.addCourse(course1);
    student.addCourse(course2);
    student.addCourse(course3);

    app.update(student);

    System.out.println("Sucessfully enrolled to new courses!");
  }

  private void deleteCourseById(AppDao app) {
    int id = 8;

    System.out.println("Delete course: " + id);
    app.deleteCourseById(id);

    System.out.println("Deleted successfully!");
  }

  private void deleteStudent(AppDao app) {
    int id = 4;

    System.out.println("Delete student: " + id);
    app.deleteStudentById(id);

    System.out.println("Deleted successfully!");
  }

}
