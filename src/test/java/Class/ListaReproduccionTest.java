package Class;

import com.models.ListaReproduccion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaReproduccionTest {

    @Test
    void testCrearListaReproduccion() {
        ListaReproduccion lista = new ListaReproduccion();
        lista.setLista_Id(1);
        lista.setNombre("Mi Lista");
        lista.setUsuario_Id(42);
        lista.setFecha_Creacion(1000L);
        lista.setPrivacidad("Privado");

        assertEquals(1, lista.getLista_Id());
        assertEquals("Mi Lista", lista.getNombre());
        assertEquals(42, lista.getUsuario_Id());
        assertNotNull(lista.getFecha_Creacion());
        assertEquals("Privado", lista.getPrivacidad());
    }
}
