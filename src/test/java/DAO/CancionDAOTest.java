package DAO;

import com.models.Cancion;
import com.models.dao.CancionDAO;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CancionDAOTest {
    private static Connection connection;
    private static CancionDAO cancionDAO;

    @BeforeAll
    static void setUp() throws Exception {
        connection = TestDatabaseSetup.getTestConnection(); // Persistent connection
        cancionDAO = new CancionDAO(connection);
    }

    @AfterAll
    static void tearDown() throws Exception {
        TestDatabaseSetup.closeConnection();
        TestDatabaseSetup.deleteDatabase(); // Delete the database file
    }

    @BeforeEach
    void cleanDatabase() throws Exception {
        if (connection == null || connection.isClosed()) {
            connection = TestDatabaseSetup.getTestConnection();
        }
        try (var stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM Canciones;"); // Clean data before each test
        }
    }

    @Test
    void testInsertarYObtenerCancion() throws SQLException {
        Cancion cancion = new Cancion();
        cancion.setTitulo("Prueba Cancion");
        cancion.setDuracion(210);
        cancion.setAlbumId(1);
        cancion.setUrlArchivo("http://archivo.com/cancion.mp3");
        cancion.setConteoReproducciones(100);

        cancionDAO.insertar(cancion);

        var canciones = cancionDAO.obtenerTodos();

        assertNotNull(canciones);
        assertFalse(canciones.isEmpty());
        assertEquals("Prueba Cancion", canciones.get(0).getTitulo());
    }

    @Test
    void testInsertarYObtenerOtraCancion() throws SQLException {
        Cancion cancion = new Cancion();
        cancion.setTitulo("Otra Cancion");
        cancion.setDuracion(300);
        cancion.setAlbumId(2);
        cancion.setUrlArchivo("http://archivo.com/otra_cancion.mp3");
        cancion.setConteoReproducciones(50);

        cancionDAO.insertar(cancion);

        var canciones = cancionDAO.obtenerTodos();

        assertNotNull(canciones);
        assertTrue(canciones.stream().anyMatch(c -> c.getTitulo().equals("Otra Cancion")));
    }

    @Test
    void insertarCancionConCamposNulos() {
        Cancion cancion = new Cancion();
        cancion.setTitulo(null);
        cancion.setDuracion(0);
        cancion.setAlbumId(0);
        cancion.setUrlArchivo(null);
        cancion.setConteoReproducciones(0);

        assertThrows(SQLException.class, () -> cancionDAO.insertar(cancion));
    }

    @Test
    void obtenerCancionPorIdExistente() throws SQLException {
        Cancion cancion = new Cancion();
        cancion.setTitulo("Cancion Existente");
        cancion.setDuracion(180);
        cancion.setAlbumId(1);
        cancion.setUrlArchivo("http://archivo.com/existente.mp3");
        cancion.setConteoReproducciones(200);

        cancionDAO.insertar(cancion);
        Optional<Cancion> cancionObtenida = cancionDAO.obtenerPorId(cancion.getCancionId());

        assertNotNull(cancionObtenida);
        assertEquals("Cancion Existente", cancionObtenida.get().getTitulo());
    }

    @Test
    void obtenerCancionPorIdInexistente() throws SQLException {
        Optional<Cancion> cancionObtenida = cancionDAO.obtenerPorId(-1);
        assertEquals(Optional.empty(), cancionObtenida);
    }


    @Test
    void actualizarCancionInexistente() {
        Cancion cancion = new Cancion();
        cancion.setCancionId(-1);
        cancion.setTitulo("Cancion Inexistente");
        cancion.setDuracion(0);
        cancion.setAlbumId(0);
        cancion.setUrlArchivo("http://archivo.com/inexistente.mp3");
        cancion.setConteoReproducciones(0);

        assertThrows(SQLException.class, () -> cancionDAO.actualizar(cancion));
    }

    @Test
    void eliminarCancionExistente() throws SQLException {
        Cancion cancion = new Cancion();
        cancion.setTitulo("Cancion a Eliminar");
        cancion.setDuracion(180);
        cancion.setAlbumId(1);
        cancion.setUrlArchivo("http://archivo.com/eliminar.mp3");
        cancion.setConteoReproducciones(200);

        cancionDAO.insertar(cancion);
        cancionDAO.eliminar(cancion.getCancionId());

        var canciones = cancionDAO.obtenerTodos();
        assertFalse(canciones.stream().anyMatch(c -> c.getTitulo().equals("Cancion a Eliminar")));
    }
}