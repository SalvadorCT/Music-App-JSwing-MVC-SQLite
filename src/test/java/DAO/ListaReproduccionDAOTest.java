package DAO;

import com.models.ListaReproduccion;
import com.models.dao.ListaReproduccionDAO;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.BeforeEach;


import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class ListaReproduccionDAOTest {
    private ListaReproduccionDAO listaReproduccionDAO;
    private Connection connection;
    private QueryRunner queryRunner;

    @BeforeEach
    void setUp() throws SQLException {
        connection = mock(Connection.class);
        queryRunner = mock(QueryRunner.class);
        listaReproduccionDAO = new ListaReproduccionDAO(connection);
    }

    void insertarListaReproduccion() throws SQLException, ParseException {
        ListaReproduccion listaReproduccion = new ListaReproduccion();
        listaReproduccion.setNombre("Lista 1");
        listaReproduccion.setUsuarioId(1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaCreacion = dateFormat.parse("2023-10-01");
        listaReproduccion.setFechaCreacion(fechaCreacion);

        listaReproduccion.setPrivacidad("Publica");

        when(queryRunner.insert(any(), anyString(), any(ScalarHandler.class), any())).thenReturn(1);

        listaReproduccionDAO.insertarListaReproduccion(listaReproduccion);

        assertNotNull(listaReproduccion.getListaId());
        assertEquals(1, listaReproduccion.getListaId());
    }

    void eliminarListaReproduccionExistente() throws SQLException {
        when(queryRunner.update(any(), anyString(), anyInt())).thenReturn(1);

        listaReproduccionDAO.eliminarListaReproduccion(1);

        verify(queryRunner, times(1)).update(any(), anyString(), anyInt());
    }

    void eliminarListaReproduccionNoExistente() throws SQLException {
        when(queryRunner.update(any(), anyString(), anyInt())).thenReturn(0);

        SQLException exception = assertThrows(SQLException.class, () -> {
            listaReproduccionDAO.eliminarListaReproduccion(1);
        });

        assertEquals("No se eliminó ninguna fila, la lista de reproducción con ID 1 no existe.", exception.getMessage());
    }

    void actualizarListaReproduccionExistente() throws SQLException, ParseException {
        ListaReproduccion listaReproduccion = new ListaReproduccion();
        listaReproduccion.setListaId(1);
        listaReproduccion.setNombre("Lista Actualizada");
        listaReproduccion.setUsuarioId(1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaCreacion = dateFormat.parse("2023-10-01");
        listaReproduccion.setFechaCreacion(fechaCreacion);

        listaReproduccion.setPrivacidad("Privada");

        when(queryRunner.update(any(), anyString(), any())).thenReturn(1);

        listaReproduccionDAO.actualizarListaReproduccion(listaReproduccion);

        verify(queryRunner, times(1)).update(any(), anyString(), any());
    }

    void actualizarListaReproduccionNoExistente() throws SQLException, ParseException {
        ListaReproduccion listaReproduccion = new ListaReproduccion();
        listaReproduccion.setListaId(1);
        listaReproduccion.setNombre("Lista Actualizada");
        listaReproduccion.setUsuarioId(1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaCreacion = dateFormat.parse("2023-10-01");
        listaReproduccion.setFechaCreacion(fechaCreacion);

        listaReproduccion.setPrivacidad("Privada");

        when(queryRunner.update(any(), anyString(), any())).thenReturn(0);

        SQLException exception = assertThrows(SQLException.class, () -> {
            listaReproduccionDAO.actualizarListaReproduccion(listaReproduccion);
        });

        assertEquals("No se actualizó ninguna fila, la lista de reproducción con ID 1 no existe.", exception.getMessage());
    }

    void obtenerListaReproduccionExistente() throws SQLException, ParseException {
        ListaReproduccion listaReproduccion = new ListaReproduccion();
        listaReproduccion.setListaId(1);
        listaReproduccion.setNombre("Lista 1");
        listaReproduccion.setUsuarioId(1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaCreacion = dateFormat.parse("2023-10-01");
        listaReproduccion.setFechaCreacion(fechaCreacion);

        listaReproduccion.setPrivacidad("Publica");

        when(queryRunner.query(any(), anyString(), any(BeanHandler.class), anyInt())).thenReturn(listaReproduccion);

        Optional<ListaReproduccion> result = listaReproduccionDAO.obtenerListaReproduccion(1);

        assertTrue(result.isPresent());
        assertEquals(listaReproduccion, result.get());
    }

    void obtenerListaReproduccionNoExistente() throws SQLException {
        when(queryRunner.query(any(), anyString(), any(BeanHandler.class), anyInt())).thenReturn(null);

        Optional<ListaReproduccion> result = listaReproduccionDAO.obtenerListaReproduccion(1);

        assertFalse(result.isPresent());
    }

    void obtenerListasReproduccion() throws SQLException {
        List<ListaReproduccion> listas = List.of(new ListaReproduccion(), new ListaReproduccion());

        when(queryRunner.query(any(), Optional.of(anyString()), any(BeanListHandler.class))).thenReturn(listas);

        List<ListaReproduccion> result = listaReproduccionDAO.obtenerListasReproduccion();

        assertEquals(2, result.size());
    }

    void obtenerListasReproduccionPorUsuario() throws SQLException {
        List<ListaReproduccion> listas = List.of(new ListaReproduccion(), new ListaReproduccion());

        when(queryRunner.query(any(), anyString(), any(BeanListHandler.class), anyInt())).thenReturn(listas);

        List<ListaReproduccion> result = listaReproduccionDAO.obtenerListasReproduccionPorUsuario(1);

        assertEquals(2, result.size());
    }

    void obtenerListasReproduccionPorNombre() throws SQLException {
        List<ListaReproduccion> listas = List.of(new ListaReproduccion(), new ListaReproduccion());

        when(queryRunner.query(any(), anyString(), any(BeanListHandler.class), anyString())).thenReturn(listas);

        List<ListaReproduccion> result = listaReproduccionDAO.obtenerListasReproduccionPorNombre("Lista 1");

        assertEquals(2, result.size());
    }

    void obtenerListasReproduccionPorPrivacidad() throws SQLException {
        List<ListaReproduccion> listas = List.of(new ListaReproduccion(), new ListaReproduccion());

        when(queryRunner.query(any(), anyString(), any(BeanListHandler.class), anyString())).thenReturn(listas);

        List<ListaReproduccion> result = listaReproduccionDAO.obtenerListasReproduccionPorPrivacidad("Publica");

        assertEquals(2, result.size());
    }

    void obtenerListasReproduccionPorFechaCreacion() throws SQLException {
        List<ListaReproduccion> listas = List.of(new ListaReproduccion(), new ListaReproduccion());

        when(queryRunner.query(any(), anyString(), any(BeanListHandler.class), anyString())).thenReturn(listas);

        List<ListaReproduccion> result = listaReproduccionDAO.obtenerListasReproduccionPorFechaCreacion("2023-10-01");

        assertEquals(2, result.size());
    }
}