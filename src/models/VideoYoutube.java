package models;

public class VideoYoutube extends ContenidoAudiovisual {
    private String canal;
    private long visualizaciones;

    public VideoYoutube(String titulo, int duracion, String genero, String canal, long visualizaciones) {
        super(titulo, duracion, genero);
        this.canal = canal;
        this.visualizaciones = visualizaciones;
    }

    public VideoYoutube(int id, String titulo, int duracion, String genero, String canal, long visualizaciones) {
        super(id, titulo, duracion, genero);
        this.canal = canal;
        this.visualizaciones = visualizaciones;
    }

    public String getCanal() { return canal; }
    public long getVisualizaciones() { return visualizaciones; }

    @Override
    public String getTipoContenido() { return "YOUTUBE"; }
}