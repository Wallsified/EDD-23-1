# Práctica 3: Pilas y Colas

## Comandos de Ejecución

En la carpeta de la práctica abre la terminal y ejecuta los siguientes comandos: 

```
javac -d bin/ src/*.java

java -cp ".:bin/" PruebaEstructuras
```

Para poder ver la documentación, se ejecuta: 

```
cd src/

javadoc -d docs *.java
```
y revisar el archivo _allclasses-index_.

## Resultado previsto en terminal.

```
 ----- PRUEBA DE PILA ----- 

Añadimos elementos a la Pila y la imprimimos: 

[9.0] -> [8.0] -> [7.0] -> [6.0] -> [5.0] -> [4.0] -> [3.0] -> [2.0] -> [1.0]

Hacemos pop() y eliminamos el primer elemento:
 
[8.0] -> [7.0] -> [6.0] -> [5.0] -> [4.0] -> [3.0] -> [2.0] -> [1.0]

Hacemos push() y agregamos un elemento nuevo

[123456.01] -> [8.0] -> [7.0] -> [6.0] -> [5.0] -> [4.0] -> [3.0] -> [2.0] -> [1.0]

Vemos el primer elemento de la Pila usando top(): 123456.01

 ----- PRUEBA DE COLA ----- 

Añadimos elementos a la Cola y la imprimimos: 

[1.0] <- [2.0] <- [3.0] <- [4.0] <- [5.0] <- [6.0] <- [7.0] <- [8.0] <- [9.0] <- [10.0] <- [11.0] <- [12.0] <- [13.0] <- [14.0] <- [15.0] <- [16.0] <- [17.0] <- [18.0] <- [19.0]

Hacemos dequeue() y eliminamos el primer elemento:

[2.0] <- [3.0] <- [4.0] <- [5.0] <- [6.0] <- [7.0] <- [8.0] <- [9.0] <- [10.0] <- [11.0] <- [12.0] <- [13.0] <- [14.0] <- [15.0] <- [16.0] <- [17.0] <- [18.0] <- [19.0]

Hacemos queue() y agregamos un elemento nuevo

[2.0] <- [3.0] <- [4.0] <- [5.0] <- [6.0] <- [7.0] <- [8.0] <- [9.0] <- [10.0] <- [11.0] <- [12.0] <- [13.0] <- [14.0] <- [15.0] <- [16.0] <- [17.0] <- [18.0] <- [19.0] <- [140.0]

Vemos el primer elemento de la Cola usando peek(): 2.0

```

<br>

> _Peaches peaches peaches peaches peaches peaches...._