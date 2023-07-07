# Práctica 2: Listas

## Comandos de Ejecución

En la carpeta de la práctica abre la terminal y ejecuta los siguientes comandos: 

```
javac -d bin/ src/*.java

java -cp ".:bin/" PruebaLista
```

Para poder ver la documentación, se ejecuta: 

```
cd src/

javadoc -d docs *.java
```
y revisar el archivo _allclasses-index_.

## Resultado previsto en terminal.

```
Prueba #1: Propiedades básicas de la lista: Añadir, Eliminar, tamaño, indice...:

Lista #0: 

Agregamos unos cuantos elementos.
[7946.0]

[7946.0] <-> [7852.25852]

[7946.0] <-> [7852.25852] <-> [0.2]

Tamaño de la lista: 3

Indice de un elemento, 7946.00: 0

Elemento en la posición 0: 7946.0

Agregamos al inicio un elemento:
[484848.2] <-> [7946.0] <-> [7852.25852] <-> [0.2]

Agregamos al final un elemento:
[484848.2] <-> [7946.0] <-> [7852.25852] <-> [0.2] <-> [4848.2]

Eliminamos elementos:
[484848.2] <-> [7946.0] <-> [7852.25852] <-> [4848.2]

[484848.2] <-> [7852.25852] <-> [4848.2]

Revisamos si un elemento existe en un la lista, por ejemplo, 4.0: false

Prueba #2: Copia, Reversa, Merge, Listas iguales. Damos dos lista de prueba:

Lista #1: [1] <-> [2] <-> [3] <-> [4] <-> [5] <-> [6] <-> [7] <-> [8] <-> [9] <-> [10] <-> [11] <-> [12] <-> [13] <-> [14]

Lista #2: [0] <-> [20] <-> [21] <-> [22] <-> [23] <-> [24] <-> [25] <-> [26] <-> [27] <-> [28] <-> [29] <-> [30] <-> [31] <-> [32] <-> [33] <-> [34]

Sea la lista #3 la copia de Lista 1: [1] <-> [2] <-> [3] <-> [4] <-> [5] <-> [6] <-> [7] <-> [8] <-> [9] <-> [10] <-> [11] <-> [12] <-> [13] <-> [14]

Sea la lista #4 la reversa de Lista 2: [34] <-> [33] <-> [32] <-> [31] <-> [30] <-> [29] <-> [28] <-> [27] <-> [26] <-> [25] <-> [24] <-> [23] <-> [22] <-> [21] <-> [20] <-> [0]

Sea la lista #5 la combinacion ("merge") de lista 1 y 2 [0] <-> [1] <-> [2] <-> [3] <-> [4] <-> [5] <-> [6] <-> [7] <-> [8] <-> [9] <-> [10] <-> [11] <-> [12] <-> [13] <-> [14] <-> [20] <-> [21] <-> [22] <-> [23] <-> [24] <-> [25] <-> [26] <-> [27] <-> [28] <-> [29] <-> [30] <-> [31] <-> [32] <-> [33] <-> [34]

Veamos ahora igualdad y desigualdad de listas.

¿Lista 3 es igual a Lista 1?: true

¿Lista 3 es igual a Lista 2?: false

Sea la lista #6 una lista de aleatorios:  [14] <-> [39] <-> [0] <-> [42] <-> [65] <-> [64] <-> [56] <-> [57] <-> [36] <-> [16]

Usamos mergeSort() para ordenar la lista.
 
Lista Acomodada: [0] <-> [14] <-> [16] <-> [36] <-> [39] <-> [42] <-> [56] <-> [57] <-> [64] <-> [65]

```

## Notas

Los valores finales en mergeSort() serán diferentes, ya que la lista sobre la cual lo hace es una lista de valores aleatorios entre 0 y 80.


<br>

> _I'm doing science and I'm still alive!_