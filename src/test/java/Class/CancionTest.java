package Class;

import com.models.Cancion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CancionTest {

    @Test
    void testCrearCancion() {
        Cancion cancion = new Cancion();
        cancion.setCancion_Id(101);
        cancion.setTitulo("Song Title");
        cancion.setDuracion(240);
        cancion.setAlbum_Id(10);
        cancion.setUrlArchivo("http://example.com/song.mp3");
        cancion.setConteo_Reproducciones(5000);

        assertEquals(101, cancion.getCancion_Id());
        assertEquals("Song Title", cancion.getTitulo());
        assertEquals(240, cancion.getDuracion());
        assertEquals(10, cancion.getAlbum_Id());
        assertEquals("http://example.com/song.mp3", cancion.getUrlArchivo());
        assertEquals(5000, cancion.getConteo_Reproducciones());
    }

    @Test
    void testConteoReproduccionesNegativo() {
        Cancion cancion = new Cancion();
        cancion.setConteo_Reproducciones(-10);

        assertEquals(-10, cancion.getConteo_Reproducciones());
    }
}
