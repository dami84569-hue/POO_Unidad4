package models;

public abstract class ContenidoAudiovisual {
    // Variable estática para generar IDs únicos si no vienen del archivo
    private static int contadorIds = 1;
    
    protected int id;
    private String titulo;
    private int duracionEnMinutos;
    private String genero;

    // Constructor general
    public ContenidoAudiovisual(String titulo, int duracionEnMinutos, String genero) {
        this.id = contadorIds++;
        this.titulo = titulo;
        this.duracionEnMinutos = duracionEnMinutos;
        this.genero = genero;
    }

    // Constructor para recuperar datos desde el archivo (mantiene el ID original)
    public ContenidoAudiovisual(int id, String titulo, int duracionEnMinutos, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.duracionEnMinutos = duracionEnMinutos;
        this.genero = genero;
        // Ajustamos el contador para evitar duplicados al crear nuevos objetos
        if (id >= contadorIds) {
            contadorIds = id + 1;
        }
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getDuracionEnMinutos() { return duracionEnMinutos; }
    public String getGenero() { return genero; }

    // Método abstracto para identificar el tipo al guardar en CSV
    public abstract String getTipoContenido();
}