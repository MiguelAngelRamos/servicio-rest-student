# Ejemplos de Peticiones para API de Estudiantes

## 1. Crear un estudiante (POST)
- Método: POST
- URL: http://localhost:9090/api/students
- Body (raw, JSON):
```json
{
  "name": "Juan",
  "lastname": "Pérez",
  "email": "juan.perez@email.com"
}
```

## 2. Obtener todos los estudiantes (GET)
- Método: GET
- URL: http://localhost:9090/api/students

## 3. Obtener un estudiante por ID (GET)
- Método: GET
- URL: http://localhost:9090/api/students/1

## 4. Actualizar un estudiante (PUT)
- Método: PUT
- URL: http://localhost:9090/api/students/1
- Body (raw, JSON):
```json
{
  "name": "Pedro",
  "lastname": "Gómez",
  "email": "pedro.gomez@email.com"
}
```

## 5. Eliminar un estudiante (DELETE)
- Método: DELETE
- URL: http://localhost:9090/api/students/1
