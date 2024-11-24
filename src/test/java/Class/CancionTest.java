package Class;

import com.models.Cancion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CancionTest {

    @Test
    void testCrearCancion() {
        Cancion cancion = new Cancion();
        cancion.setCancionId(101);
        cancion.setTitulo("Song Title");
        cancion.setDuracion(240);
        cancion.setAlbumId(10);
        cancion.setUrlArchivo("http://example.com/song.mp3");
        cancion.setConteoReproducciones(5000);

        assertEquals(101, cancion.getCancionId());
        assertEquals("Song Title", cancion.getTitulo());
        assertEquals(240, cancion.getDuracion());
        assertEquals(10, cancion.getAlbumId());
        assertEquals("http://example.com/song.mp3", cancion.getUrlArchivo());
        assertEquals(5000, cancion.getConteoReproducciones());
    }

    @Test
    void testConteoReproduccionesNegativo() {
        Cancion cancion = new Cancion();
        cancion.setConteoReproducciones(-10);

        assertEquals(-10, cancion.getConteoReproducciones());
    }
}
