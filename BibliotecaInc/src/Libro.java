public class Libro {
    
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponible;


public Libro(String titulo, String autor, String isbn) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El título del libro no puede estar vacío.");
        }
        if (autor == null || autor.isBlank()) {
            throw new IllegalArgumentException("El autor del libro no puede estar vacío.");
        }
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("El ISBN del libro no puede estar vacío.");
        }
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponible = true;
    }


public String getTitulo() {
        return titulo;
    }

public String getAutor() {
        return autor;
    }

public String getIsbn() {
        return isbn;
    }

public boolean isDisponible() { 
        return disponible; }


void setDisponible(boolean disponible) {
    this.disponible = disponible;
    }


@Override
    public String toString() {
        String estado = disponible ? "Disponible" : "Prestado";
        return "Libro: " + titulo + " | Autor: " + autor + " | ISBN: " + isbn + " | Estado: " + estado;
    }
}


