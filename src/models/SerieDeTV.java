package models;
import java.util.List;

public class SerieDeTV extends ContenidoAudiovisual {
    private List<Temporada> temporadas;

    public SerieDeTV(String titulo, int duracion, String genero, List<Temporada> temporadas) {
        super(titulo, duracion, genero);
        this.temporadas = temporadas;
    }

    public SerieDeTV(int id, String titulo, int duracion, String genero, List<Temporada> temporadas) {
        super(id, titulo, duracion, genero);
        this.temporadas = temporadas;
    }

    public List<Temporada> getTemporadas() { return temporadas; }

    @Override
    public String getTipoContenido() { return "SERIE"; }
}