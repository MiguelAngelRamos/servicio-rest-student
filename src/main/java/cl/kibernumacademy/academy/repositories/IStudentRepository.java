package cl.kibernumacademy.academy.repositories;


import org.springframework.data.repository.CrudRepository;

import cl.kibernumacademy.academy.entitys.Student;

public interface IStudentRepository extends CrudRepository<Student,Long> {
  // No es necesario agregar métodos adicionales aquí, ya que CrudRepository ya proporciona
  // los métodos básicos para CRUD (Create, Read, Update, Delete).
}


// Acronimo Crud
// Create, Read, Update, Delete
// CrudRepository es una interfaz de Spring Data que proporciona métodos CRUD básicos para la entidad Student.
