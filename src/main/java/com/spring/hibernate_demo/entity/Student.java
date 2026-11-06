package com.spring.hibernate_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a student record in the database.
 *
 * <p>
 * This entity is mapped to the {@code student} table and stores the
 * student's basic information, including first name, last name, and email.
 * </p>
 */
@Entity
@Table(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  public Student() {
  }

  /**
   * Creates a student with the specified name and email address.
   *
   * @param firstName the student's first name
   * @param lastName  the student's last name
   * @param email     the student's email address
   */
  public Student(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Student [id=" + id +
        ", firstName=" + firstName +
        ", lastName=" + lastName +
        ", email=" + email + "]";
  }

}
