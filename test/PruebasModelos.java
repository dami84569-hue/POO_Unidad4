package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import models.*;
import java.util.ArrayList;
import java.util.List;

class PruebasModelos {

    @Test
    @DisplayName("Prueba de creación de Película y Agregación de Actores")
    void testCreacionPelicula() {
        // 1. Preparar datos (Arrange)
        Actor actor1 = new Actor("Brad Pitt", 55);
        List<Actor> reparto = new ArrayList<>();
        reparto.add(actor1);

        // 2. Ejecutar (Act)
        Pelicula pelicula = new Pelicula("Troya", 160, "Épico", "Warner Bros", reparto);

        // 3. Verificar (Assert)
        assertEquals("Troya", pelicula.getTitulo());
        assertEquals("PELICULA", pelicula.getTipoContenido());
        assertEquals(1, pelicula.getReparto().size());
        assertEquals("Brad Pitt", pelicula.getReparto().get(0).getNombre());
    }

    @Test
    @DisplayName("Prueba de Serie de TV y Temporadas")
    void testCreacionSerie() {
        Temporada t1 = new Temporada(1, 12);
        List<Temporada> temporadas = new ArrayList<>();
        temporadas.add(t1);

        SerieDeTV serie = new SerieDeTV("Breaking Bad", 50, "Drama", temporadas);

        assertNotNull(serie.getTemporadas());
        assertEquals(12, serie.getTemporadas().get(0).getNumEpisodios());
        assertEquals("SERIE", serie.getTipoContenido());
    }

    @Test
    @DisplayName("Prueba de Documental y Composición de Investigador")
    void testCreacionDocumental() {
        Investigador investigador = new Investigador("Carl Sagan", "Astronomía");
        Documental doc = new Documental("Cosmos", 60, "Ciencia", "Universo", investigador);

        assertEquals("Cosmos", doc.getTitulo());
        assertEquals("Carl Sagan", doc.getInvestigador().getNombre());
        assertEquals("Astronomía", doc.getInvestigador().getEspecialidad());
    }

    @Test
    @DisplayName("Prueba de Video YouTube y tipos de datos largos")
    void testVideoYoutube() {
        // Probamos con un número grande de visualizaciones para asegurar que el 'long' funciona
        long vistasEsperadas = 5000000000L; 
        VideoYoutube video = new VideoYoutube("Tutorial Java", 10, "Educación", "ProfeCode", vistasEsperadas);

        assertEquals(vistasEsperadas, video.getVisualizaciones());
        assertEquals("YOUTUBE", video.getTipoContenido());
    }

    @Test
    @DisplayName("Prueba de Polimorfismo en Lista")
    void testPolimorfismo() {
        List<ContenidoAudiovisual> lista = new ArrayList<>();
        lista.add(new Pelicula("A", 1, "B", "C", new ArrayList<>()));
        lista.add(new Cortometraje("D", 2, "E", "F", 2020));

        // Verificamos que la lista acepte distintos subtipos
        assertEquals(2, lista.size());
        assertTrue(lista.get(0) instanceof Pelicula);
        assertTrue(lista.get(1) instanceof Cortometraje);
    }
}