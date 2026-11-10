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

  /*
   * @Bean
   * CommandLineRunner commandLineRunner(StudentDao studentDao) {
   * return runner -> {
   * createStudent(studentDao);
   * readStudent(studentDao);
   * queryStudents(studentDao);
   * queryStudentsLastName(studentDao);
   * updateStudent(studentDao);
   * deleteStudent(studentDao);
   * deleteAllStudents(studentDao);
   * };
   * }
   */

  @Bean
  CommandLineRunner commandLineRunner(AppDao app) {
    return runner -> {
      // createInstructor(app);
      // findInstructor(app);
      // deleteInstructor(app);
      // findInstructorDetail(app);
      // deleteInstructorDetail(app);
      // createInstructorWithCourses(app);
      // findInstructorWithCourses(app);
      // findInstructorWithCourses(app);
      // findInstructorWithCoursesJoinFetch(app);
      // updateInstructor(app);
      // deleteInstructorOnly(app);
      // createCourseAndReviews(app);
      // retrieveCourseAndReviews(app);
      // deleteCourseAndReviews(app);
      // createCourseAndStudents(app);
      // findCourseAndStudents(app);
      // findStudentAndCourses(app);
      // addMoreCoursesForStudent(app);
      // deleteCourseById(app);
      deleteStudent(app);
    };
  }

  private void createInstructor(AppDao app) {
    // create the instructor and details
    Instructor instructor = new Instructor(
        // "Vicky",
        // "Magtatanggol",
        // "vicky.mags@example.com");
        "Ana",
        "Alimurung",
        "ana.alimurung@example.com");

    InstructorDetail details = new InstructorDetail(
        "Math",
        "Solving problems");

    // associate the objects
    instructor.setInstructorDetails(details);

    // save the instructor
    // this will also save the details object
    // because of the CascadeType.ALL
    System.out.println("Information saved!");
    app.save(instructor);
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
    // get the instructor detail object
    int id = 1;
    InstructorDetail instructorDetails = app.findInstructorDetailById(id);

    // print the instructor detail
    System.out.println("Details: " + instructorDetails);

    // print the associated instructor
    System.out.println("Instructor: " + instructorDetails.getInstructor());
  }

  private void deleteInstructorDetail(AppDao app) {
    int id = 3;
    System.out.println("Delete id: " + id);

    app.deleteInstructorDetail(id);
    System.out.println("Deleted successfully!");
  }

  private void createInstructorWithCourses(AppDao app) {
    // create the instructor and details
    Instructor instructor = new Instructor("Peri",
        "Winkle",
        "peri.winkle@example.com");
    InstructorDetail details = new InstructorDetail("Science",
        "Sprinkling dust");

    // associate the objects
    instructor.setInstructorDetails(details);

    // create courses
    Course course1 = new Course("Physics");
    Course course2 = new Course("Biology");

    // add courses to instructor
    instructor.add(course1);
    instructor.add(course2);

    // save the instructor
    System.out.println("Course saved: " + instructor.getCourses());
    app.save(instructor);
  }

  private void findInstructorWithCourses(AppDao app) {
    int id = 4;
    System.out.println("Find instructor id: " + id);

    Instructor instructor = app.findInstructorById(id);

    System.out.println("Instructor: " + instructor);
    System.out.println("Courses: " + instructor.getCourses());
  }

  private void findCoursesForInstructor(AppDao app) {
    int id = 4;

    // find the instructor
    System.out.println("Find instructor id: " + id);
    Instructor instructor = app.findInstructorById(id);

    System.out.println("Instructor: " + instructor);

    // find courses for instructor
    System.out.println("Courses from id: " + id);
    List<Course> courses = app.findCoursesByInstructorId(id);

    // associate the objects
    instructor.setCourses(courses);

    // get the courses
    System.out.println(instructor.getCourses());
  }

  private void findInstructorWithCoursesJoinFetch(AppDao app) {
    int id = 4;
    System.out.println("Find instructor id: " + id);

    Instructor instructor = app.findInstructorByIdJoinFetch(id);

    System.out.println("Instructor: " + instructor);
    System.out.println("Courses: " + instructor.getCourses());
  }

  private void updateInstructor(AppDao app) {
    int id = 2;
    System.out.println("Find instructor id: " + id);

    Instructor instructor = app.findInstructorById(id);

    // change instructor's data
    instructor.setLastName("Cosma");

    // update the data
    System.out.println("Updated!");
    app.update(instructor);
  }

  private void deleteInstructorOnly(AppDao app) {
    int id = 2;
    System.out.println("Delete instructor id: " + id);

    app.deleteInstructorByIdWithoutRemovingCourses(id);
    System.out.println("Deleted successfully!");
  }

  private void createCourseAndReviews(AppDao app) {
    // create a course
    Course course = new Course("How to Speed Run Spring in 1 Day");

    // add some reviews
    course.addReview(new Review("Great speed 2x... loved it!!!"));
    course.addReview(new Review("I wanted the speed 0.75x :(("));
    course.addReview(new Review("Hoping to do a speed of 3x :D. Anyways, nice!"));

    // save the course
    System.out.println("Course saved!");
    app.save(course);
  }

  private void retrieveCourseAndReviews(AppDao app) {
    int id = 3;
    System.out.println("Get course and reviews from id: " + id);
    // get the course and reviews
    Course course = app.findCourseAndReviewsByCourseId(id);

    // print the course
    System.out.println(course);

    // print the reviews
    System.out.println(course.getReview());
  }

  private void deleteCourseAndReviews(AppDao app) {
    int id = 3;
    System.out.println("Delete course: " + id);

    app.deleteCourseById(id);
    System.out.println("Deleted successfully!");
  }

  private void createCourseAndStudents(AppDao app) {
    // create a course
    Course course = new Course("How to Be A FullStack Dev in Half a Day");

    // create the students
    Student student1 = new Student("Light", "Yagami", "light.yagami@example.com");
    Student student2 = new Student("Leslie", "Andrews", "leslie.andrews@example.com");

    // add student to the course
    course.addStudent(student1);
    course.addStudent(student2);

    // save the course and associated students
    app.save(course);
    System.out.println("Successfully enrolled!");
  }

  private void findCourseAndStudents(AppDao app) {
    int id = 21;
    Course course = app.findCourseAndStudentsByCourseId(id);

    System.out.println("Course: " + course);
    System.out.println("Students: " + course.getStudents());
  }

  private void findStudentAndCourses(AppDao app) {
    int id = 2;
    Student student = app.findStudentAndCoursesByStudentId(id);

    System.out.println("Student: " + student);
    System.out.println("Courses: " + student.getCourses());
  }

  private void addMoreCoursesForStudent(AppDao app) {
    int id = 2;
    Student student = app.findStudentAndCoursesByStudentId(id);

    Course course1 = new Course("How to Test without Running Application");
    Course course2 = new Course("How to Develop without Computer");
    Course course3 = new Course("How to Be a Legit Developer");

    student.addCourse(course1);
    student.addCourse(course2);
    student.addCourse(course3);

    System.out.println("Sucessfully enrolled to new courses!");

    app.update(student);
  }

  private void deleteCourseById(AppDao app) {
    int id = 42;
    System.out.println("Delete course: " + id);

    app.deleteCourseById(id);
    System.out.println("Deleted successfully!");
  }

  private void deleteStudent(AppDao app) {
    int id = 2;
    System.out.println("Delete student: " + id);

    app.deleteStudentById(id);
    System.out.println("Deleted successfully!");
  }

  /*
   * private void createStudent(StudentDao studentDao) {
   * // create the student object
   * System.out.println("Creating student information...");
   * Student student1 = new Student("Light",
   * "Yagami",
   * "light.yagami@example.com");
   * 
   * Student student2 = new Student("Leslie",
   * "Andrews",
   * "leslie.andrews@example.com");
   * 
   * Student student3 = new Student("Dexter",
   * "Lauron",
   * "dexter.lauron@example.com");
   * 
   * // save the student object
   * System.out.println("Saving student information...");
   * studentDao.save(student1);
   * studentDao.save(student2);
   * studentDao.save(student3);
   * 
   * // display id of the saved student
   * System.out.println("Saved! Generated id: " + student1.getId());
   * System.out.println("Saved! Generated id: " + student2.getId());
   * System.out.println("Saved! Generated id: " + student3.getId());
   * }
   * 
   * private void readStudent(StudentDao studentDao) {
   * // retrieve student based on the id
   * System.out.println("Retrieving student id 2...");
   * Student student = studentDao.findById(2);
   * 
   * // display student
   * System.out.println("Found the student: " + student);
   * }
   * 
   * private void queryStudents(StudentDao studentDao) {
   * // get list of students
   * List<Student> students = studentDao.findAll();
   * 
   * // display list of students
   * for (Student student : students) {
   * System.out.println(student);
   * }
   * }
   * 
   * private void queryStudentsLastName(StudentDao studentDao) {
   * // get list of students
   * List<Student> students = studentDao.findByLastName("Andrews");
   * 
   * // display list of students
   * for (Student student : students) {
   * System.out.println(student);
   * }
   * }
   * 
   * private void updateStudent(StudentDao studentDao) {
   * // retrieve student based on id
   * int id = 1;
   * System.out.println("Student ID: " + id);
   * Student student = studentDao.findById(id);
   * 
   * // change first name
   * System.out.println("Updating student name...");
   * student.setFirstName("Dark");
   * student.setLastName("Vader");
   * student.setEmail("dark.vader@example.com");
   * 
   * // update the student
   * studentDao.update(student);
   * 
   * // display the updated student information
   * System.out.println("Updated student information: " + student);
   * }
   * 
   * private void deleteStudent(StudentDao studentDao) {
   * int id = 2;
   * System.out.println("Delete ID: " + id);
   * 
   * // delete the student
   * studentDao.delete(id);
   * }
   * 
   * private void deleteAllStudents(StudentDao studentDao) {
   * System.out.println("Deleting all student information...");
   * int rowsDeleted = studentDao.deleteAll();
   * System.out.println("Number of deleted rows: " + rowsDeleted);
   * }
   */
}
