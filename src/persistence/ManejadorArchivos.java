package persistence;

import models.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejadorArchivos {
    private static final String RUTA_ARCHIVO = "contenidos.csv";
    private static final String CSV_SEP = ",";      // Separador de campos
    private static final String LIST_SEP = "\\|";   // Separador de listas (actores/temporadas)
    private static final String OBJ_SEP = ";";      // Separador dentro de objetos complejos

    // Guarda la lista completa en el archivo CSV
    public void guardarDatos(List<ContenidoAudiovisual> contenidos) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
            for (ContenidoAudiovisual c : contenidos) {
                writer.write(serializarObjeto(c));
                writer.newLine();
            }
        }
    }

    // Lee el archivo CSV y retorna la lista de objetos
    public List<ContenidoAudiovisual> cargarDatos() throws IOException {
        List<ContenidoAudiovisual> lista = new ArrayList<>();
        File file = new File(RUTA_ARCHIVO);
        
        // Si el archivo no existe, retornamos lista vacía para que el controlador cree los datos por defecto
        if (!file.exists()) return lista;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    ContenidoAudiovisual c = deserializarObjeto(linea);
                    if (c != null) lista.add(c);
                }
            }
        }
        return lista;
    }

    // Convierte un Objeto a String CSV
    private String serializarObjeto(ContenidoAudiovisual c) {
        StringBuilder sb = new StringBuilder();
        // Cabecera común: TIPO,ID,TITULO,DURACION,GENERO
        sb.append(c.getTipoContenido()).append(CSV_SEP)
          .append(c.getId()).append(CSV_SEP)
          .append(c.getTitulo()).append(CSV_SEP)
          .append(c.getDuracionEnMinutos()).append(CSV_SEP)
          .append(c.getGenero()).append(CSV_SEP);

        // Campos específicos según el tipo
        if (c instanceof Pelicula) {
            Pelicula p = (Pelicula) c;
            sb.append(p.getEstudio()).append(CSV_SEP);
            // Formato Actores: Nombre;Edad|Nombre;Edad
            for (Actor a : p.getReparto()) {
                sb.append(a.getNombre()).append(OBJ_SEP).append(a.getEdad()).append("|");
            }
        } else if (c instanceof SerieDeTV) {
            SerieDeTV s = (SerieDeTV) c;
            // Formato Temporadas: Num;Eps|Num;Eps
            for (Temporada t : s.getTemporadas()) {
                sb.append(t.getNumeroTemporada()).append(OBJ_SEP).append(t.getNumEpisodios()).append("|");
            }
        } else if (c instanceof Documental) {
            Documental d = (Documental) c;
            sb.append(d.getTema()).append(CSV_SEP);
            // Formato Investigador: Nombre;Especialidad
            sb.append(d.getInvestigador().getNombre()).append(OBJ_SEP).append(d.getInvestigador().getEspecialidad());
        } else if (c instanceof Cortometraje) {
            Cortometraje cm = (Cortometraje) c;
            sb.append(cm.getFestival()).append(CSV_SEP).append(cm.getAnioEstreno());
        } else if (c instanceof VideoYoutube) {
            VideoYoutube vy = (VideoYoutube) c;
            sb.append(vy.getCanal()).append(CSV_SEP).append(vy.getVisualizaciones());
        }
        
        return sb.toString();
    }

    // Convierte un String CSV a Objeto
    private ContenidoAudiovisual deserializarObjeto(String linea) {
        String[] parts = linea.split(CSV_SEP);
        
        // Datos comunes
        String tipo = parts[0];
        int id = Integer.parseInt(parts[1]);
        String titulo = parts[2];
        int duracion = Integer.parseInt(parts[3]);
        String genero = parts[4];

        switch (tipo) {
            case "PELICULA":
                String estudio = parts[5];
                List<Actor> reparto = new ArrayList<>();
                if (parts.length > 6) { // Verificar si hay actores
                    String[] actoresRaw = parts[6].split(LIST_SEP);
                    for (String s : actoresRaw) {
                        String[] dt = s.split(OBJ_SEP);
                        reparto.add(new Actor(dt[0], Integer.parseInt(dt[1])));
                    }
                }
                return new Pelicula(id, titulo, duracion, genero, estudio, reparto);

            case "SERIE":
                List<Temporada> temps = new ArrayList<>();
                if (parts.length > 5) { // Las series a veces no tienen mas campos si la lista falla, validamos
                    String[] tempsRaw = parts[5].split(LIST_SEP);
                    for (String s : tempsRaw) {
                        String[] dt = s.split(OBJ_SEP);
                        temps.add(new Temporada(Integer.parseInt(dt[0]), Integer.parseInt(dt[1])));
                    }
                }
                return new SerieDeTV(id, titulo, duracion, genero, temps);

            case "DOCUMENTAL":
                String tema = parts[5];
                String[] invData = parts[6].split(OBJ_SEP);
                Investigador inv = new Investigador(invData[0], invData[1]);
                return new Documental(id, titulo, duracion, genero, tema, inv);

            case "CORTOMETRAJE":
                String festival = parts[5];
                int anio = Integer.parseInt(parts[6]);
                return new Cortometraje(id, titulo, duracion, genero, festival, anio);

            case "YOUTUBE":
                String canal = parts[5];
                long views = Long.parseLong(parts[6]);
                return new VideoYoutube(id, titulo, duracion, genero, canal, views);

            default:
                return null;
        }
    }
}