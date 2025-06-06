# ValRide Backend (Spring Boot)

## Requisitos
- Java 17+
- PostgreSQL
- Maven

## Instalación y ejecución
1. Crea una base de datos llamada `valride` en PostgreSQL.
2. Modifica `src/main/resources/application.properties` si tu usuario/contraseña es diferente.
3. Importa el proyecto en IntelliJ IDEA como proyecto Maven.
4. Ejecuta la aplicación (`ValrideBackendApplication.java`).
5. Se crearán usuarios y motos de ejemplo automáticamente.

## Usuarios de ejemplo
- Superadmin: usuario `superadmin`, contraseña `admin123`
- Usuario normal: usuario `usuario`, contraseña `user123`

## Endpoints principales
- `POST /auth/login` — Login, devuelve JWT
- `POST /auth/register` — Registro de usuario
- `GET /motos` — Listar motos
- `POST /reservas` — Crear reserva (usuario)
- `GET /reservas/mias` — Ver mis reservas (usuario)
- `GET /reservas` — Ver todas las reservas (superadmin)
- `PUT /reservas/{id}/estado` — Cambiar estado de reserva (superadmin)

## Seguridad
- Usa JWT en el header `Authorization: Bearer <token>` en las peticiones protegidas.
