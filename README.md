# Parque Turístico de Dinosaurios
Simulación secuencial en Java 17 de un parque temático de dinosaurios.

## Herramientas utilizadas
El proyecto fue desarrollado utilizando las siguientes herramientas y tecnologías:
- Java 17
- Maven
- JUnit 5
- Mockito
- Git
- GitHub

### Dependencias principales
- `junit-jupiter` — pruebas unitarias
- `mockito-junit-jupiter` — creación de mocks para pruebas
- `exec-maven-plugin` — ejecución del proyecto mediante Maven
- `jacoco-maven-plugin` — análisis de cobertura de pruebas

### Entorno de desarrollo
- Lenguaje: Java 17
- Sistema de construcción: Maven
- Paradigma principal: Programación Orientada a Objetos
- Arquitectura basada en paquetes y separación de responsabilidades

## Instrucciones de configuración

### Requisitos previos
Antes de ejecutar el proyecto es necesario contar con:
- Java 17 o superior
- Maven 3.9 o superior
- Git

### Clonar el repositorio
`git clone <URL_DEL_REPOSITORIO>`
`cd dinosaur-park`

### Compilar el proyecto
`mvn clean compile`

### Ejecutar pruebas unitarias
`mvn test`

### Revisar reporte de cobertura
Después de ejecutar las pruebas unitarias, el reporte se encontrará ubicado en `target/site/jacoco/index.html`.
Abrir en un navegador.

### Archivo de configuración
El proyecto utiliza un archivo `park.properties` ubicado en: `src/main/resources/park.properties`.

Este archivo contiene parámetros de configuración de la simulación, como:
- Cantidad de turistas
- Número de dinosaurios
- Capacidad de zonas
- Probabilidades de eventos
- Costos operativos
- Configuración de energía

## Forma de ejecución del proyecto
Para ejecutar el proyecto mediante Maven utiliza el siguiente comando:
`mvn exec:java`

También es posible ejecutar la clase principal directamente desde un IDE:
`com.axity.dinosaurpark.Main`

### Comportamiento actual
Actualmente la clase `Main` contiene una implementación inicial utilizada para validar la configuración del proyecto y la correcta compilación mediante Maven.

El flujo completo de simulación del parque continúa en desarrollo.

## Explicación general del sistema
El proyecto consiste en una simulación secuencial de un Parque Turístico de Dinosaurios desarrollada en Java 17 utilizando Programación Orientada a Objetos y Maven.

El sistema modela distintas entidades relacionadas con la operación del parque, incluyendo:
- Turistas
- Dinosaurios
- Trabajadores
- Vehículos
- Zonas del parque

La aplicación fue diseñada siguiendo una arquitectura organizada por paquetes y separación de responsabilidades, permitiendo mantener una estructura modular y escalable.
### Funcionalidades implementadas
Actualmente el proyecto incluye la implementación de:
- Configuración centralizada mediante `ParkConfig`
- Modelado de turistas y dinosaurios
- Jerarquía de trabajadores (`Guard` y `Technician`)
- Sistema básico de vehículos
- Zonas principales del parque:
    - `ArrivalZone`
    -  `CentralHub`
    -  `BathroomZone`
- Manejo de capacidades y flujo básico de turistas
- Configuración externa mediante `park.properties`

### Características técnicas implementadas
Durante el desarrollo se aplicaron conceptos como:
- Programación Orientada a Objetos
- Herencia y Polimorfismo
- Interfaces
- Encapsulamiento
- Uso de enums
- Patrón Singleton

### Estado actual del proyecto
El proyecto se encuentra parcialmente implementado.  
Algunas funcionalidades avanzadas como persistencia completa, simulación de eventos, monitoreo y motor principal de simulación continúan en desarrollo.

## Patrones de diseño utilizados
Durante el desarrollo del proyecto se aplicaron distintos patrones y principios de diseño orientados a mantener una arquitectura modular, reutilizable y organizada.

### Singleton — `ParkConfig`
El patrón Singleton fue utilizado en la clase `ParkConfig`.

Su propósito es garantizar la existencia de una única instancia encargada de cargar y administrar la configuración global del sistema mediante el archivo `park.properties`.

Características implementadas:
- Constructor privado
- Instancia única compartida
- Punto de acceso global mediante `getInstance()`
- Lectura centralizada de propiedades.

Esto permite que todas las clases del sistema utilicen la misma configuración sin duplicar lecturas del archivo.