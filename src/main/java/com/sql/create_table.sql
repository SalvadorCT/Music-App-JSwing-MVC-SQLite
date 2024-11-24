-- Tabla Usuarios
CREATE TABLE Usuarios (
                          usuario_id INTEGER PRIMARY KEY AUTOINCREMENT,
                          nombre TEXT NOT NULL,
                          email TEXT NOT NULL UNIQUE,
                          tipo_suscripcion TEXT NOT NULL,
                          fecha_creacion DATE NOT NULL,
                          contrasena_hash TEXT NOT NULL,
                          estado TEXT DEFAULT 'activo'
);

-- Tabla Perfiles_Usuarios
CREATE TABLE Perfiles_Usuarios (
                                   perfil_id INTEGER PRIMARY KEY AUTOINCREMENT,
                                   usuario_id INTEGER NOT NULL,
                                   foto_perfil TEXT,
                                   biografia TEXT,
                                   FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id)
);

-- Tabla Listas_Reproduccion
CREATE TABLE Listas_Reproduccion (
                                     lista_id INTEGER PRIMARY KEY AUTOINCREMENT,
                                     nombre TEXT NOT NULL,
                                     usuario_id INTEGER NOT NULL,
                                     fecha_creacion DATE NOT NULL,
                                     privacidad TEXT DEFAULT 'publica',
                                     FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id)
);

-- Tabla Canciones
CREATE TABLE Canciones (
                           cancion_id INTEGER PRIMARY KEY AUTOINCREMENT,
                           titulo TEXT NOT NULL,
                           duracion INTEGER NOT NULL,
                           album_id INTEGER NOT NULL,
                           url_archivo TEXT NOT NULL,
                           conteo_reproducciones INTEGER DEFAULT 0
);

-- Tabla Artistas
CREATE TABLE Artistas (
                          artista_id INTEGER PRIMARY KEY AUTOINCREMENT,
                          nombre TEXT NOT NULL,
                          genero TEXT,
                          pais_origen TEXT
);

-- Tabla Albumes
CREATE TABLE Albumes (
                         album_id INTEGER PRIMARY KEY AUTOINCREMENT,
                         nombre TEXT NOT NULL,
                         fecha_lanzamiento DATE NOT NULL,
                         genero TEXT NOT NULL,
                         url_portada TEXT
);

-- Tabla Albumes_Artistas (relación muchos a muchos)
CREATE TABLE Albumes_Artistas (
                                  album_artista_id INTEGER PRIMARY KEY AUTOINCREMENT,
                                  album_id INTEGER NOT NULL,
                                  artista_id INTEGER NOT NULL,
                                  FOREIGN KEY (album_id) REFERENCES Albumes(album_id),
                                  FOREIGN KEY (artista_id) REFERENCES Artistas(artista_id)
);

-- Tabla Canciones_Artistas (relación muchos a muchos)
CREATE TABLE Canciones_Artistas (
                                    cancion_artista_id INTEGER PRIMARY KEY AUTOINCREMENT,
                                    cancion_id INTEGER NOT NULL,
                                    artista_id INTEGER NOT NULL,
                                    FOREIGN KEY (cancion_id) REFERENCES Canciones(cancion_id),
                                    FOREIGN KEY (artista_id) REFERENCES Artistas(artista_id)
);

-- Tabla Canciones_Listas (relación muchos a muchos)
CREATE TABLE Canciones_Listas (
                                  cancion_lista_id INTEGER PRIMARY KEY AUTOINCREMENT,
                                  lista_id INTEGER NOT NULL,
                                  cancion_id INTEGER NOT NULL,
                                  FOREIGN KEY (lista_id) REFERENCES Listas_Reproduccion(lista_id),
                                  FOREIGN KEY (cancion_id) REFERENCES Canciones(cancion_id)
);

-- Tabla Historial_Reproduccion
CREATE TABLE Historial_Reproduccion (
                                        historial_id INTEGER PRIMARY KEY AUTOINCREMENT,
                                        usuario_id INTEGER NOT NULL,
                                        cancion_id INTEGER NOT NULL,
                                        fecha_reproduccion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id),
                                        FOREIGN KEY (cancion_id) REFERENCES Canciones(cancion_id)
);
