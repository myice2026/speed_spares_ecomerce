# SpeedSpares Backend - Copilot Instructions

## Architecture Overview

**SpeedSpares** is a Spring Boot 4.0 e-commerce API for automotive spare parts with location-based discovery (hiperlocalización). The system connects Flutter mobile clients to a MySQL database via REST endpoints.

### Three-Layer Architecture

1. **Controller** (`RepuestoController`) → Exposes REST API endpoints
2. **Service** (`RepuestoService`) → Business logic for filtering and distance calculations
3. **Repository** (Spring Data JPA) → Database access abstraction

**Key flows:**
- Flutter sends user GPS coordinates → Controller receives them as URL parameters
- Service filters parts by: verified suppliers + stock availability → calculates Haversine distance
- Results return sorted by proximity

## Core Domain Logic

### Location-Based Search (`buscarRepuestosCercanos`)

The system implements the **Haversine formula** (distance between two geographic points on Earth):

```java
// In RepuestoService.java
private double calcularDistancia(double lat1, double lon1, double lat2, double lon2)
// Uses EARTH_RADIUS = 6371 km
```

**Filtering rules applied (in order):**
1. `proveedor.isEstadoVerificacion()` - Only verified suppliers
2. `disponibilidad > 0` - Only in-stock parts
3. Sort by calculated distance (closest first)

### Data Models

**Repuesto (Spare Part):**
- `id` (Long, PK)
- `nombrePieza` - Part name
- `precio` (Double)
- `disponibilidad` (Integer) - Stock count
- `procedencia` - Traceability origin (e.g., "Original OEM", "Certified Remanufactured")
- `proveedor` (ManyToOne FK) - Supplier reference
- `distanciaKm` - Calculated runtime field (not persisted)

**Proveedor (Supplier):**
- `id` (Long, PK)
- `nombreEmpresa` - Business name
- `estadoVerificacion` (boolean) - Verification flag (CRITICAL for filtering)
- `latitud`, `longitud` - GPS coordinates

## Development Conventions

### Project Setup

- **Framework:** Spring Boot 4.0 (parent POM)
- **Java:** Version 17
- **Build:** Maven (mvnw provided)
- **Database:** MySQL (via XAMPP default: root/no-password)
- **ORM:** Hibernate/JPA with auto-schema updates (`ddl-auto=update`)

### Running & Building

```bash
# Start development server (port 8080)
./mvnw spring-boot:run

# Build JAR
./mvnw clean package

# Run tests
./mvnw test
```

### Dependency Notes

- **Lombok** - Used for boilerplate reduction (getters/setters annotation)
- **MySQL Connector** - Runtime dependency
- **Spring Data JPA** - Automatic repository implementations
- **spring-boot-starter-webmvc** - REST controller support

### Code Patterns

1. **Dependency Injection:** Use `@Autowired` for service/repository injection in controllers/services
2. **REST Endpoints:** Prefix all endpoints with `/api/repuestos` (defined in `@RequestMapping`)
3. **Repository Queries:** Extend `JpaRepository<Entity, ID>` - `findAll()` is the primary method currently used
4. **Streams:** Service layer uses Java streams for filtering/mapping (e.g., `.filter()`, `.peek()`, `.sorted()`)

### SQL & JPA Notes

- Entity classes require no-arg constructors (`public Repuesto() {}`)
- Foreign keys mapped via `@JoinColumn` (e.g., `id_proveedor` in repuestos table)
- `@Column` annotation for explicit SQL column naming (e.g., `estado_verificacion`)
- SQL logging enabled: Set `spring.jpa.show-sql=true` in `application.properties`

## Common Tasks

### Adding a New Endpoint

1. Add method to `RepuestoController` with `@GetMapping` or `@PostMapping`
2. Call corresponding service method
3. Service handles business logic (filtering, calculations)
4. Return results (Spring auto-serializes to JSON)

### Extending Queries

Current repositories use `findAll()`. To optimize:
- Add custom `@Query` methods to `RepuestoRepository`
- Example: `List<Repuesto> findByProveedorId(Long id)`

### Database Schema Changes

Edit model classes (Repuesto, Proveedor) → Hibernate auto-creates/updates tables (ddl-auto=update).

## Known Limitations & TODOs

- All repuestos fetched in memory before filtering (scalability concern at large datasets)
- No pagination implemented
- `distanciaKm` is a transient calculated field (not stored)
- Test coverage minimal (only context loader test exists)
- No authentication/authorization currently
