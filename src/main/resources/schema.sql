CREATE TABLE IF NOT EXISTS usuario (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS moto (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion TEXT,
    imagen TEXT,
    alquilada BOOLEAN DEFAULT false
);

CREATE TABLE IF NOT EXISTS reserva (
    id SERIAL PRIMARY KEY,
    moto_id INTEGER REFERENCES moto(id),
    usuario_id INTEGER REFERENCES usuario(id),
    fecha_inicio DATE,
    fecha_fin DATE,
    activa BOOLEAN
);
