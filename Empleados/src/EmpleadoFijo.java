public class EmpleadoFijo extends Empleado {

    protected int anosAntiguedad;

    public EmpleadoFijo(String nombre, String cedula, double salarioBase, int anosAntiguedad) {
        super(nombre, cedula, salarioBase);
        this.anosAntiguedad = anosAntiguedad;
    }

    public int getAnosAntiguedad() {
        return anosAntiguedad;
    }

    @Override
    public double calcularSueldo() {
        double bonoAntiguedad = salarioBase * 0.02 * anosAntiguedad;
        return salarioBase + bonoAntiguedad;
    }

    @Override
    public String toString() {
        return "EmpleadoFijo{" +
                "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", anosAntiguedad=" + anosAntiguedad +
                ", sueldoCalculado=" + calcularSueldo() +
                '}';
    }
}