# Sistema de Gestión de Personal - New Team

## 📌 Descripción del Proyecto
El club de fútbol **New Team**, ha confiado en nosotros para implementar un sistema de gestión de personal para **administrar** la información de sus **jugadores y entrenadores**. Este sistema permite la gestión completa del personal a través de un **menú interactivo** en consola.

## 🔥 Funcionalidades Principales

### 📂 Gestión de Personal
- Almacena datos como ID, nombre, apellido, fecha de nacimiento, fecha de incorporación, salario y país de origen.
- **Caché LRU** con un máximo de **5 elementos** para mejorar la eficiencia.
- **Validación de datos** para evitar errores en el almacenamiento.

### 🏆 Entrenadores
- Además de la información general, cada entrenador cuenta con un campo adicional que indica su **especialización**:
  - **Entrenador de porteros**
  - **Entrenador principal**
  - **Entrenador asistente**

### ⚽ Jugadores
- Se almacena información adicional como:
  - **Posición** (Portero, Defensa, Centrocampista o Delantero)
  - **Número de camiseta** (Dorsal)
  - **Altura y peso**
  - **Goles anotados**
  - **Partidos jugados**

### 🛠️ Funcionalidades CRUD y de gestión
Se ha implementado un **menú interactivo** con las siguientes opciones:
   |1. Cargar datos desde fichero
   |2. Crear un miebro del equipo
   |3. Actualizar un miembro del equipo
   |4. Eliminar un miembro del equipo
   |5. Mostrar miembros
   |6. Exportar equipo a fichero
   |7. Imprimir conusltas del equipo
   |8. Salir de la aplicacion

### 🔍 Consultas Disponibles
El sistema permite realizar múltiples consultas, entre ellas:
- Listado de **jugadores y entrenadores**.
- **Delantero más alto** y media de goles de los delanteros.
- **Defensa con más partidos jugados**.
- Jugadores **agrupados por país** y por **año de incorporación**.
- **Entrenador con mayor salario**.
- **Salario promedio** de los jugadores por país.
- Jugadores con **salario mayor al promedio del equipo**.
- **Estimación del coste total de la plantilla**.

Y muchas otras estadísticas sobre el equipo.

## 📥 Entrada y Salida de Datos
- **Entrada**: Datos ingresados por consola o archivos **CSV, XML, JSON y Binario**.
- **Salida**: Resultados mostrados por consola o exportados en los formatos mencionados.
- **Fechas**: La salida por consola se adapta al estándar ISO 8601.

## 👥 Equipo de Desarrollo
Este proyecto fue desarrollado por **Samuel Gómez Gutiérrez, Carlos Cortés Yagüe, Jesús Cobo Arrogante**.
