# Video

[Explicación sistema de biblioteca y nómina de empleados](https://youtu.be/iJYcYxYZdIc)

# BibliotecaInc

## Descripción del proyecto

Este proyecto implementa un sistema básico de gestión de una biblioteca utilizando Java y programación orientada a objetos.

El sistema permite:

* Registrar libros.
* Registrar socios.
* Prestar libros a los socios.
* Controlar la disponibilidad de los libros.
* Limitar a un máximo de 3 libros prestados por socio.
* Devolver libros.
* Consultar los libros disponibles.
* Demostrar el funcionamiento de las referencias compartidas entre objetos.

Las respuestas a las preguntas planteadas se encuentran en el archivo `BibliotecaInc.java` al final.

## Manual de programación

### Estructura del proyecto

El proyecto está compuesto por las siguientes clases:

```text
BibliotecaInc.java
Biblioteca.java
Libro.java
Socio.java
```

### Clase BibliotecaInc

Es la clase principal del programa y contiene el método `main`, desde donde se crean los objetos y se ejecutan las diferentes pruebas.

En esta clase se realizan las siguientes operaciones:

1. Se crea una instancia de `Biblioteca`.
2. Se crean cinco libros.
3. Se registran los libros en la biblioteca.
4. Se crean dos socios.
5. Se registran los socios en la biblioteca.
6. Se realizan diferentes préstamos.
7. Se comprueba el límite de tres libros por socio.
8. Se intenta prestar un libro que ya está prestado.
9. Se muestran los libros disponibles.
10. Se devuelve un libro.
11. Se vuelven a listar los libros disponibles.
12. Se demuestra el funcionamiento de las referencias compartidas.

### Clase Libro

La clase `Libro` representa un libro dentro del sistema.

Sus atributos principales son:

```java
private String titulo;
private String autor;
private String isbn;
private boolean disponible;
```

Estos atributos representan:

| Atributo     | Descripción                                      |
| ------------ | ------------------------------------------------ |
| `titulo`     | Título del libro                                 |
| `autor`      | Autor del libro                                  |
| `isbn`       | Identificador ISBN del libro                     |
| `disponible` | Indica si el libro está disponible para préstamo |

Al crear un libro, este comienza con el estado disponible:

```java
this.disponible = true;
```

El constructor también realiza validaciones para evitar que el título, autor o ISBN sean nulos o estén vacíos.

### Clase Socio

La clase `Socio` representa a una persona registrada en la biblioteca.

Sus atributos son:

```java
private String nombre;
private String numeroCarnet;
private List<Libro> librosPrestados;
```

Cada socio tiene un nombre, un número de carnet y una lista de libros que tiene actualmente en préstamo.

El método `puedePrestar()` controla el límite máximo de libros:

```java
public boolean puedePrestar() {
    return librosPrestados.size() < 3;
}
```

Por lo tanto, un socio puede tener como máximo tres libros prestados al mismo tiempo.

### Clase Biblioteca

La clase `Biblioteca` administra los libros y socios registrados.

Sus atributos son:

```java
private List<Libro> libros;
private List<Socio> socios;
```

La biblioteca utiliza listas para almacenar los libros y socios registrados.

Los métodos principales son:

```java
agregarLibro()
agregarSocio()
prestar()
devolver()
listarDisponibles()
```

El método `prestar()` comprueba diferentes condiciones antes de realizar un préstamo:

```java
if (socio == null || libro == null)
```

Primero verifica que el socio y el libro sean válidos.

Después verifica si el libro está disponible:

```java
if (!libro.isDisponible())
```

Finalmente comprueba que el socio no haya alcanzado el límite de tres libros:

```java
if (!socio.puedePrestar())
```

Si todas las condiciones son correctas, el libro pasa a estar prestado y se agrega a la lista de libros del socio.

## Preguntas planteadas

### Pregunta 1.a: Referencia compartida

En el `main` se realiza una demostración de referencia compartida.

Primero se obtiene un libro desde la lista de libros prestados del socio:

```java
Libro libroDesdeSocio = socio1.getLibrosPrestados().get(0);
```

Posteriormente se modifica su disponibilidad:

```java
libroDesdeSocio.setDisponible(true);
```

Después se consulta el mismo libro desde la biblioteca y desde el socio.

También se utiliza el operador `==`:

```java
biblioteca.getLibros().get(0) == socio1.getLibrosPrestados().get(0)
```

El resultado es `true`.

Esto demuestra que ambas listas contienen una referencia al mismo objeto `Libro`, y no dos objetos diferentes.

### Pregunta 1.b: ¿Por qué el cambio se refleja también en la biblioteca?

En Java las variables no contienen los objetos: Una variable de tipo objeto solo guarda una dirección que dice en qué parte de la memoria del computador vive el objeto real. 
Al hacer `socio.agregarLibroPrestado(libro)` no se duplica el libro. Le doy al socio una copia de la dirección, no del libro.

La ventaja es que se ahorra memoria y los datos se sincronizan automáticamente. No tengo que ir a buscar en la lista de la biblioteca para actualizar el estado cada vez que el socio devuelve el libro, porque ambos ya leen la misma información.

## Manual de pruebas funcionales

Las pruebas funcionales se encuentran implementadas directamente en el método `main` de `BibliotecaInc`.

### Prueba 1: Préstamo de libros

Se realizan tres préstamos exitosos al primer socio:

```java
biblioteca.prestar(socio1, libro1);
biblioteca.prestar(socio1, libro2);
biblioteca.prestar(socio1, libro3);
```

Resultado esperado:

```text
SE PUEDE PRESTAR: El libro Hojas de hierba se le presta a James P. Sullivan
SE PUEDE PRESTAR: El libro Poesia completa se le presta a James P. Sullivan
SE PUEDE PRESTAR: El libro Antologia poetica se le presta a James P. Sullivan
```

Los tres libros pasan a tener estado `Prestado`.

### Prueba 2: Límite máximo de préstamos

Se intenta prestar un cuarto libro al mismo socio:

```java
biblioteca.prestar(socio1, libro4);
```

Como el socio ya tiene tres libros prestados, la operación es rechazada.

Resultado esperado:

```text
RECHAZADO: El socio James P. Sullivan ya tiene 3 libros prestados.
```

Esta prueba verifica que se cumple la regla de máximo tres libros por socio.

### Prueba 3: Prestar un libro que ya está prestado

Se intenta prestar `libro1` al segundo socio:

```java
biblioteca.prestar(socio2, libro1);
```

El libro ya fue prestado al primer socio, por lo que no está disponible.

Resultado esperado:

```text
RECHAZADO: El libro Hojas de hierba ya está prestado.
```

Esta prueba verifica el control de disponibilidad de los libros.

### Prueba 4: Listar libros disponibles

Se ejecuta:

```java
biblioteca.listarDisponibles();
```

El método recorre todos los libros y muestra únicamente aquellos cuyo atributo `disponible` sea `true`.

Después de realizar los primeros préstamos, los libros disponibles corresponden a los que todavía no han sido prestados.

### Prueba 5: Devolver un libro

Se devuelve `libro2` mediante:

```java
biblioteca.devolver(socio1, libro2);
```

El libro se elimina de la lista de préstamos del socio y vuelve a estar disponible.

Resultado esperado:

```text
LIBRO DEVUELTO: El libro Poesia completa fue devuelto por James P. Sullivan
```

Después se ejecuta nuevamente:

```java
biblioteca.listarDisponibles();
```

Esto permite comprobar que el libro devuelto vuelve a aparecer entre los libros disponibles.

### Prueba 6: Comprobación de referencias compartidas

Se obtiene el mismo libro desde la lista del socio:

```java
Libro libroDesdeSocio = socio1.getLibrosPrestados().get(0);
```

Se modifica su disponibilidad:

```java
libroDesdeSocio.setDisponible(true);
```

Luego se compara la referencia desde la biblioteca y desde el socio:

```java
biblioteca.getLibros().get(0) == socio1.getLibrosPrestados().get(0)
```

El resultado esperado es:

```text
true
```

Esto demuestra que ambas referencias apuntan al mismo objeto en memoria.

## Evidencia

![Screenshot de la consola en Eclipse](./img/Screenshot%202026-09-13%20213854.png)

## Resultado general

El programa permite comprobar mediante pruebas funcionales que:

* Los libros pueden registrarse correctamente.
* Los socios pueden registrarse correctamente.
* Los préstamos se realizan cuando se cumplen las condiciones.
* Un socio no puede tener más de tres libros prestados.
* Un libro prestado no puede ser prestado nuevamente.
* Los libros pueden ser devueltos.
* Los libros devueltos vuelven a estar disponibles.
* El sistema utiliza referencias compartidas entre `Biblioteca`, `Socio` y `Libro`.
* Las validaciones de datos evitan la creación de objetos con información vacía o nula.

# Nómina empleados

Este proyecto es un sistema sencillo para administrar la nómina de la empresa Monsters Inc.

El proyecto está desarrollado en Java y utiliza programación orientada a objetos.

El sistema permite:

* Registrar empleados.
* Manejar diferentes tipos de empleados.
* Calcular el sueldo de cada empleado.
* Calcular el total de la nómina.
* Identificar al empleado con el sueldo más alto.
* Mostrar la información de los empleados.

Se utilizan los siguientes conceptos de programación orientada a objetos:

* Clases y objetos.
* Herencia.
* Abstracción.
* Encapsulamiento.
* Polimorfismo.
* Sobreescritura de métodos.

# Clase Main

La clase `Main` es la encargada de iniciar el programa.

Primero se crea la empresa:

```java
Empresa empresa = new Empresa("Monsters Inc.");
```

Después se registran los empleados de la empresa.

Se crean 2 gerentes:

```java
empresa.contratar(new Gerente("James P. Sullivan", "10234567", 4000000, 12, 9600000));
empresa.contratar(new Gerente("Henry J. Waternoose", "10234568", 5000000, 30, 12000000));
```

Se crean 3 empleados fijos:

```java
empresa.contratar(new EmpleadoFijo("Mike Wazowski", "10234569", 2200000, 10));
empresa.contratar(new EmpleadoFijo("Randall Boggs", "10234570", 2000000, 8));
empresa.contratar(new EmpleadoFijo("Celia Mae", "10234571", 1800000, 5));
```

Se crean 2 empleados por horas:

```java
empresa.contratar(new EmpleadoPorHoras("Roz", "10234572", 0, 170, 8500));
empresa.contratar(new EmpleadoPorHoras("Boo Mary", "10234573", 0, 60, 7000));
```

Después se calcula la nómina completa:

```java
double totalNomina = empresa.pagarNomina();
```

Finalmente, se busca el empleado con el sueldo más alto:

```java
Empleado mejorPagado = empresa.empleadoMejorPagado();
```

# Clase Empresa

La clase `Empresa` representa a la empresa y se encarga de administrar a los empleados.

Tiene una lista donde se almacenan los empleados:

```java
private List<Empleado> empleados;
```

Para contratar un empleado se utiliza el método:

```java
public void contratar(Empleado empleado)
```

Este método agrega el empleado a la lista.

## Pago de nómina

El método:

```java
public double pagarNomina()
```

recorre todos los empleados y calcula el sueldo de cada uno.

```java
for (Empleado e : empleados) {
    e.mostrarInfo();
    total += e.calcularSueldo();
}
```

El método devuelve el total que debe pagar la empresa.

## Empleado mejor pagado

El método:

```java
public Empleado empleadoMejorPagado()
```

compara los sueldos de todos los empleados y devuelve el empleado que tenga el sueldo más alto.

Si no existen empleados, devuelve `null`.

# Clase Empleado

`Empleado` es una clase abstracta que representa a un empleado general de la empresa.

Contiene los siguientes atributos:

```java
protected String nombre;
protected String cedula;
protected double salarioBase;
```

Estos atributos representan el nombre, la cédula y el salario base.

La clase contiene el método abstracto:

```java
public abstract double calcularSueldo();
```

Cada tipo de empleado implementa este método de acuerdo con su forma de calcular el sueldo.

También contiene el método:

```java
public void mostrarInfo()
```

Este método muestra el nombre, la cédula y el sueldo calculado.

# Clase EmpleadoFijo

`EmpleadoFijo` hereda de `Empleado`.

Tiene un atributo adicional:

```java
protected int anosAntiguedad;
```

El sueldo se calcula tomando el salario base y agregando un 2% por cada año de antigüedad.

La fórmula utilizada es:

```text
Bono de antigüedad = salario base × 0.02 × años de antigüedad

Sueldo = salario base + bono de antigüedad
```

Por ejemplo, para Mike Wazowski:

```text
Salario base: $2.200.000
Antigüedad: 10 años

Bono = 2.200.000 × 0.02 × 10
Bono = $440.000

Sueldo = 2.200.000 + 440.000
Sueldo = $2.640.000
```

# Clase Gerente

`Gerente` hereda de `EmpleadoFijo`.

Además de las características de un empleado fijo, tiene un bono anual:

```java
protected double bonoAnual;
```

El bono anual se divide entre 12 para obtener el bono mensual.

El sueldo se calcula de la siguiente manera:

```text
Sueldo de empleado fijo + bono anual / 12
```

Para James P. Sullivan:

```text
Salario base: $4.000.000
Antigüedad: 12 años
Bono anual: $9.600.000

Bono de antigüedad:
4.000.000 × 0.02 × 12 = $960.000

Sueldo con antigüedad:
4.000.000 + 960.000 = $4.960.000

Bono mensual:
9.600.000 / 12 = $800.000

Sueldo final:
4.960.000 + 800.000 = $5.760.000
```

# Clase EmpleadoPorHoras

`EmpleadoPorHoras` representa a los empleados que reciben un pago según las horas trabajadas.

Tiene los siguientes atributos:

```java
protected double horasTrabajadas;
protected double valorHora;
```

Las primeras 160 horas se pagan al valor normal.

Las horas que superen las 160 se pagan al doble.

La fórmula para las horas normales es:

```text
Sueldo = horas trabajadas × valor de la hora
```

Cuando existen horas extras:

```text
Sueldo = (160 × valor hora) +
         (horas extra × valor hora × 2)
```

Para Roz:

```text
Horas trabajadas: 170
Valor de la hora: $8.500

Horas normales: 160
Horas extra: 10

Pago normal:
160 × 8.500 = $1.360.000

Pago extra:
10 × 8.500 × 2 = $170.000

Sueldo total:
1.360.000 + 170.000 = $1.530.000
```

# Conceptos de programación utilizados

## Abstracción

La clase `Empleado` es abstracta:

```java
public abstract class Empleado
```

Esto permite definir las características generales de un empleado sin crear directamente objetos de esta clase.

## Herencia

`EmpleadoFijo` y `EmpleadoPorHoras` heredan de `Empleado`.

```java
public class EmpleadoFijo extends Empleado
```

```java
public class EmpleadoPorHoras extends Empleado
```

`Gerente` hereda de `EmpleadoFijo`:

```java
public class Gerente extends EmpleadoFijo
```

Esto permite reutilizar atributos y métodos.

## Polimorfismo

El método `calcularSueldo()` está definido en `Empleado`, pero cada clase lo implementa de una manera diferente.

Cuando la empresa ejecuta:

```java
e.calcularSueldo()
```

Java utiliza el método correspondiente al tipo real del empleado.

## Encapsulamiento

Los atributos y métodos de cada clase se mantienen organizados dentro de la clase correspondiente.

Por ejemplo:

```java
public String getNombre()
```

permite obtener el nombre de un empleado sin acceder directamente al atributo.

# Manual de pruebas funcionales

Las pruebas realizadas permiten comprobar el funcionamiento de las principales partes del sistema.

# Prueba de contratación

Se registran 7 empleados en la empresa:

```text
2 Gerentes
3 Empleados Fijos
2 Empleados por Horas
```

Resultado esperado:

```text
>> Contratado: James P. Sullivan
>> Contratado: Henry J. Waternoose
>> Contratado: Mike Wazowski
>> Contratado: Randall Boggs
>> Contratado: Celia Mae
>> Contratado: Roz
>> Contratado: Boo Mary
```

# Prueba de empleado fijo

Para Mike Wazowski:

```text
Salario base: $2.200.000
Antigüedad: 10 años
```

Resultado esperado:

```text
Sueldo: $2.640.000
```

# Prueba de gerente

Para James P. Sullivan:

```text
Salario base: $4.000.000
Antigüedad: 12 años
Bono anual: $9.600.000
```

Resultado esperado:

```text
Sueldo: $5.760.000
```

# Prueba de empleado por horas sin horas extras

Para Boo Mary:

```text
Horas trabajadas: 60
Valor de la hora: $7.000
```

Resultado esperado:

```text
Sueldo: $420.000
```

# Prueba de empleado por horas con horas extras

Para Roz:

```text
Horas trabajadas: 170
Valor de la hora: $8.500
```

Las primeras 160 horas se pagan normalmente y las 10 horas restantes se pagan al doble.

Resultado esperado:

```text
Sueldo: $1.530.000
```

# Prueba de nómina completa

Los sueldos esperados son:

| Empleado            | Tipo          |     Sueldo |
| ------------------- | ------------- | ---------: |
| James P. Sullivan   | Gerente       | $5.760.000 |
| Henry J. Waternoose | Gerente       | $9.000.000 |
| Mike Wazowski       | Empleado fijo | $2.640.000 |
| Randall Boggs       | Empleado fijo | $2.320.000 |
| Celia Mae           | Empleado fijo | $1.980.000 |
| Roz                 | Por horas     | $1.530.000 |
| Boo Mary            | Por horas     |   $420.000 |

El total esperado es:

```text
$23.650.000
```

# Prueba de empleado mejor pagado

Después de calcular los sueldos, el programa busca el empleado con el sueldo más alto.

En este caso:

```text
Henry J. Waternoose
```

Su sueldo es:

```text
$9.000.000
```

Por lo tanto, el programa debe mostrar a Henry J. Waternoose como el empleado mejor pagado.

# Resumen de pruebas

| Prueba                    | Resultado |
| ------------------------- | --------- |
| Contratación de empleados | Correcta  |
| Cálculo de empleado fijo  | Correcta  |
| Cálculo de gerente        | Correcta  |
| Empleado por horas        | Correcta  |
| Horas extras              | Correcta  |
| Cálculo de nómina         | Correcta  |
| Empleado mejor pagado     | Correcta  |

# Evidencia

![Screenshot de la consola en Eclipse](./img/Screenshot%202026-09-13%20213827.png)

# Resultado final

El sistema permite administrar una nómina básica utilizando programación orientada a objetos.

Las pruebas realizadas comprueban que los empleados se registran correctamente, que cada tipo de empleado calcula su sueldo de acuerdo con sus reglas, que las horas extras se pagan correctamente, que se puede calcular el total de la nómina y que se puede identificar al empleado mejor pagado.


