package Class;

import com.models.ListaReproduccion;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ListaReproduccionTest {

    @Test
    void testCrearListaReproduccion() {
        ListaReproduccion lista = new ListaReproduccion();
        lista.setListaId(1);
        lista.setNombre("Mi Lista");
        lista.setUsuarioId(42);
        lista.setFechaCreacion(new Date());
        lista.setPrivacidad("Privado");

        assertEquals(1, lista.getListaId());
        assertEquals("Mi Lista", lista.getNombre());
        assertEquals(42, lista.getUsuarioId());
        assertNotNull(lista.getFechaCreacion());
        assertEquals("Privado", lista.getPrivacidad());
    }
}
