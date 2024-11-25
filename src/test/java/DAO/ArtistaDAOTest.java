package DAO;

import com.models.Artista;
import com.models.dao.ArtistaDAO;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArtistaDAOTest {
    private static Connection connection;
    private static ArtistaDAO artistaDAO;

    @BeforeAll
    static void setUp() throws Exception {
        connection = TestDatabaseSetup.getTestConnection();
        artistaDAO = new ArtistaDAO(connection);
    }

    @AfterAll
    static void tearDown() throws Exception {
        TestDatabaseSetup.closeConnection();
        TestDatabaseSetup.deleteDatabase();
    }

    @BeforeEach
    void cleanDatabase() throws Exception {
        if (connection == null || connection.isClosed()) {
            connection = TestDatabaseSetup.getTestConnection();
        }
        try (var stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM Artistas;");
        }
    }

    @Test
    void insertarArtista() throws SQLException {
        Artista artista = new Artista();
        artista.setNombre("Artista Prueba");
        artista.setGenero("Rock");
        artista.setPaisOrigen("USA");

        artistaDAO.insertar(artista);

        List<Artista> artistas = artistaDAO.obtenerTodos();
        assertNotNull(artistas);
        assertFalse(artistas.isEmpty());
        assertEquals("Artista Prueba", artistas.get(0).getNombre());
    }

    @Test
    void eliminarArtistaExistente() throws SQLException {
        Artista artista = new Artista();
        artista.setNombre("Artista Eliminar");
        artista.setGenero("Pop");
        artista.setPaisOrigen("UK");

        artistaDAO.insertar(artista);

        artistaDAO.eliminar(artista.getArtistaId());

        Optional<Artista> artistaObtenido = artistaDAO.obtenerPorId(artista.getArtistaId());
        assertTrue(artistaObtenido.isEmpty());
    }

    @Test
    void eliminarArtistaInexistente() {
        assertThrows(SQLException.class, () -> artistaDAO.eliminar(-1));
    }

    @Test
    void obtenerArtistaPorIdExistente() throws SQLException {
        Artista artista = new Artista();
        artista.setNombre("Artista Existente");
        artista.setGenero("Jazz");
        artista.setPaisOrigen("France");

        artistaDAO.insertar(artista);

        Optional<Artista> artistaObtenido = artistaDAO.obtenerPorId(artista.getArtistaId());
        assertTrue(artistaObtenido.isPresent());
        assertEquals("Artista Existente", artistaObtenido.get().getNombre());
    }

    @Test
    void obtenerArtistaPorIdInexistente() throws SQLException {
        Optional<Artista> artistaObtenido = artistaDAO.obtenerPorId(-1);
        assertTrue(artistaObtenido.isEmpty());
    }

    @Test
    void actualizarArtistaExistente() throws SQLException {
        Artista artista = new Artista();
        artista.setNombre("Artista Actualizar");
        artista.setGenero("Blues");
        artista.setPaisOrigen("Canada");

        artistaDAO.insertar(artista);

        artista.setNombre("Artista Actualizado");
        artistaDAO.actualizar(artista);

        Optional<Artista> artistaActualizado = artistaDAO.obtenerPorId(artista.getArtistaId());
        assertTrue(artistaActualizado.isPresent());
        assertEquals("Artista Actualizado", artistaActualizado.get().getNombre());
    }

    @Test
    void actualizarArtistaInexistente() {
        Artista artista = new Artista();
        artista.setArtistaId(-1);
        artista.setNombre("Artista Inexistente");
        artista.setGenero("Classical");
        artista.setPaisOrigen("Germany");

        assertThrows(SQLException.class, () -> artistaDAO.actualizar(artista));
    }
}