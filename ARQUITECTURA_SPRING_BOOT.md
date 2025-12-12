# Arquitectura Spring Boot Corregida - SpeedSpares

## Estructura de Carpetas

```
com/speedspares/backend/
├── controller/
│   ├── UsuarioController.java      ✅ COMPLETADO
│   └── ProductoController.java     ✅ COMPLETADO
├── model/
│   ├── Usuario.java                ✅ COMPLETADO
│   └── Producto.java               ✅ COMPLETADO
├── service/
│   ├── UsuarioService.java         ✅ COMPLETADO
│   └── ProductoService.java        ✅ COMPLETADO
├── repository/
│   ├── UsuarioRepository.java      ✅ COMPLETADO
│   └── ProductoRepository.java     ✅ COMPLETADO
├── dto/
│   ├── UsuarioDTO.java             ✅ NUEVO
│   ├── UsuarioCrearDTO.java        ✅ NUEVO
│   ├── ProductoDTO.java            ✅ NUEVO
│   └── ProductoCrearDTO.java       ✅ NUEVO
└── exception/
    ├── GlobalExceptionHandler.java ✅ NUEVO
    ├── ErrorResponse.java          ✅ NUEVO
    ├── ResourceNotFoundException.java ✅ NUEVO
    └── BadRequestException.java    ✅ NUEVO
```

## Cambios Realizados

### 1. **Entidades (Model)**
- ✅ Agregado Lombok (@Data, @NoArgsConstructor, @AllArgsConstructor)
- ✅ Cambio de `Timestamp` a `LocalDateTime`
- ✅ Relación ManyToOne entre Producto y Usuario

### 2. **DTOs (Data Transfer Objects)**
- ✅ UsuarioDTO - Para respuestas del servidor
- ✅ UsuarioCrearDTO - Para crear/actualizar usuarios
- ✅ ProductoDTO - Para respuestas de productos
- ✅ ProductoCrearDTO - Para crear/actualizar productos

### 3. **Services (Lógica de Negocio)**
- ✅ UsuarioService con operaciones CRUD
- ✅ ProductoService con operaciones CRUD y búsqueda por usuario

### 4. **Controllers (Endpoints REST)**
- ✅ UsuarioController con todos los endpoints
- ✅ ProductoController con todos los endpoints
- ✅ @CrossOrigin configurado para permitir CORS

### 5. **Exception Handling**
- ✅ GlobalExceptionHandler para manejo centralizado de errores
- ✅ ErrorResponse con formato consistente
- ✅ Excepciones personalizadas

### 6. **Configuración**
- ✅ application.properties actualizado con Hibernate dialect
- ✅ Logging configurado
- ✅ Jackson indentation habilitada

## Endpoints Disponibles

### USUARIOS

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/usuarios` | Obtener todos los usuarios |
| GET | `/api/usuarios/{id}` | Obtener usuario por ID |
| POST | `/api/usuarios` | Crear nuevo usuario |
| PUT | `/api/usuarios/{id}` | Actualizar usuario |
| DELETE | `/api/usuarios/{id}` | Eliminar usuario |
| PUT | `/api/usuarios/{id}/verificar` | Verificar como proveedor |

### PRODUCTOS

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/productos` | Obtener todos los productos |
| GET | `/api/productos/{id}` | Obtener producto por ID |
| POST | `/api/productos` | Crear nuevo producto |
| PUT | `/api/productos/{id}` | Actualizar producto |
| DELETE | `/api/productos/{id}` | Eliminar producto |
| GET | `/api/productos/usuario/{idUsuario}` | Obtener productos de un usuario |

## Ejemplos de Uso

### Crear Usuario
```json
POST /api/usuarios
{
  "nombre": "Juan",
  "apellidos": "Pérez",
  "email": "juan@example.com",
  "password": "123456",
  "telefono": "1234567890",
  "latitud": 10.5,
  "longitud": -75.5
}
```

### Crear Producto
```json
POST /api/productos
{
  "idCategoria": 1,
  "sku": "REPUESTO001",
  "nombre": "Amortiguador",
  "descripcion": "Amortiguador delantero",
  "precio": 150000,
  "stock": 50,
  "procedencia": "Original",
  "idUsuario": 1
}
```

## Pasos Siguientes

1. **Crear base de datos en MySQL:**
```sql
CREATE DATABASE speed_spares_db;
```

2. **Ejecutar la aplicación:**
```bash
./mvnw spring-boot:run
```

3. **La aplicación creará las tablas automáticamente** (ddl-auto=update)

4. **Probar endpoints en Postman o Insomnia**

5. **Agregar validaciones** (si es necesario):
   - @NotNull, @NotBlank en DTOs
   - Custom validators

6. **Agregar seguridad** (si es necesario):
   - Spring Security
   - JWT tokens

## Verificación de Compilación

✅ BUILD SUCCESS - No hay errores de compilación
