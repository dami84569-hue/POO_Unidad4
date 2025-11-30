package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.*;
import persistence.ManejadorArchivos;
import views.VistaConsola;

public class ControladorAudiovisual {
    private List<ContenidoAudiovisual> contenidos;
    private VistaConsola vista;
    private ManejadorArchivos persistencia;

    public ControladorAudiovisual() {
        this.vista = new VistaConsola();
        this.persistencia = new ManejadorArchivos();
        this.contenidos = new ArrayList<>();
    }

    public void iniciar() {
            cargarDatosDeArchivo();

            boolean continuar = true;
            while (continuar) {
                int opcion = vista.mostrarMenu();
                switch (opcion) {
                    case 1:
                        vista.mostrarListado(contenidos);
                        break;
                    case 2:
                        // Lógica para agregar manual
                        VideoYoutube nuevoVideo = vista.solicitarDatosVideo();
                        if (nuevoVideo != null) {
                            contenidos.add(nuevoVideo);
                            vista.mostrarMensaje("¡Video agregado a la lista en memoria!");
                            vista.mostrarMensaje("Recuerda usar la opción 'Guardar' para persistir los cambios.");
                        }
                        break;
                    case 3:
                        guardarDatosEnArchivo();
                        break;
                    case 4:
                        continuar = false;
                        vista.despedida();
                        break;
                    default:
                        vista.mostrarMensaje("Opción no válida.");
                }
            }
        }

    private void cargarDatosDeArchivo() {
        try {
            contenidos = persistencia.cargarDatos();
            if (contenidos.isEmpty()) {
                vista.mostrarMensaje("Archivo vacío o inexistente. Cargando datos por defecto...");
                cargarDatosHardcoded(); // Método auxiliar creado anteriormente
                guardarDatosEnArchivo();
            } else {
                vista.mostrarMensaje("Datos cargados: " + contenidos.size() + " elementos.");
            }
        } catch (IOException e) {
            vista.mostrarMensaje("Error al cargar archivo: " + e.getMessage());
        }
    }

    private void guardarDatosEnArchivo() {
        try {
            persistencia.guardarDatos(contenidos);
            vista.mostrarMensaje("Datos guardados correctamente en contenidos.csv");
        } catch (IOException e) {
            vista.mostrarMensaje("Error al guardar: " + e.getMessage());
        }
    }

    private void cargarDatosHardcoded() {
        // (Aquí va el mismo código de creación de objetos que te di antes)
        // Solo para no repetir código aquí, asegúrate de mantener las listas de Actores, etc.
        List<Actor> reparto = new ArrayList<>();
        reparto.add(new Actor("Ejemplo Actor", 30));
        contenidos.add(new Pelicula("Pelicula Ejemplo", 120, "Test", "Estudio", reparto));
        // ... Agrega el resto ...
    }
}