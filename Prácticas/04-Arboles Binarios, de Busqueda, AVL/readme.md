# Práctica 4: Arboles Binarios, de Búsqueda y AVL

## Comandos de Ejecución

En la carpeta de la práctica abre la terminal y ejecuta los siguientes comandos:

```
javac -cp ".:lib/PilasYColas.jar" -d bin src/*.java

java -cp ".:lib/PilasYColas.jar:bin/" PruebasArbol
```

Para poder ver la documentación, se ejecuta:

```
javadoc -cp ".:lib/PilasYColas.jar" -d docs src/*.java

cd /docs
```

y revisar el archivo _allclasses-index_.

## Resultado previsto en terminal.

Nota: El valor del resultado es siempre aleatorio. Asi que esto es un posible resultado.

```
-------- Pruebas Árboles Binarios de Búsqueda --------

-------- Pruebas de Agregación de elementos --------

Agregamos valores aleatorios al árbol del 1 al 35

8
├─›0
│  └─»2
│     └─»7
└─»24
   ├─›16
   │  ├─›15
   │  └─»16
   │     └─»19
   │        └─›18
   └─»30

Agregamos un elemento fijo, 100:

8
├─›0
│  └─»2
│     └─»7
└─»24
   ├─›16
   │  ├─›15
   │  └─»16
   │     └─»19
   │        └─›18
   └─»30
      └─»100

 -------- Pruebas de Contención --------

El elemento 100 se encuentra en el arbol?: true

El elemento 1 se encuentra en el arbol?: false

-------- Pruebas de eliminación --------

Eliminamos el elemento 100:

8
├─›0
│  └─»2
│     └─»7
└─»24
   ├─›16
   │  ├─›15
   │  └─»16
   │     └─»19
   │        └─›18
   └─»30

Eliminamos la raiz:8

7
├─›0
│  └─»2
└─»24
   ├─›16
   │  ├─›15
   │  └─»16
   │     └─»19
   │        └─›18
   └─»30

 -------- Pruebas de Tamaño --------

Según la propiedad de tamaño hay: 10 elementos

 -------- Pruebas de Rotación --------

Rotamos a la izquierda:

24
├─›7
│  ├─›0
│  │  └─»2
│  └─»16
│     ├─›15
│     └─»16
│        └─»19
│           └─›18
└─»30

Rotamos el arbol a la derecha:
7
├─›0
│  └─»2
└─»24
   ├─›16
   │  ├─›15
   │  └─»16
   │     └─»19
   │        └─›18
   └─»30

 -------- Pruebas de Ordenes --------

Árbol en inorden : {0, 2, 7, 15, 16, 16, 18, 19, 24, 30}

Arbol en preorden: {7, 0, 2, 15, 16, 16, 18, 19, 24, 30}

Arbol en postorden: {0, 2, 15, 16, 16, 18, 19, 24, 30, 7}


-------- Pruebas Árboles AVL --------

Generamos un árbol AVL de valores aleatorios que se autobalancea:

45
├─›29
│  ├─›22
│  └─»33
│     └─»34
└─»88
   ├─›59
   │  ├─›53
   │  └─»83
   └─»96


Agregamos un elemento fijo, 15:

45
├─›29
│  ├─›22
│  │  └─›15
│  └─»33
│     └─»34
└─»88
   ├─›59
   │  ├─›53
   │  └─»83
   └─»96


 -------- Pruebas de Contención --------

El elemento 15 se encuentra en el arbol?: true

El elemento 1 se encuentra en el arbol?: false

-------- Pruebas de eliminación --------

Eliminamos la raiz:45

34
├─›29
│  ├─›22
│  │  └─›15
│  └─»33
└─»88
   ├─›59
   │  ├─›53
   │  └─»83
   └─»96

 -------- Pruebas de Ordenes --------

Árbol en inorden : {15, 22, 29, 33, 34, 53, 59, 83, 88, 96}

Arbol en preorden: {34, 15, 22, 29, 33, 53, 59, 83, 88, 96}

Arbol en postorden: {15, 22, 29, 33, 53, 59, 83, 88, 96, 34}
```

<br>

> _Who's gonna code the world tonight? Who's gonna debug it back to life?...._
