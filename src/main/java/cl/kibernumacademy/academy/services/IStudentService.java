package cl.kibernumacademy.academy.services;

import java.util.List;
import java.util.Optional;

import cl.kibernumacademy.academy.entitys.Student;

public interface IStudentService {
  public List<Student> listAllStudents();
  public Student saveStudent(Student student);
  public Optional<Student> getStudentById(Long id);
  public Student updatedStudent(Student student);
  public void deleteStudentById(Long id);
}
