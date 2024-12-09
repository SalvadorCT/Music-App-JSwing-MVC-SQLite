package Class;

import com.models.PerfilUsuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerfilUsuarioTest {

    @Test
    void testCrearPerfilUsuario() {
        PerfilUsuario perfil = new PerfilUsuario();
        perfil.setPerfil_Id(10);
        perfil.setUsuario_Id(5);
        perfil.setFoto_Perfil("http://example.com/profile.jpg");
        perfil.setBiografia("Este es mi perfil.");

        assertEquals(10, perfil.getPerfil_Id());
        assertEquals(5, perfil.getUsuario_Id());
        assertEquals("http://example.com/profile.jpg", perfil.getFoto_Perfil());
        assertEquals("Este es mi perfil.", perfil.getBiografia());
    }
}
