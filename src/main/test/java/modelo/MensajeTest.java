package modelo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de la clase Mensaje")
public class MensajeTest {

    @Test
    @DisplayName("Constructor vacio no debe ser null")
    public void testConstructorVacio() {
        Mensaje m = new Mensaje();
        assertNotNull(m);
    }
    @Test
    @DisplayName("Setters y getters funcionan correctamente")
    public void testSettersGetters() {
        Mensaje m = new Mensaje();
        m.setId(5);
        m.setIdSolicitud(2);
        m.setIdEmpleado(3);
        m.setContenido("Mensaje de prueba");
        m.setFechaEnvio("2026-05-15");

        assertEquals(5, m.getId());
        assertEquals(2, m.getIdSolicitud());
        assertEquals(3, m.getIdEmpleado());
        assertEquals("Mensaje de prueba", m.getContenido());
        assertEquals("2026-05-15", m.getFechaEnvio());
    }

    @Test
    @DisplayName("El contenido no debe ser null")
    public void testContenidoNoNull() {
        Mensaje m = new Mensaje(1, 1, 1, "Contenido", "2026-05-14");
        assertNotNull(m.getContenido());
    }
}
