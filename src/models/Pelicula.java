package models;
import java.util.List;

public class Pelicula extends ContenidoAudiovisual {
    private String estudio;
    private List<Actor> reparto; 

    // Constructor nuevo
    public Pelicula(String titulo, int duracion, String genero, String estudio, List<Actor> reparto) {
        super(titulo, duracion, genero);
        this.estudio = estudio;
        this.reparto = reparto;
    }

    // Constructor carga de archivo
    public Pelicula(int id, String titulo, int duracion, String genero, String estudio, List<Actor> reparto) {
        super(id, titulo, duracion, genero);
        this.estudio = estudio;
        this.reparto = reparto;
    }

    public String getEstudio() { return estudio; }
    public List<Actor> getReparto() { return reparto; }

    @Override
    public String getTipoContenido() { return "PELICULA"; }
}