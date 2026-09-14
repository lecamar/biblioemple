public class BibliotecaInc {

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        Libro libro1 = new Libro("Hojas de hierba", "Walt Whitman", "978-8424939229");
        Libro libro2 = new Libro("Poesia completa", "Emily Dickinson", "978-8491041146");
        Libro libro3 = new Libro("Antologia poetica", "Jorge Luis Borges", "978-8420675206");
        Libro libro4 = new Libro("Veinte poemas de amor", "Pablo Neruda", "978-8432240942");
        Libro libro5 = new Libro("Poeta en Nueva York", "Federico Garcia Lorca", "978-8439722380");

        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        biblioteca.agregarLibro(libro3);
        biblioteca.agregarLibro(libro4);
        biblioteca.agregarLibro(libro5);

        
        Socio socio1 = new Socio("James P. Sullivan", "INC001");
        Socio socio2 = new Socio("Mike Wazowski", "INC002");

        biblioteca.agregarSocio(socio1);
        biblioteca.agregarSocio(socio2);

        System.out.println("===> a) y ===> b) Prestar 4 libros al primer socio (el 4to se rechaza)");
        biblioteca.prestar(socio1, libro1); // ok
        biblioteca.prestar(socio1, libro2); // ok
        biblioteca.prestar(socio1, libro3); // ok
        biblioteca.prestar(socio1, libro4); // Rechazado: supera el límite de 3

        System.out.println();
        System.out.println("===> c) Intentar prestar a socio2 un libro ya prestado");
        biblioteca.prestar(socio2, libro1); // ya no está disponible

        System.out.println();
        System.out.println("===> d) Listar disponibles");
        biblioteca.listarDisponibles();

        System.out.println();
        System.out.println("===> e) Devolver un libro y volver a listar");
        biblioteca.devolver(socio1, libro2);
        biblioteca.listarDisponibles();

        System.out.println();
        System.out.println(socio1);
        System.out.println(socio2);
        System.out.println(biblioteca);

        // ***************************
        // Demostración Pregunta 1.a
        // ***************************
        System.out.println();
        System.out.println("===> Demostración de referencia compartida (Pregunta 1.a)");

        System.out.println("Antes del cambio:");
        System.out.println("  Desde biblioteca: " + biblioteca.getLibros().get(0));
        System.out.println("  Desde socio1:     " + socio1.getLibrosPrestados().get(0));

        // Modifico disponible desde la lista del socio
        Libro libroDesdeSocio = socio1.getLibrosPrestados().get(0);
        libroDesdeSocio.setDisponible(true);

        System.out.println();
        System.out.println("Después de cambiar 'disponible' a true SOLO desde la referencia de socio1:");
        System.out.println("  Desde biblioteca: " + biblioteca.getLibros().get(0));
        System.out.println("  Desde socio1:     " + socio1.getLibrosPrestados().get(0));
        System.out.println("  ¿Apuntan a la misma dirección de memoria? (==): " + 
                           (biblioteca.getLibros().get(0) == socio1.getLibrosPrestados().get(0)));

        // Reestablezco estado
        libroDesdeSocio.setDisponible(false);

        // ***************************
        // Pregunta 1.b) Explicación
        // ***************************
        //
        // ¿Por qué el cambio hecho desde socio1 se refleja también en biblioteca?
        //
        // En Java las variables no contienen los objetos: Una variable de tipo objeto solo guarda una dirección que dice en qué parte de la memoria del computador vive el objeto real.

        //Al hacer socio.agregarLibroPrestado(libro), no se duplica el libro. Le doy al socio una copia de la dirección, no del libro.

        //Tanto la lista de la biblioteca como la lista del socio apuntan al mismo objeto Libro. Cualquier modificación hecha desde cualquier lado cambiará el objeto real directamente.
        //la ventaja es que se ahorra memoria y los datos sincronizados automáticamente. No tengo que ir a buscar en la lista de la biblioteca para actualizar el estado cada vez que el socio devuelve el libro, porque ambos ya leen la misma información.

    }
}


