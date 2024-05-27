Create database juatsappo;

use juatsappo;

-- Tabla de Imagen
CREATE TABLE imagen (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    datos_imagen LONGBLOB
);



-- Tabla de Domicilio
CREATE TABLE domicilio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    calle VARCHAR(150) NOT NULL,
    numero VARCHAR(150) NOT NULL,
    colonia VARCHAR(150) NOT NULL,
    codigoPostal VARCHAR(6) NOT NULL
);



-- Tabla de Usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    telefono VARCHAR(255) NOT NULL UNIQUE,
    generoUsuario VARCHAR(255) NOT NULL,
    perfil BIGINT,
    fechaNacimiento DATE NOT NULL,
    contrasenya VARCHAR(255) NOT NULL,
    id_domicilio INT,
    FOREIGN KEY (perfil) REFERENCES imagen(id),
    FOREIGN KEY (id_domicilio) REFERENCES domicilio(id)
);

-- Tabla de Chat
CREATE TABLE chat (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
-- Tabla de Mensajes
CREATE TABLE mensajes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    imagen_id BIGINT,
    texto TEXT NOT NULL,
    fechaHoraRegistro TIMESTAMP NOT NULL,
    id_usuario INT NOT NULL,
    id_chat BIGINT NOT NULL,
    FOREIGN KEY (imagen_id) REFERENCES imagen(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_chat) REFERENCES chat(id)
);

-- Tabla intermedia para la relación muchos a muchos entre Chat y Usuario
CREATE TABLE usuario_chat (
    id_usuario INT NOT NULL,
    id_chat BIGINT NOT NULL,
    PRIMARY KEY (id_usuario, id_chat),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_chat) REFERENCES chat(id)
);

select * from usuario_chat;

select * from usuarios; 


