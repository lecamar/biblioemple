import java.util.List;
import java.util.ArrayList;

public class Biblioteca {
     private List<Libro> libros;
     private List<Socio> socios;
    

public Biblioteca() {
     this.libros = new ArrayList<>();
     this.socios = new ArrayList<>();
    }

public void agregarLibro(Libro libro) {
     libros.add(libro);
    }

public void agregarSocio(Socio socio) {
        socios.add(socio);
    }

    public List<Libro> getLibros() {
        return libros;
    }

public List<Socio> getSocios() {
        return socios;
    }


    public void prestar(Socio socio, Libro libro) {
        if (socio == null || libro == null) {
            System.out.println("RECHAZADO: Socio o libro inválido (nulo).");
            return;
        }
        if (!libro.isDisponible()) {
            System.out.println("RECHAZADO: El libro " + libro.getTitulo() + " ya está prestado.");
            return;
        }
        if (!socio.puedePrestar()) {
            System.out.println("RECHAZADO: El socio " + socio.getNombre() + " ya tiene 3 libros prestados.");
            return;
        }

        libro.setDisponible(false);
        socio.agregarLibroPrestado(libro);
        System.out.println("SE PUEDE PRESTAR: El libro " + libro.getTitulo() + " se le presta a " + socio.getNombre());
    }

    public void devolver(Socio socio, Libro libro) {
        if (socio == null || libro == null) {
            System.out.println("ERROR: Socio o libro inválido (nulo).");
            return;
        }
        
        if (socio.devolverLibro(libro)) {
            libro.setDisponible(true);
            System.out.println("LIBRO DEVUELTO: El libro " + libro.getTitulo() + " fue devuelto por " + socio.getNombre());
        } else {
            System.out.println("ERROR: El socio " + socio.getNombre() + " no tiene prestado el libro " + libro.getTitulo());
        }
    }

    public void listarDisponibles() {
        System.out.println("*** Libros Disponibles ***");
        boolean hayDisponibles = false;
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                System.out.println(" ☺ " + libro);
                hayDisponibles = true;
            }
        }
        if (!hayDisponibles) {
            System.out.println("No hay libros disponibles");
        }
    }

    @Override
    public String toString() {
        return "Biblioteca: " + libros.size() + " libro(s) registrado(s) | "
                + socios.size() + " socio(s) registrado(s)";
    }
}

