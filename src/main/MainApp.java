package main;

import controllers.ControladorAudiovisual;

public class MainApp {
    
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Contenido Audiovisual...");
        
        // Instanciamos el controlador y delegamos la ejecución
        ControladorAudiovisual controlador = new ControladorAudiovisual();
        controlador.iniciar();
    }
}