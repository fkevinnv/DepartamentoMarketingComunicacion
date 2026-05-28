package dao;

import modelo.Publicacion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de PublicacionDAO")
public class PublicacionDAOTest {

    @Test
    @DisplayName("Obtener publicaciones publicadas no debe ser null")
    public void testObtenerPublicados() {
        PublicacionDAO dao = new PublicacionDAO();
        ArrayList<Publicacion> lista = dao.obtenerPublicados();
        assertNotNull(lista);
    }

    @Test
    @DisplayName("Obtener todas las publicaciones no debe ser null")
    public void testObtenerTodasPublicaciones() {
        PublicacionDAO dao = new PublicacionDAO();
        ArrayList<Publicacion> lista = dao.obtenerTodasPublicaciones();
        assertNotNull(lista);
    }

    @Test
    @DisplayName("Insertar publicacion correctamente")
    public void testInsertar() {
        PublicacionDAO dao = new PublicacionDAO();
        Publicacion p = new Publicacion();
        p.setTitulo("Publicacion test");
        p.setTipo("noticia");
        p.setEstado("borrador");
        p.setContenido("contenidoprueba");
        p.setIdSede(1);

        boolean resultado = dao.insertar(p);
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Insertar publicacion null debe devolver false")
    public void testInsertarNull() {
        PublicacionDAO dao = new PublicacionDAO();
        boolean resultado = dao.insertar(null);
        assertFalse(resultado);
    }
}