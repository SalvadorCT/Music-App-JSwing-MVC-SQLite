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
        usuario.setTipo_Suscripcion("Premium");
        usuario.setFecha_Creacion(System.currentTimeMillis());
        usuario.setContrasena_Hash("hashed_password");
        usuario.setEstado("Activo");

        assertEquals(1, usuario.getUsuarioId());
        assertEquals("Carlos", usuario.getNombre());
        assertEquals("carlos@example.com", usuario.getEmail());
        assertEquals("Premium", usuario.getTipo_Suscripcion());
        assertNotNull(usuario.getFecha_Creacion());
        assertEquals("hashed_password", usuario.getContrasena_Hash());
        assertEquals("Activo", usuario.getEstado());
    }

    @Test
    void testUsuarioSinNombre() {
        Usuario usuario = new Usuario();
        usuario.setNombre(null);

        assertNull(usuario.getNombre());
    }
}
