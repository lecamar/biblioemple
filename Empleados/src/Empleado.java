public abstract class Empleado {

    protected String nombre;
    protected String cedula;
    protected double salarioBase;

    public Empleado(String nombre, String cedula, double salarioBase) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.salarioBase = salarioBase;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public abstract double calcularSueldo();

    public void mostrarInfo() {
        System.out.printf("Nombre: %-20s | Cedula: %-10s | Sueldo: $%,.2f%n",
                nombre, cedula, calcularSueldo());
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", salarioBase=" + salarioBase +
                ", sueldoCalculado=" + calcularSueldo() +
                '}';
    }
}