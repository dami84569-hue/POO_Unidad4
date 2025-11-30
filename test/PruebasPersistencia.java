package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import models.*;
import persistence.ManejadorArchivos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class PruebasPersistencia {

    private ManejadorArchivos manejador;
    private List<ContenidoAudiovisual> listaOriginal;

    @BeforeEach
    void setUp() {
        manejador = new ManejadorArchivos();
        listaOriginal = new ArrayList<>();
    }

    @Test
    void testGuardarYCargarDatos() {
        // 1. Crear datos de prueba complejos
        List<Actor> reparto = new ArrayList<>();
        reparto.add(new Actor("Test Actor", 30));
        
        Pelicula peli = new Pelicula("Peli Test", 120, "TestGenre", "Estudio X", reparto);
        VideoYoutube video = new VideoYoutube("Video Test", 10, "Vlog", "Canal Y", 1000L);
        
        listaOriginal.add(peli);
        listaOriginal.add(video);

        // 2. Guardar en archivo
        try {
            manejador.guardarDatos(listaOriginal);
        } catch (IOException e) {
            fail("Falló la escritura del archivo: " + e.getMessage());
        }

        // 3. Leer del archivo (simulando reiniciar el programa)
        List<ContenidoAudiovisual> listaCargada = new ArrayList<>();
        try {
            listaCargada = manejador.cargarDatos();
        } catch (IOException e) {
            fail("Falló la lectura del archivo: " + e.getMessage());
        }

        // 4. Validaciones
        assertEquals(listaOriginal.size(), listaCargada.size(), "El tamaño de la lista leída debe ser igual a la guardada");
        
        // Validar el primer elemento (Película)
        ContenidoAudiovisual item1 = listaCargada.get(0);
        assertTrue(item1 instanceof Pelicula);
        assertEquals("Peli Test", item1.getTitulo());
        assertEquals("Test Actor", ((Pelicula) item1).getReparto().get(0).getNombre());

        // Validar el segundo elemento (YouTube)
        ContenidoAudiovisual item2 = listaCargada.get(1);
        assertTrue(item2 instanceof VideoYoutube);
        assertEquals(1000L, ((VideoYoutube) item2).getVisualizaciones());
    }

    @Test
    void testManejoArchivoVacio() throws IOException {
        // Aseguramos que el archivo existe pero está vacío o simulamos una carga sin datos
        File file = new File("contenidos.csv");
        if(file.exists()) {
            file.delete(); // Borramos para simular primera ejecución
        }

        List<ContenidoAudiovisual> resultado = manejador.cargarDatos();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty(), "La lista debería estar vacía si el archivo no existe");
    }
}