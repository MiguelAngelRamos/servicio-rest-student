package cl.kibernumacademy.academy.controllers;

import cl.kibernumacademy.academy.controllers.dto.StudentRequest;
import cl.kibernumacademy.academy.controllers.dto.StudentResponse;
import cl.kibernumacademy.academy.entitys.Student;
import cl.kibernumacademy.academy.services.IStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerRestTest {
    @Mock
    private IStudentService service;

    @InjectMocks
    private StudentControllerRest controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_returnsListOfStudentResponses() {
        Student s1 = new Student("Juan", "Perez", "juan@mail.com");
        s1.setId(1L);
        Student s2 = new Student("Ana", "Lopez", "ana@mail.com");
        s2.setId(2L);
        when(service.listAllStudents()).thenReturn(Arrays.asList(s1, s2));
        List<StudentResponse> result = controller.getAll();
        assertEquals(2, result.size());
        assertEquals("Juan", result.get(0).getName());
    }

    @Test
    void getById_returnsStudentResponse_whenFound() {
        Student s = new Student("Juan", "Perez", "juan@mail.com");
        s.setId(1L);
        when(service.getStudentById(1L)).thenReturn(Optional.of(s));
        ResponseEntity<StudentResponse> response = controller.getById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Juan", response.getBody().getName());
    }

    @Test
    void create_returnsCreatedStudentResponse() {
        StudentRequest req = new StudentRequest();
        req.setName("Juan");
        req.setLastname("Perez");
        req.setEmail("juan@mail.com");
        Student s = new Student("Juan", "Perez", "juan@mail.com");
        s.setId(1L);
        when(service.saveStudent(any(Student.class))).thenReturn(s);
        ResponseEntity<StudentResponse> response = controller.create(req);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Juan", response.getBody().getName());
    }

    @Test
    void update_returnsUpdatedStudentResponse() {
        StudentRequest req = new StudentRequest();
        req.setName("Juan");
        req.setLastname("Perez");
        req.setEmail("nuevo@mail.com");
        Student s = new Student("Juan", "Perez", "juan@mail.com");
        s.setId(1L);
        when(service.getStudentById(1L)).thenReturn(Optional.of(s));
        when(service.updatedStudent(any(Student.class))).thenReturn(s);
        ResponseEntity<StudentResponse> response = controller.update(1L, req);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void delete_removesStudent() {
        Student s = new Student("Juan", "Perez", "juan@mail.com");
        s.setId(1L);
        when(service.getStudentById(1L)).thenReturn(Optional.of(s));
        doNothing().when(service).deleteStudentById(1L);
        ResponseEntity<Void> response = controller.delete(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
