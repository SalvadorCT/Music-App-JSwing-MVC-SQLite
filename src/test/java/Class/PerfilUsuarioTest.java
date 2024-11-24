package Class;

import com.models.PerfilUsuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerfilUsuarioTest {

    @Test
    void testCrearPerfilUsuario() {
        PerfilUsuario perfil = new PerfilUsuario();
        perfil.setPerfilId(10);
        perfil.setUsuarioId(5);
        perfil.setFotoPerfil("http://example.com/profile.jpg");
        perfil.setBiografia("Este es mi perfil.");

        assertEquals(10, perfil.getPerfilId());
        assertEquals(5, perfil.getUsuarioId());
        assertEquals("http://example.com/profile.jpg", perfil.getFotoPerfil());
        assertEquals("Este es mi perfil.", perfil.getBiografia());
    }
}
