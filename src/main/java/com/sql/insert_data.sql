-- Insertar usuarios
INSERT INTO Usuarios (nombre, email, tipo_suscripcion, fecha_creacion, contrasena_hash, estado)
VALUES
    ('Juan Pérez', 'juan.perez@email.com', 'Premium', '2024-01-15', 'hash_contrasena_1', 'activo'),
    ('María López', 'maria.lopez@email.com', 'Gratis', '2024-02-20', 'hash_contrasena_2', 'activo'),
    ('Carlos Gómez', 'carlos.gomez@email.com', 'Premium', '2024-03-10', 'hash_contrasena_3', 'activo');

-- Insertar perfiles de usuarios
INSERT INTO Perfiles_Usuarios (usuario_id, foto_perfil, biografia)
VALUES
    (1, 'foto_juan.jpg', 'Amante de la música pop y rock.'),
    (2, 'foto_maria.jpg', 'Fanática de la música clásica y jazz.'),
    (3, 'foto_carlos.jpg', 'Disfruta de todo tipo de música electrónica.');

-- Insertar listas de reproducción
INSERT INTO Listas_Reproduccion (nombre, usuario_id, fecha_creacion, privacidad)
VALUES
    ('Pop Hits', 1, '2024-01-15', 'publica'),
    ('Jazz para relajarse', 2, '2024-02-20', 'privada'),
    ('Electro Dance', 3, '2024-03-10', 'publica');

-- Insertar canciones
INSERT INTO Canciones (titulo, duracion, album_id, url_archivo, conteo_reproducciones)
VALUES
    ('Shape of You', 233, 1, 'url_archivo_1.mp3', 100),
    ('Bohemian Rhapsody', 354, 2, 'url_archivo_2.mp3', 250),
    ('Blinding Lights', 200, 3, 'url_archivo_3.mp3', 150);

-- Insertar artistas
INSERT INTO Artistas (nombre, genero, pais_origen)
VALUES
    ('Ed Sheeran', 'Pop', 'Reino Unido'),
    ('Queen', 'Rock', 'Reino Unido'),
    ('The Weeknd', 'Pop', 'Canadá');

-- Insertar álbumes
INSERT INTO Albumes (nombre, fecha_lanzamiento, genero, url_portada)
VALUES
    ('Divide', '2017-03-03', 'Pop', 'url_portada_1.jpg'),
    ('A Night at the Opera', '1975-11-21', 'Rock', 'url_portada_2.jpg'),
    ('After Hours', '2020-03-20', 'Pop', 'url_portada_3.jpg');

-- Insertar relaciones entre álbumes y artistas
INSERT INTO Albumes_Artistas (album_id, artista_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);

-- Insertar relaciones entre canciones y artistas
INSERT INTO Canciones_Artistas (cancion_id, artista_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);

-- Insertar relaciones entre canciones y listas de reproducción
INSERT INTO Canciones_Listas (lista_id, cancion_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);

-- Insertar historial de reproducciones
INSERT INTO Historial_Reproduccion (usuario_id, cancion_id, fecha_reproduccion)
VALUES
    (1, 1, '2024-01-15 10:00:00'),
    (2, 2, '2024-02-20 15:30:00'),
    (3, 3, '2024-03-10 08:45:00');
