package cl.kibernumacademy.academy.entitys;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentTest {
  
  private Student student;

  @BeforeEach
  public void setUp() {
    student = new Student("James", "Gosling", "james.gosling@java.com");
  }

  @Test
  public void shoudSetName() {
    student.setName("Richard");
    assertEquals("Richard", student.getName());
  }

  @Test
  public void shouldSetLastName() {
    student.setLastname("Stallman");
    assertEquals("Stallman", student.getLastname());
  }

  @Test
  public void shouldSetEmail() {
    student.setEmail("richard.stallman@gnu.com");
    assertEquals("richard.stallman@gnu.com", student.getEmail());
  }


}
