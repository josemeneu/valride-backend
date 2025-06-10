# Valride Backend

Este proyecto es el backend de la aplicación de reservas de motos, desarrollado en Java Spring Boot.

## Requisitos

- Java JDK 17 o superior
- Maven
- MySQL (servidor local)
- (Opcional) Docker

## Configuración de la base de datos

1. **Crea la base de datos en MySQL**
   ```sql
   CREATE DATABASE valride;
   mysql -u TU_USUARIO -p valride < init_db.sql
2. **Configura las credenciales en src/main/resources/application.properties:**
    spring.datasource.url=jdbc:mysql://localhost:3306/valride?useSSL=false&serverTimezone=UTC
    spring.datasource.username=TU_USUARIO
    spring.datasource.password=TU_PASSWORD
    
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

server.port=8080 

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
