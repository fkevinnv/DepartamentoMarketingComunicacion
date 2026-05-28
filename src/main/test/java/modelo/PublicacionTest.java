package modelo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de Publicacion")
public class PublicacionTest {

    @Test
    @DisplayName("constructor vacio no debe ser null")
    public void testConstructorVacio() {
        Publicacion p = new Publicacion();
        assertNotNull(p);
    }

    @Test 
    @DisplayName("el tipo debe ser valido"){
        public void testTipoValido(){
            Publicacion p = new Publicacion();
            p.setTipo("evento");
            assertTrue(p.getTipo().equals("noticia") || p.getTipo().equals("campaña") || p.getTipo().equals("evento"));
        }
    }

    @Test
    @DisplayName("el titulo no debe ser null"){
        public void testTituloNoNull(){
            Publicacion p = new Publicacion(1, "titulo", "noticia", "publicado", "2026-05-12", "Contenido", 1);
            assertNotNull(p.getTitulo());
        }
    }
}