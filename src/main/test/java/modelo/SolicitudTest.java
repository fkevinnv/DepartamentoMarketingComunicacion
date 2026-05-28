package modelo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de Solicitud")
public class SolicitudTest {

    @Test
    @DisplayName("el estado debe ser valido")
        public void testEstadoValido(){
            Solicitud s = new Solicitud();
            s.setEstado("cerrada");
            assertTrue(s.setEstado().equals("pendiente") || s.getEstado().equals("respondida") || s.getEstado().equals("cerrada"));
        }
    @Test
    @DisplayName("El estado no debe ser null")
        public void testEstadoNoNull() {
            Solicitud s = new Solicitud(1, 1, "Asunto", "Descripcion", "pendiente", "2026-05-12");
            assertNotNull(s.getEstado());
    }
    @Test
    @DisplayName("Constructor con parametros asigna valores correctamente")
    public void testConstructorConParametros() {
        Solicitud s = new Solicitud(1, 3, "Asunto prueba", "Descripcion prueba", "pendiente", "2026-05-12");
        assertEquals(1, s.getId());
        assertEquals(3, s.getIdEmpleado());
        assertEquals("Asunto prueba", s.getAsunto());
        assertEquals("Descripcion prueba", s.getDescripcion());
        assertEquals("pendiente", s.getEstado());
        assertEquals("2026-05-12", s.getFechaSolicitud());
    }

}