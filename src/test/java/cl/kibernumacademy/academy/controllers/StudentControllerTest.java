package cl.kibernumacademy.academy.controllers;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test; // Anotación para métodos de prueba
import org.springframework.beans.factory.annotation.Autowired; // Para inyectar dependencias en el test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; // Configuración de MockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; // Slice test del layer web MVC
import org.springframework.boot.test.mock.mockito.MockBean; // Crea mocks administrados por Spring
import org.springframework.context.annotation.Import; // Para importar el manejador de excepciones REST
import org.springframework.http.MediaType; // Representa tipos de contenido (JSON)
import org.springframework.test.web.servlet.MockMvc; // Cliente para simular llamadas HTTP

import cl.kibernumacademy.academy.entitys.Student;
import cl.kibernumacademy.academy.services.IStudentService;

import java.util.Arrays; // Utilidad para listas
import java.util.Collections; // Utilidad para listas vacías
import java.util.Optional; // Para valores opcionales de servicio

import static org.mockito.ArgumentMatchers.any; // Matchers de Mockito
import static org.mockito.ArgumentMatchers.anyLong; // Matcher para Long
import static org.mockito.Mockito.when; // Stub de comportamientos
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // Construcción de requests (GET/POST/PUT/DELETE)
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*; // Matchers de respuesta

@AutoConfigureMockMvc(addFilters = false) // Desactiva los filtros de seguridad para pruebas
@WebMvcTest(controllers = StudentControllerRest.class)
//TODO: Crear un manejador de expeciones globales
public class StudentControllerTest {
  
  @Autowired
  private MockMvc mockMvc; // Crea un mock para simulacion de peticiones http

  @MockBean
  private IStudentService studentService;

  @Test
  void listShouldRenderViewWithStudents() throws Exception {
    // Simular que el servicio retorna una lista de estudiantes
    // Cuando se llame al servicio y al metodo listAllStudents devolvemos este listado simulado de estudiantes
    when(studentService.listAllStudents()).thenReturn(Arrays.asList(
      makeStudent(1L, "Sofia", "Araya", "sofia.araya@correo.com"),
      makeStudent(2L, "Richard", "Stallman", "richard.stallman@correo.com")
    ));

    mockMvc.perform(get("/api/students").accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].id").value(1L))
          .andExpect(jsonPath("$[0].name").value("Sofia"))
          .andExpect(jsonPath("$[0].email").value("sofia.araya@correo.com"));
  }

  private Student makeStudent(Long id, String name, String lastname, String email) {
    Student student = new Student(name, lastname, email);
    student.setId(id);
    return student;
  }

}
