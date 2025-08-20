package cl.kibernumacademy.academy.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid; // Añadido  para validaciones en @RequestBody
import java.util.stream.Collectors; // Añadido  para .collect(Collectors.toList())
import org.springframework.http.HttpStatus; // Añadido para HttpStatus.CREATED

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.kibernumacademy.academy.controllers.dto.StudentRequest;
import cl.kibernumacademy.academy.controllers.dto.StudentResponse;
import cl.kibernumacademy.academy.entitys.Student;
import cl.kibernumacademy.academy.exceptions.ResourceNotFoundException;
import cl.kibernumacademy.academy.services.IStudentService;

@RestController
@RequestMapping("/api/students")
@Validated

public class StudentControllerRest {
    private final IStudentService service;

    public StudentControllerRest(IStudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentResponse> getAll() {
        return service.listAllStudents().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable Long id) {
        Optional<Student> student = service.getStudentById(id);
        return student.map(value -> ResponseEntity.ok(toResponse(value)))
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró estudiante con id: " + id));
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentRequest request) {
        Student student = new Student(request.getName(), request.getLastname(), request.getEmail());
        Student saved = service.saveStudent(student);
        /*
         * Debe incluir header Location con la URL del nuevo recurso.
         */
        URI location = URI.create(String.format("/api/students/%s", saved.getId()));
        // return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
        return ResponseEntity.created(location).body(toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
        Optional<Student> optionalStudent = service.getStudentById(id);
        if (!optionalStudent.isPresent()) {
            throw new ResourceNotFoundException("No se encontró estudiante con id: " + id);
        }
        Student student = optionalStudent.get();
        student.setName(request.getName());
        student.setLastname(request.getLastname());
        student.setEmail(request.getEmail());
        Student updated = service.updatedStudent(student);
        return ResponseEntity.ok(toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Student> optionalStudent = service.getStudentById(id);
        if (!optionalStudent.isPresent()) {
            throw new ResourceNotFoundException("No se encontró estudiante con id: " + id);
        }
        service.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    private StudentResponse toResponse(Student s) {
        return new StudentResponse(s.getId(), s.getName(), s.getLastname(), s.getEmail());
    }
}
