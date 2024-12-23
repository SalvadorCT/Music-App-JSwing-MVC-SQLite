package Class;

import com.models.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void testCrearUsuario() {
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(1);
        usuario.setNombre("Carlos");
        usuario.setEmail("carlos@example.com");
        usuario.setTipoSuscripcion("Premium");
        usuario.setFechaCreacion(System.currentTimeMillis());
        usuario.setContrasenaoaHash("hashed_password");
        usuario.setEstado("Activo");

        assertEquals(1, usuario.getUsuarioId());
        assertEquals("Carlos", usuario.getNombre());
        assertEquals("carlos@example.com", usuario.getEmail());
        assertEquals("Premium", usuario.getTipoSuscripcion());
        assertNotNull(usuario.getFechaCreacion());
        assertEquals("hashed_password", usuario.getContrasenaoaHash());
        assertEquals("Activo", usuario.getEstado());
    }

    @Test
    void testUsuarioSinNombre() {
        Usuario usuario = new Usuario();
        usuario.setNombre(null);

        assertNull(usuario.getNombre());
    }
}
