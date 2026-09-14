import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class Socio {    
    private String nombre;
    private String numeroCarnet;
    private List<Libro> librosPrestados;


public Socio(String nombre, String numeroCarnet) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del socio no puede estar vacío.");
        }
        if (numeroCarnet == null || numeroCarnet.isBlank()) {
            throw new IllegalArgumentException("El número de carnet no puede estar vacío.");
        }
        this.nombre = nombre;
        this.numeroCarnet = numeroCarnet;
        this.librosPrestados = new ArrayList<>();        
    }

public boolean puedePrestar() {
    return librosPrestados.size() < 3;
    }

public void agregarLibroPrestado(Libro libro) {
    if (libro != null && puedePrestar()) {
        librosPrestados.add(libro);
    }
}

public boolean devolverLibro(Libro libro) {
    return librosPrestados.remove(libro);
}

public String getNombre() { return nombre; 
    }

public String getNumeroCarnet() { return numeroCarnet; 
    }

public List<Libro> getLibrosPrestados() { 
    return Collections.unmodifiableList(librosPrestados); 
}

@Override
public String toString() {
     return "Socio: " + nombre + " | Carnet: " + numeroCarnet + " | Libros prestados: " + librosPrestados.size();
    }
}



