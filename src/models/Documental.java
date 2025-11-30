package models;

public class Documental extends ContenidoAudiovisual {
    private String tema;
    private Investigador investigador;

    public Documental(String titulo, int duracion, String genero, String tema, Investigador investigador) {
        super(titulo, duracion, genero);
        this.tema = tema;
        this.investigador = investigador;
    }

    public Documental(int id, String titulo, int duracion, String genero, String tema, Investigador investigador) {
        super(id, titulo, duracion, genero);
        this.tema = tema;
        this.investigador = investigador;
    }

    public String getTema() { return tema; }
    public Investigador getInvestigador() { return investigador; }

    @Override
    public String getTipoContenido() { return "DOCUMENTAL"; }
}