package models;

public class Cortometraje extends ContenidoAudiovisual {
    private String festival;
    private int anioEstreno;

    public Cortometraje(String titulo, int duracion, String genero, String festival, int anioEstreno) {
        super(titulo, duracion, genero);
        this.festival = festival;
        this.anioEstreno = anioEstreno;
    }

    public Cortometraje(int id, String titulo, int duracion, String genero, String festival, int anioEstreno) {
        super(id, titulo, duracion, genero);
        this.festival = festival;
        this.anioEstreno = anioEstreno;
    }

    public String getFestival() { return festival; }
    public int getAnioEstreno() { return anioEstreno; }

    @Override
    public String getTipoContenido() { return "CORTOMETRAJE"; }
}