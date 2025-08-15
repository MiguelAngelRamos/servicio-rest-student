package cl.kibernumacademy.academy.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

// import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.kibernumacademy.academy.entitys.Student;
import cl.kibernumacademy.academy.repositories.IStudentRepository;

public class StudentServiceImplTest {
  private StudentServiceImpl studentService;

  @Mock
  private IStudentRepository studentRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    studentService = new StudentServiceImpl(studentRepository);
  }

  @Test
  public void shouldSaveStudent() {
    Student student = new Student("James", "Gosling", "james.gosling@java.com" );
    when(studentRepository.save(any(Student.class))).thenReturn(student); 
    // Llamamos al método que queremos probar
    studentService.saveStudent(student);
    // Verifica si el save "fue llamado" con el estudiante correcto
    verify(studentRepository).save(student);
  }

  @Test
  public void shouldFindStudentById() {

    Student student = new Student("James", "Gosling", "james.gosling@java.com" );
    student.setId(1L);
    when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
    studentService.getStudentById(1L);
    // Verifica si el findById "fue llamado"
    verify(studentRepository).findById(1L);
  }

  @Test
  public void shouldListAllStudents() {

    Student student = new Student("James", "Gosling", "james.gosling@java.com" );
    Student student2 = new Student("Richard", "Stallman", "richard.stallman@gnu.com");

    when(studentRepository.findAll()).thenReturn(Arrays.asList(student, student2));

    studentService.listAllStudents();
    // Verifica si el findAll "fue llamado"
    verify(studentRepository).findAll();

  }

}
