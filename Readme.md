
# Sistema de Gestión de Contenido Audiovisual (Refactorizado)

Este proyecto es una evolución de un sistema de gestión de contenidos audiovisuales. Se ha refactorizado completamente aplicando principios de **Código Limpio**, **SOLID**, patrón **MVC** y **Persistencia de Datos**.

**Estudiante:** Alexis Gabriel Franco Calle  
**Asignatura:** Programación Orientada a Objetos  
**Unidad:** 4

##  Características Implementadas

### 1. Persistencia de Datos
- **Lectura/Escritura:** El sistema ahora guarda la información en un archivo `contenidos.csv`.
- **Integridad:** Los datos persisten entre ejecuciones. Si el archivo no existe, se generan datos de prueba automáticamente.

### 2. Arquitectura MVC (Modelo-Vista-Controlador)
El código se ha organizado en paquetes para separar responsabilidades:
- `models`: Contiene las entidades (Pelicula, Serie, VideoYoutube, etc.). Son POJOs puros sin lógica de visualización.
- `views`: Maneja la interacción con el usuario a través de la consola (`VistaConsola`).
- `controllers`: Coordina la lógica del negocio (`ControladorAudiovisual`), conectando la vista con el modelo y la persistencia.
- `persistence`: Se encarga exclusivamente de la gestión del archivo CSV (`ManejadorArchivos`).

### 3. Principios SOLID y Clean Code
- **SRP (Single Responsibility):** Cada clase tiene una única responsabilidad. Se eliminaron los `System.out.println` de las clases del modelo.
- **OCP (Open/Closed):** El sistema permite agregar nuevos tipos de contenido (ej. Podcast) extendiendo la clase abstracta sin romper la lógica existente.
- **Nombres Claros:** Variables y métodos renombrados para ser autoexplicativos.
- **LSP (Liskov Substitution):** Todas las subclases de `ContenidoAudiovisual` son intercambiables en las listas del controlador.

### 4. Nuevas Funcionalidades
- **Ingreso Manual:** Se agregó la opción de ingresar nuevos videos de YouTube manualmente desde el menú.
- **Validaciones:** Control de errores en la entrada de datos (evita caídas si se ingresan letras en campos numéricos).

##  Estructura del Proyecto

```text
src/
├── controllers/
│   └── ControladorAudiovisual.java  # Cerebro de la aplicación
├── main/
│   └── MainApp.java                 # Punto de entrada (main)
├── models/
│   ├── ContenidoAudiovisual.java    # Clase Padre Abstracta
│   ├── Pelicula.java
│   ├── SerieDeTV.java
│   ├── VideoYoutube.java            # Nueva clase
│   ├── Cortometraje.java            # Nueva clase
│   └── (Clases auxiliares: Actor, Temporada, Investigador)
├── persistence/
│   └── ManejadorArchivos.java       # Lógica I/O (CSV)
├── test/
│   ├── PruebasModelos.java          # Tests de lógica de negocio
│   └── PruebasPersistencia.java     # Tests de lectura/escritura
└── views/
    └── VistaConsola.java            # Interfaz de Usuario
```

##  Requisitos Previos

- **Java Development Kit (JDK) 20** o superior.
- **VS CODE** (o cualquier IDE de Java).
- **JUnit 5** (incluido en el Build Path para ejecutar las pruebas).

##  Instrucciones de Ejecución

### Clonar el Repositorio
```bash
git clone https://github.com/dami84569-hue/POO_Unidad4.git
cd POO_Unidad4
```

### Ejecutar la Aplicación
1. Abra el proyecto en Eclipse.
2. Navegue a `src/main/MainApp.java`.
3. Haga clic derecho -> **Run As** -> **Java Application**.
4. Siga las instrucciones del menú en consola.

### Ejecutar Pruebas Unitarias
1. Navegue a la carpeta `src/test/`.
2. Haga clic derecho sobre `PruebasModelos.java` o `PruebasPersistencia.java`.
3. Seleccione **Run As** -> **JUnit Test**.
4. Verifique que la barra de resultados aparezca en verde.

##  Formato del Archivo CSV
El sistema genera automáticamente un archivo `contenidos.csv` con el siguiente formato:
`TIPO,ID,TITULO,DURACION,GENERO,DATOS_EXTRA...`

Ejemplo:
```csv
PELICULA,1,Avatar,125,Accion,20th Century Studios,Leonardo DiCaprio;50|Kate Winslet;49|
YOUTUBE,5,Análisis UML,25,Académico,DevSoft,4500
```

---
*Universidad Politécnica Salesiana - 2025*
```