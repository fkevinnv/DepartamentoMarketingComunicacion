package dao;

import modelo.Mensaje;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de MensajeDAO")
public class MensajeDAOTest {

    @Test
    @DisplayName("Obtener mensajes de una solicitud no debe ser null")
    public void testObtenerMensajesSolicitud() {
        MensajeDAO dao = new MensajeDAO();
        ArrayList<Mensaje> lista = dao.obtenerMensajesSolicitud(1);
        assertNotNull(lista);
    }

    @Test
    @DisplayName("Obtener mensaje por id existente o null sin excepcion")
    public void testObtenerMensaje() {
        MensajeDAO dao = new MensajeDAO();
        Mensaje m = dao.obtenerMensaje(1);
        assertTrue(m == null || m.getId() == 1);
    }

    @Test
    @DisplayName("Insertar mensaje correctamente")
    public void testInsertar() {
        MensajeDAO dao = new MensajeDAO();
        Mensaje m = new Mensaje();
        m.setIdSolicitud(1);
        m.setIdEmpleado(1);
        m.setContenido("Mensaje de prueba");

        boolean resultado = dao.insertar(m);
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Insertar mensaje null debe devolver false")
    public void testInsertarNull() {
        MensajeDAO dao = new MensajeDAO();
        boolean resultado = dao.insertar(null);
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Eliminar mensaje correctamente")
    public void testEliminar() {
        MensajeDAO dao = new MensajeDAO();
        Mensaje m = new Mensaje();
        m.setIdSolicitud(1);
        m.setIdEmpleado(1);
        m.setContenido("Mensaje a eliminar");
        dao.insertar(m);

        ArrayList<Mensaje> lista = dao.obtenerMensajesSolicitud(1);
        int id = lista.get(lista.size() - 1).getId();
        boolean resultado = dao.eliminar(id);
        assertTrue(resultado);
    }
}
