package views;

import models.*;
import java.util.List;
import java.util.Scanner;

public class VistaConsola {
    private Scanner scanner;

    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public int mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN AUDIOVISUAL ===");
        System.out.println("1. Listar todos los contenidos");
        System.out.println("2. Agregar nuevo Video de YouTube");
        System.out.println("3. Guardar cambios en archivo");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
        
        try {
            String entrada = scanner.nextLine();
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public VideoYoutube solicitarDatosVideo() {
        System.out.println("\n--- REGISTRO DE NUEVO VIDEO ---");
        try {
            System.out.print("Ingrese el Título: ");
            String titulo = scanner.nextLine();

            System.out.print("Ingrese la Duración (minutos): ");
            int duracion = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese el Género: ");
            String genero = scanner.nextLine();

            System.out.print("Nombre del Canal: ");
            String canal = scanner.nextLine();

            System.out.print("Cantidad de Visualizaciones: ");
            long views = Long.parseLong(scanner.nextLine());

            // Retornamos el objeto creado. El ID se generará automáticamente en el constructor.
            return new VideoYoutube(titulo, duracion, genero, canal, views);

        } catch (NumberFormatException e) {
            System.out.println(">> Error: Debe ingresar valores numéricos para duración y visualizaciones.");
            return null;
        }
    }

    public void mostrarListado(List<ContenidoAudiovisual> contenidos) {
        System.out.println("\n--- LISTA DE CONTENIDOS ---");
        if (contenidos.isEmpty()) {
            System.out.println("No hay elementos registrados.");
        } else {
            for (ContenidoAudiovisual c : contenidos) {
                imprimirDetalle(c);
            }
        }
        System.out.println("---------------------------");
    }

    private void imprimirDetalle(ContenidoAudiovisual c) {
        System.out.println("------------------------------------------------");
        System.out.println("[" + c.getTipoContenido() + "] ID: " + c.getId());
        System.out.println("Título:   " + c.getTitulo());
        System.out.println("Duración: " + c.getDuracionEnMinutos() + " min | Género: " + c.getGenero());

        if (c instanceof Pelicula) {
            Pelicula p = (Pelicula) c;
            System.out.println("Estudio:  " + p.getEstudio());
            System.out.print("Reparto:  ");
            for (Actor a : p.getReparto()) {
                System.out.print(a.getNombre() + ", ");
            }
            System.out.println(); // Salto de línea
        } 
        else if (c instanceof SerieDeTV) {
            SerieDeTV s = (SerieDeTV) c;
            System.out.println("Temporadas: " + s.getTemporadas().size());
            for (Temporada t : s.getTemporadas()) {
                System.out.println("  > Temp " + t.getNumeroTemporada() + " (" + t.getNumEpisodios() + " episodios)");
            }
        } 
        else if (c instanceof Documental) {
            Documental d = (Documental) c;
            System.out.println("Tema: " + d.getTema());
            System.out.println("Investigador: " + d.getInvestigador().getNombre() + " (" + d.getInvestigador().getEspecialidad() + ")");
        } 
        else if (c instanceof Cortometraje) {
            Cortometraje cm = (Cortometraje) c;
            System.out.println("Festival: " + cm.getFestival() + " (Año: " + cm.getAnioEstreno() + ")");
        } 
        else if (c instanceof VideoYoutube) {
            VideoYoutube vy = (VideoYoutube) c;
            System.out.println("Canal: " + vy.getCanal());
            System.out.println("Vistas: " + vy.getVisualizaciones());
        }
    }

    public void despedida() {
        System.out.println("Saliendo del sistema...");
    }
}