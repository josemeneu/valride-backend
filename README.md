# ValRide Backend (Spring Boot)

## Requisitos
- Java 17+
- PostgreSQL
- Maven

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
