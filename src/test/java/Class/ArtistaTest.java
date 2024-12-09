package Class;

import com.models.Artista;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtistaTest {

    @Test
    void testCrearArtista() {
        Artista artista = new Artista();
        artista.setArtistaId(1);
        artista.setNombre("Juan Pérez");
        artista.setGenero("Pop");
        artista.setPais_Origen("Argentina");

        assertEquals(1, artista.getArtistaId());
        assertEquals("Juan Pérez", artista.getNombre());
        assertEquals("Pop", artista.getGenero());
        assertEquals("Argentina", artista.getPais_Origen());
    }

    @Test
    void testArtistaCamposNulos() {
        Artista artista = new Artista();
        artista.setNombre(null);
        artista.setGenero(null);
        artista.setPais_Origen(null);

        assertNull(artista.getNombre());
        assertNull(artista.getGenero());
        assertNull(artista.getPais_Origen());
    }
}
