package com.spring.hibernate_demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.Student;

@SpringBootApplication
public class HibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	@Bean 
	CommandLineRunner commandLineRunner(StudentDao studentDao) {
		return runner -> {
			createStudent(studentDao);
			// readStudent(studentDao);
			// queryStudents(studentDao);
			// queryStudentsLastName(studentDao);
			// updateStudent(studentDao);
			// deleteStudent(studentDao);
			// deleteAllStudents(studentDao);
		};
	}

	private void createStudent(StudentDao studentDao) {
		// create the student object
		System.out.println("Creating student information...");
		Student student1 = new Student("Light", 
										"Yagami", 
										"light.yagami@example.com");
		
		Student student2 = new Student("Leslie", 
										"Andrews", 
										"leslie.andrews@example.com");
		
		Student student3 = new Student("Dexter", 
										"Lauron", 
										"dexter.lauron@example.com");
		
		// save the student object
		System.out.println("Saving student information...");
		studentDao.save(student1);
		studentDao.save(student2);
		studentDao.save(student3);

		// display id of the saved student
		System.out.println("Saved! Generated id: " + student1.getId());
		System.out.println("Saved! Generated id: " + student2.getId());
		System.out.println("Saved! Generated id: " + student3.getId());
	}

	private void readStudent(StudentDao studentDao) {
		// retrieve student based on the id
		System.out.println("Retrieving student id 2...");
		Student student = studentDao.findById(2);

		// display student
		System.out.println("Found the student: " + student);
	}

	private void queryStudents(StudentDao studentDao) {
		// get list of students
		List<Student> students = studentDao.findAll();
		
		// display list of students
		for (Student student : students) {
			System.out.println(student);	
		}
	}

	private void queryStudentsLastName(StudentDao studentDao) {
		// get list of students
		List<Student> students = studentDao.findByLastName("Andrews");
		
		// display list of students
		for (Student student : students) {
			System.out.println(student);	
		}
	}

	private void updateStudent(StudentDao studentDao) {
		// retrieve student based on id
		int id = 1;
		System.out.println("Student ID: " + id);
		Student student = studentDao.findById(id);

		// change first name
		System.out.println("Updating student name...");
		student.setFirstName("Dark");
		student.setLastName("Vader");
		student.setEmail("dark.vader@example.com");

		// update the student
		studentDao.update(student);

		// display the updated student information
		System.out.println("Updated student information: " + student);
	}

	private void deleteStudent(StudentDao studentDao) {
		int id = 2;
		System.out.println("Delete ID: " + id);

		// delete the student
		studentDao.delete(id);
	}

	private void deleteAllStudents(StudentDao studentDao) {
		System.out.println("Deleting all student information...");
		int rowsDeleted = studentDao.deleteAll();
		System.out.println("Number of deleted rows: " + rowsDeleted);
	}

}
