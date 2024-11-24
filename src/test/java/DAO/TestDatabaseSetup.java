// src/test/java/DAO/TestDatabaseSetup.java
package DAO;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDatabaseSetup {
    private static Connection connection;
    private static final String DATABASE_URL = "jdbc:sqlite:test_database.db";

    public static Connection getTestConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DATABASE_URL);
            createTables(connection); // Inicializa las tablas si no existen
        }
        return connection;
    }

    private static void createTables(Connection connection) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            // Tabla Usuarios
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Usuarios (
                    usuario_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    email TEXT NOT NULL UNIQUE,
                    tipo_suscripcion TEXT NOT NULL,
                    fecha_creacion DATE NOT NULL,
                    contrasena_hash TEXT NOT NULL,
                    estado TEXT DEFAULT 'activo'
                );
            """);

            // Tabla Perfiles_Usuarios
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Perfiles_Usuarios (
                    perfil_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    usuario_id INTEGER NOT NULL,
                    foto_perfil TEXT,
                    biografia TEXT,
                    FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id)
                );
            """);

            // Tabla Listas_Reproduccion
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Listas_Reproduccion (
                    lista_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    usuario_id INTEGER NOT NULL,
                    fecha_creacion DATE NOT NULL,
                    privacidad TEXT DEFAULT 'publica',
                    FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id)
                );
            """);

            // Tabla Canciones
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Canciones (
                    cancion_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    titulo TEXT NOT NULL,
                    duracion INTEGER NOT NULL,
                    album_id INTEGER NOT NULL,
                    url_archivo TEXT NOT NULL,
                    conteo_reproducciones INTEGER DEFAULT 0
                );
            """);

            // Tabla Artistas
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Artistas (
                    artista_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    genero TEXT,
                    pais_origen TEXT
                );
            """);

            // Tabla Albumes
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Albumes (
                    album_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    fecha_lanzamiento DATE NOT NULL,
                    genero TEXT NOT NULL,
                    url_portada TEXT
                );
            """);

            // Tabla Albumes_Artistas (relación muchos a muchos entre Albumes y Artistas)
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Albumes_Artistas (
                    album_artista_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    album_id INTEGER NOT NULL,
                    artista_id INTEGER NOT NULL,
                    FOREIGN KEY (album_id) REFERENCES Albumes(album_id),
                    FOREIGN KEY (artista_id) REFERENCES Artistas(artista_id)
                );
            """);

            // Tabla Canciones_Artistas (relación muchos a muchos entre Canciones y Artistas)
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Canciones_Artistas (
                    cancion_artista_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    cancion_id INTEGER NOT NULL,
                    artista_id INTEGER NOT NULL,
                    FOREIGN KEY (cancion_id) REFERENCES Canciones(cancion_id),
                    FOREIGN KEY (artista_id) REFERENCES Artistas(artista_id)
                );
            """);

            // Tabla Canciones_Listas (relación muchos a muchos entre Canciones y Listas_Reproduccion)
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Canciones_Listas (
                    cancion_lista_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    lista_id INTEGER NOT NULL,
                    cancion_id INTEGER NOT NULL,
                    FOREIGN KEY (lista_id) REFERENCES Listas_Reproduccion(lista_id),
                    FOREIGN KEY (cancion_id) REFERENCES Canciones(cancion_id)
                );
            """);

            // Tabla Historial_Reproduccion
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Historial_Reproduccion (
                    historial_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    usuario_id INTEGER NOT NULL,
                    cancion_id INTEGER NOT NULL,
                    fecha_reproduccion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id),
                    FOREIGN KEY (cancion_id) REFERENCES Canciones(cancion_id)
                );
            """);
        }
    }

    // Cierra la conexión al final de todas las pruebas
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public static void deleteDatabase() {
        File dbFile = new File("test_database.db");
        if (dbFile.exists()) {
            dbFile.delete(); // Borra la base de datos después de las pruebas
        }
    }
}