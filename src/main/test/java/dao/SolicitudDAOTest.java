package dao;

import modelo.Solicitud;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de SolicitudDAO")
public class SolicitudDAOTest {

    @Test
    @DisplayName("Obtener todas las solicitudes no debe ser null")
    public void testObtenerTodasSolicitudes() {
        SolicitudDAO dao = new SolicitudDAO();
        ArrayList<Solicitud> lista = dao.obtenerTodasSolicitudes();
        assertNotNull(lista);
    }

    @Test
    @DisplayName("Insertar solicitud correctamente")
    public void testInsertar() {
        SolicitudDAO dao = new SolicitudDAO();
        Solicitud s = new Solicitud();
        s.setIdEmpleado(1);
        s.setAsunto("Solicitud de prueba");
        s.setDescripcion("Descripcion de prueba");

        boolean resultado = dao.insertar(s);
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Insertar solicitud null debe devolver false")
    public void testInsertarNull() {
        SolicitudDAO dao = new SolicitudDAO();
        boolean resultado = dao.insertar(null);
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Actualizar estado de solicitud correctamente")
    public void testActualizarEstado() {
        SolicitudDAO dao = new SolicitudDAO();
        Solicitud s = new Solicitud();
        s.setIdEmpleado(1);
        s.setAsunto("Solicitud estado test");
        s.setDescripcion("Descripcion");
        dao.insertar(s);

        ArrayList<Solicitud> lista = dao.obtenerTodasSolicitudes();
        int id = lista.get(lista.size() - 1).getId();
        boolean resultado = dao.actualizarEstado(id, "respondida");
        assertTrue(resultado);
    }
}