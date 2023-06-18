# Tarea Final: Sistema de Tickets

## Dependencias

- Java 17

<br>

## Comandos de Ejecución

En la carpeta de la tarea se abre la terminal y se ejecutan los siguientes comandos:

```
javac -d bin src/*/*.java

java -cp ".:bin/" System/Simulador
```

<br>

## Resultado previsto en terminal.

Consideremos que al repetirse la simulación un par de veces, el resultado varia en cuanto a que
actividad vende más, ganancias del día, membresías usadas, etc. Por lo anterior, pondremos un ejemplo
de como se ve en terminal el resultado de la ejecución de un día de simulación.

```
Bienvenido al Sistema de Ventas del CCU.

Dia Laborado: 2023-06-10
Esto ocurrió hoy:

Fila de Venta:

Membresia: Estudiante
Prioridad: 3
Hora de entrada: 23:25:279:975629155
Actividad: Teatro

Membresia: Estudiante
Prioridad: 3
Hora de entrada: 23:25:279:975710108
Actividad: Museo

Membresia: Visitante Normal
Prioridad: 4
Hora de entrada: 23:25:279:971781855
Actividad: Exposición

Membresia: Visitante Normal
Prioridad: 4
Hora de entrada: 23:25:279:975536520
Actividad: Teatro

Membresia: Visitante Normal
Prioridad: 4
Hora de entrada: 23:25:279:975786242
Actividad: Teatro


Total de Boletos Vendidos: 5
Actividad más vendida: Teatro
Actividad menos vendida: Cine
Ganancias Totales del día: 310.0

CCU Cerrado por hoy. Resumen del día guardado exitosamente.
```

La información anterior también se puede consultar en el archivo _Dia #X.txt_ generado
tras la ejecución.

<br>

## Documentación

Para generar la documentación se ocupa el siguiente comando en la carpeta de la tarea:

```
javadoc -d docs src/*/*.java
```

Y esta se podrá abrir en el navegador desde el archivo _allclasses-index.html_

<br>

> _I fight for the USER!..._
